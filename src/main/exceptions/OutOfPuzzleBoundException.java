package main.exceptions;

/**
 * Puzzle Exception
 */
public class OutOfPuzzleBoundException extends RuntimeException {
    /**
     * Constructor
     */
    public OutOfPuzzleBoundException() {
        super("Moving is impossible");
    }
}