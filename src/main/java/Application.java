import java.util.Stack;

public class Application {
    private static int[][] maze = new int[][]{
            {2, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0 ,1, 0, 1},
            {1, 0, 0, 0, 1},
            {1,1 , 1, 1, 1}
    };

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver(maze,new Position(2,4));
        mazeSolver.solveMaze();
    }

}
