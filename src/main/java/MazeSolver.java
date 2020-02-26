import java.util.Stack;

public class MazeSolver {
    private int[][] maze = new int[][]{
            {2, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0 ,1, 0, 1},
            {1, 0, 0, 0, 1},
            {1,1 , 1, 1, 1}
    };

    private Position currentPosition;
    private Stack visitedPositions;
    private boolean mazeSolved;

    public MazeSolver(int[][] maze, Position startingPosition) {
        this.maze = maze;
        currentPosition = startingPosition;
        visitedPositions = new Stack<Position>();
        visitedPositions.push(currentPosition);
    }

    void solveMaze() {
        Position previousPosition  = null;
        if (!visitedPositions.empty()) {
            previousPosition = (Position) visitedPositions.peek();
        }

        System.out.println("Current position: " + String.valueOf(currentPosition.getX()) + ", "+ String.valueOf(currentPosition.getY()));
        while (!mazeSolved) {
            moveLeft(previousPosition);
            moveUp(previousPosition); //
            moveRight(previousPosition);
            moveDown(previousPosition);
            deadEnd(previousPosition);
        }

        System.out.println("Valid Maze!!");
        setMazeSolved();
    }

    private void moveDown(Position previousPosition) {
        switch (mazeValue(downPosition())) {
            case 1:
                if (previousPosition.getX() != downPosition().getX()) {
                    System.out.println("You moving down!");
                    visitedPositions.push(currentPosition);
                    currentPosition = downPosition();
                    solveMaze();
                } else {
                    break;
                }
            case 2:
                mazeSolved = true;
                solveMaze();
            case 0:
                break;
        }
    }

    private void moveRight(Position previousPosition) {
        switch (mazeValue(rightPosition())) {
            case 1:
                if (previousPosition.getY() != rightPosition().getY()) {
                    System.out.println("You moving right!");
                    visitedPositions.push(currentPosition);
                    currentPosition = rightPosition();
                    solveMaze();
                } else {
                    break;
                }
            case 2:
                mazeSolved = true;
                solveMaze();
            case 0:
                break;
        }
    }

    private void moveUp(Position previousPosition) {
        switch (mazeValue(upPosition())) {
            case 1:
                if (previousPosition.getX() != upPosition().getX()) {
                    System.out.println("You moving up!");
                    visitedPositions.push(currentPosition);
                    currentPosition = upPosition();
                    solveMaze();
                }
                else {
                    break;
                }
            case 2:
                mazeSolved = true;
                solveMaze();
            case 0:
                break;
        }
    }

    private void moveLeft(Position previousPosition) {
        switch (mazeValue(leftPosition())) {
            case 1:
                if (previousPosition.getY() != leftPosition().getY()) {
                    System.out.println("You moving left!");
                    visitedPositions.push(currentPosition);
                    currentPosition = leftPosition();
                    solveMaze();
                } else {
                    break;
                }
            case 2:
                mazeSolved = true;
                solveMaze();
            case 0:
                break;
        }
    }

    private void deadEnd(Position previousPosition) {
        System.out.println("Found a dead end, returning back");
        if (!visitedPositions.empty() ) {
            maze[currentPosition.getX()][currentPosition.getY()] = 0;
            visitedPositions.pop();
            currentPosition = previousPosition;
            solveMaze();
        } else {

            System.out.println("Maze unsolvable");
            setMazeSolved();
        }
    }

    private int mazeValue(Position position) {
        if (position.getX() > maze[0].length -1 || position.getX() == -1) {
            return 0;
        }
        if (position.getY() > maze[0].length -1 || position.getY() == -1) {
            return 0;
        }
        return maze[position.getX()][position.getY()];
    }

    private Position leftPosition() {
        int newPosition =  currentPosition.getY() -1;
        return new Position(currentPosition.getX(),  newPosition);
    }
    private Position rightPosition() {
        int newPosition =  currentPosition.getY()  + 1 ;
        return new Position(currentPosition.getX(), newPosition);
    }
    private Position upPosition() {
        int newPosition =  currentPosition.getX() - 1;
        return new Position(newPosition, currentPosition.getY());
    }
    private Position downPosition() {
        int newPosition =  currentPosition.getX() + 1;
        return new Position(newPosition, currentPosition.getY() );
    }

    private void setMazeSolved() {
        System.exit(0);
    }
}
