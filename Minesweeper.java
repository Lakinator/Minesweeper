import DrawLib.Drawables.DrawableText;
import DrawLib.Frames.DrawFrame;
import DrawLib.Frames.Components.SettingInput;
import DrawLib.Frames.Components.SubmitButton;
import DrawLib.Frames.SettingFrame;
import Game.Cell;
import Game.Gamestates;
import Game.Grid;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Minesweeper implements MouseListener {
    public static void main(String[] args) {
        new Minesweeper();
    }

    private Gamestates gamestate;

    private DrawFrame gameFrame, aboutFrame;
    private SettingFrame settingFrame;

    private Grid grid;
    private JMenuBar bar;
    private JMenu[] menus;
    private JMenuItem[] items;
    private int bombCount;
    private Cell[] bombs;

    public Minesweeper() {
        gameFrame = new DrawFrame("Minesweeper Beta 0.93 von Lukas");
        gameFrame.addMouseListener(this);

        bar = new JMenuBar();
        menus = new JMenu[1];
        menus[0] = new JMenu("Game");
        items = new JMenuItem[4];
        items[0] = new JMenuItem("Restart");
        items[0].addActionListener(new MenuItemHandler());
        items[1] = new JMenuItem("Reveal bombs");
        items[1].addActionListener(new MenuItemHandler());
        items[2] = new JMenuItem("Settings");
        items[2].addActionListener(new MenuItemHandler());
        items[3] = new JMenuItem("About");
        items[3].addActionListener(new MenuItemHandler());

        menus[0].add(items[0]);
        menus[0].add(items[1]);
        menus[0].add(items[2]);
        menus[0].add(items[3]);
        bar.add(menus[0]);
        gameFrame.setJMenuBar(bar);

        gameFrame.setVisible(true);
        setup(15, 10, 10, 40);
    }

    public void setup(int rowCount, int columnCount, int newBombCount, int cellSize) {
        grid = new Grid(rowCount, columnCount, cellSize);
        bombCount = newBombCount;
        bombs = new Cell[bombCount];

        //Resets screen and resizes it to match the rows, columns and the cellsize
        gameFrame.clearScreen();
        gameFrame.setSize(grid.getCellSize()*grid.getRowCount() + 7, grid.getCellSize()*grid.getColumnCount() + 53);

        //Initializing all Cells
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Cell temp = new Cell(grid.getCellSize()*i, grid.getCellSize()*j, grid.getCellSize(), grid.getCellSize(), i, j, false, Color.BLUE);
                grid.setCell(i, j, temp);
            }
        }

        //Random bombspot generator
        Random r = new Random();
        int x, y;
        for (int i = 0; i < bombCount; i++) {
            x = r.nextInt(grid.getRowCount());
            y = r.nextInt(grid.getColumnCount());

            while (grid.getCell(x, y).isBomb()) {
                x = r.nextInt(grid.getRowCount());
                y = r.nextInt(grid.getColumnCount());
            }
            Cell temp = grid.getCell(x, y);
            temp.setBomb(true);
            grid.setCell(x, y, temp);
            bombs[i] = temp;
        }

        //Making game playable and drawing the cells onscreen
        gamestate = Gamestates.PLAY;
        items[1].setEnabled(true);
        for (int i = 0; i < grid.getRowCount(); i++) for (int j = 0; j < grid.getColumnCount(); j++) gameFrame.draw(grid.getCell(i, j));
    }

    public void revealBombs() {
        for (int i = 0; i < bombCount; i++) {
            bombs[i].reveal(gameFrame, grid);
            gameFrame.draw(bombs[i]);
        }
        items[1].setEnabled(false);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        //Left click
        if (e.getButton() == MouseEvent.BUTTON1 && gamestate == Gamestates.PLAY) {
            for (int i = 0; i < grid.getRowCount(); i++) {
                for (int j = 0; j < grid.getColumnCount(); j++) {
                    if (grid.getCell(i, j).isClicked(e.getX(), e.getY())) {
                        grid.getCell(i, j).reveal(gameFrame, grid);
                        if (grid.getCell(i, j).isBomb()) {
                            revealBombs();
                            //TODO: Ending Lose
                            gameFrame.draw(new DrawableText((int)(gameFrame.getSize().width / 2.2), (int)(gameFrame.getSize().width / 2.8), "YOU LOSE!", Color.RED));
                            gamestate = Gamestates.END;
                        } else if (grid.allRevealed(bombCount)) {
                            //TODO: Ending Win
                            gameFrame.draw(new DrawableText((int)(gameFrame.getSize().width / 2.2), (int)(gameFrame.getSize().width / 2.8), "WINNER!", Color.RED));
                            gamestate = Gamestates.END;
                        }
                    }
                }
            }

            for (int i = 0; i < grid.getRowCount(); i++) for (int j = 0; j < grid.getColumnCount(); j++) gameFrame.draw(grid.getCell(i, j));
        }
        //Right click
        else if (e.getButton() == MouseEvent.BUTTON2 && gamestate == Gamestates.PLAY) {
            //TODO: Flags for bombs
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}



    private class MenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == items[0]) {

                setup(grid.getRowCount(), grid.getColumnCount(), bombCount, grid.getCellSize());

            } else if (e.getSource() == items[1]) {

                revealBombs();
                gamestate = Gamestates.END;

            } else if (e.getSource() == items[2]) {

                settingFrame = new SettingFrame("Settings");

                SettingInput settingRows = new SettingInput("Row count", "" + grid.getRowCount());
                SettingInput settingColumns = new SettingInput("Column count", "" + grid.getColumnCount());
                SettingInput settingBombs = new SettingInput("Bomb count", "" + bombCount);
                SettingInput settingCellSize = new SettingInput("Cell size", "" + grid.getCellSize());

                settingFrame.addInputSetting(settingRows);
                settingFrame.addInputSetting(settingColumns);
                settingFrame.addInputSetting(settingBombs);
                settingFrame.addInputSetting(settingCellSize);

                SubmitButton btn = new SubmitButton("Submit");

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rows, colums, bombs, size;

                        rows = settingRows.getIntInput();
                        if (rows <= 0) {
                            settingRows.setHint("" + grid.getRowCount());
                            rows = grid.getRowCount();
                        }

                        colums = settingColumns.getIntInput();
                        if (colums <= 0) {
                            settingColumns.setHint("" + grid.getColumnCount());
                            colums = grid.getColumnCount();
                        }

                        bombs = settingBombs.getIntInput();
                        if (bombs <= 0) {
                            settingBombs.setHint("" + bombCount);
                            bombs = bombCount;
                        } else if (bombs >= (rows * colums)) {
                            bombs = (rows * colums) - 1;
                            settingBombs.setHint("" + bombs);
                        }

                        size = settingCellSize.getIntInput();
                        if (size <= 0) {
                            settingCellSize.setHint("" + grid.getCellSize());
                            size = grid.getCellSize();
                        }

                        setup(rows, colums, bombs, size);
                    }
                });

                settingFrame.addSubmitButton(btn);

                settingFrame.setVisible(true);

            } else if (e.getSource() == items[3]) {

                aboutFrame = new DrawFrame("About", 225, 100);
                aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                aboutFrame.setVisible(true);

                aboutFrame.draw(new DrawableText(10, 15, "Cell"));
                aboutFrame.draw(new Cell(5, 30, 30, 30, 0, 0, false, Color.BLUE));

                aboutFrame.draw(new DrawableText(60, 15, "Revealed Cells"));
                aboutFrame.draw(new Cell(65, 30, 30, 30, 0, 0, true, Color.LIGHT_GRAY));
                aboutFrame.draw(new Cell(105, 30, 30, 30, 0, 0, true, Color.LIGHT_GRAY));
                aboutFrame.draw(new Cell(105, 30, 30, 30, 0, 0, true, Color.LIGHT_GRAY).getBombCountText());

                aboutFrame.draw(new DrawableText(155, 15, "Bomb Cell"));
                aboutFrame.draw(new Cell(170, 30, 30, 30, 0, 0, true, Color.DARK_GRAY));
                aboutFrame.draw(new Cell(170, 30, 30, 30, 0, 0, true, Color.DARK_GRAY).getBombCircle());

            }
        }
    }
}
