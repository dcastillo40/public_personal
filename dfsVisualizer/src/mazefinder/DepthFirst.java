package mazefinder;

import java.util.*;

public class DepthFirst {
    
    // x and y is the starting position
    public static boolean searchPath(int[][] maze, int x, int y, List<Integer> path) {

        // check if target was reached
        if(maze[y][x] == 9) {
            path.add(x);
            path.add(y);

            return true;
        }

        // mark 2 in the maze to indicate it was visited
        if(maze[y][x] == 0) {
            maze[y][x] = 2;

            int dx = -1;
            int dy = 0;

            if(searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);

                return true;
            }

            dx = 1;
            dy = 0;

            if(searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);

                return true;
            }

            dx = 0;
            dy = -1;

            if(searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);

                return true;
            }

            dx = 0;
            dy = 1;

            if(searchPath(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);

                return true;
            }
        }

        return false;
    }
}
