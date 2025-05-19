package Task10_2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayTestTest {

    private ArrayTest arrayTest = new ArrayTest();

    @Test
    public void testFindAverage() {
        int[] numbers = { 5, 1, 9, 2, 8 };
        double expected = 5.0;
        double actual = arrayTest.find(0, numbers);
        assertEquals("Average: ", expected, actual, 0.001);
    }

    @Test
    public void testFindMinimum() {
        int[] numbers = { 5, 1, 9, 2, 8 };
        double expected = 1.0;
        double actual = arrayTest.find(1, numbers);
        assertEquals("Minimum value: ", expected, actual, 0.001);
    }

    @Test
    public void testFindMaximum() {
        int[] numbers = { 5, 1, 9, 2, 8 };
        double expected = 9.0;
        double actual = arrayTest.find(2, numbers);
        assertEquals("Maximum value:", expected, actual, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIndex() {
        int[] numbers = { 5, 1, 9, 2, 8 };
        arrayTest.find(-1, numbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArray() {
        arrayTest.find(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortArrayInvalid() {
        int[] numbers = { 5 };
        arrayTest.find(0, numbers);
    }

    @Test
    public void testIndexAboveTwo() {
        int[] numbers = { 1, 2, 3, 4, 5 };
        double expected = 0.0;
        double actual = arrayTest.find(3, numbers);
        assertEquals("Index greater than 2: ", expected, actual, 0.001);
    }
}