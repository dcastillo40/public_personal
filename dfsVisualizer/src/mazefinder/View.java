package mazefinder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class View extends JFrame {
    // Maze
    private int[][] maze = 
    { {1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,1,0,1,0,1,0,0,0,0,0,1},
    {1,0,1,0,0,0,1,0,1,1,1,0,1},
    {1,0,0,0,1,1,1,0,0,0,0,0,1},
    {1,0,1,0,0,0,0,0,1,1,1,0,1},
    {1,0,1,0,1,1,1,0,1,0,0,0,1},
    {1,0,1,0,1,0,0,0,1,1,1,0,1},
    {1,0,1,0,1,1,1,0,1,0,1,0,1},
    {1,0,0,0,0,0,0,0,0,0,1,9,1}, // 9 = target position
    {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private final ArrayList<Integer> path = new ArrayList<Integer>();
    private int pathIndex;

    public View() {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DepthFirst.searchPath(maze, 1, 1, path);
        pathIndex = path.size() - 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);

        for(int row = 0; row < maze.length; row++) {
            for(int col = 0; col < maze[0].length; col++) {
                Color color;

                switch(maze[row][col]) {
                    case 1 : color = Color.BLACK;
                    break;
                    case 9 : color = Color.RED;
                    break;
                    default : color = Color.WHITE;
                }

                g.setColor(Color.BLACK);
                g.fillRect(30 * col, 30 * row, 30, 30);

                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 28, 28);
            }
        }

        for(int p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);

            g.setColor(Color.GREEN);
            g.fillRect(pathX * 30, pathY * 30, 28, 28);
        }

        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);

        g.setColor(Color.RED);
        g.fillOval(pathX * 30, pathY * 30, 30, 30);
    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        if(ke.getID() != KeyEvent.KEY_PRESSED) {
            return ;
        }

        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            pathIndex -= 2;

            if(pathIndex < 0) {
                pathIndex = 0;
            } 
        } else {
            if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                pathIndex += 2;

                if(pathIndex > path.size() - 2) {
                    pathIndex = path.size() - 2;
                }
            }
        }

        repaint();
    }


    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();

                view.setVisible(true);
            }
        });
        
    }
}
