/**
 * The Counter class represents a simple counter that can be incremented or decremented.
 * It stores an integer value and provides methods to modify and retrieve the value.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Counter {
    private int counter;
    /**
     * Constructs a new Counter with an initial value of 0.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    void increase(int number) {
        this.counter = this.counter + number;
    }
    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    void decrease(int number) {
        this.counter = this.counter - number;
    }
    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    int getValue() {
        return this.counter;
    }
}