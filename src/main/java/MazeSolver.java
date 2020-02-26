import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MazeSolver {

    private static int[][] maze = new int[][]{
            {2, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0 ,1, 0, 1},
            {1, 0, 0, 0, 1},
            {1,1 , 1, 1, 1}
    };

    private static Position currentPosition;
    private static Stack visitedPositions;

    public static void main(String[] args) {
        currentPosition = new Position(2,4);
        visitedPositions = new Stack<Position>();

        if (solveMaze(maze)) {
            System.out.println("Valid Maze!!");
        } else {
            System.out.println("Invalid Maze");
        }
    }

    private static boolean solveMaze(int[][] maze) {
        visitedPositions.push(currentPosition);
        System.out.println("Starting position: " + String.valueOf(currentPosition.getX()) + ", "+ String.valueOf(currentPosition.getY()));
        move();
        return true;
    }

    private static void move() {
        Position previousPosition  = null;
        if (!visitedPositions.empty()) {
            previousPosition = (Position) visitedPositions.peek();
        }

        System.out.println("Current position: " + String.valueOf(currentPosition.getX()) + ", "+ String.valueOf(currentPosition.getY()));
        switch (nextPosition(moveLeft())) {
            case 1:
                if (previousPosition.getY() != moveLeft().getY()) {
                    System.out.println("You moving left!");
                    visitedPositions.push(currentPosition);
                    currentPosition = moveLeft();
                    move();
                } else {
                    break;
                }
            case 2:
                return;
            case 0:
                break;
        }

        switch (nextPosition(moveUp())) {
            case 1:
                if (previousPosition.getX() != moveUp().getX()) {
                    System.out.println("You moving up!");
                    visitedPositions.push(currentPosition);
                    currentPosition = moveUp();
                    move();
                }
                else {
                    break;
                }

            case 2:
                return;
            case 0:
                break;
        }

        switch (nextPosition(moveRight())) {
            case 1:
                if (previousPosition.getY() != moveRight().getY()) {
                    System.out.println("You moving right!");
                    visitedPositions.push(currentPosition);
                    currentPosition = moveRight();
                    move();
                } else {
                    break;
                }

            case 2:
                return;
            case 0:
                break;
        }

        switch (nextPosition(moveDown())) {
            case 1:
                if (previousPosition.getX() != moveDown().getX()) {
                    System.out.println("You moving down!");
                    visitedPositions.push(currentPosition);
                    currentPosition = moveDown();
                    move();
                } else {
                    break;
                }
            case 2:
                return;
            case 0:
                break;
        }

        deadEnd(previousPosition);


    }

    private static void deadEnd(Position previousPosition) {
        System.out.println("Found a dead end, returning back");
        if (!visitedPositions.empty() ) {
            maze[currentPosition.getX()][currentPosition.getY()] = 0;
            visitedPositions.pop();
            currentPosition = previousPosition;
            move();
        } else {
            System.out.println("wrongggg");
        }
    }

    private static int nextPosition(Position position) {
        if (position.getX() > maze[0].length -1 || position.getX() == -1) {
            return 0;
        }
        if (position.getY() > maze[0].length -1 || position.getY() == -1) {
            return 0;
        }
        return maze[position.getX()][position.getY()];
    }

    private static Position moveLeft() {
        int newPosition =  currentPosition.getY() -1;
        return new Position(currentPosition.getX(),  newPosition);
    }
    private static Position moveRight() {
        int newPosition =  currentPosition.getY()  + 1 ;
        return new Position(currentPosition.getX(), newPosition);
    }
    private static Position moveUp() {
        int newPosition =  currentPosition.getX() - 1;
        return new Position(newPosition, currentPosition.getY());
    }
    private static Position moveDown() {
        int newPosition =  currentPosition.getX() + 1;
        return new Position(newPosition, currentPosition.getY() );
    }

}
