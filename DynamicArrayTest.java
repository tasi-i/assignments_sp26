import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the DynamicArray class.
 * 
 * Tests cover:
 * - Basic operations: size, isEmpty, get, set, add, remove
 * - Index out-of-bounds behavior
 * - Functional-style operations: append, addAll, splitSuffix, splitPrefix, delete, extract
 *
 * @param <T> type of elements in DynamicArray (for internal use, mostly String or Integer in tests)
 */
public class DynamicArrayTest {

    private DynamicArray<String> emptyArray;
    private DynamicArray<String> filledArray;

    /**
     * Setup method run before each test.
     * Initializes empty and pre-filled DynamicArrays.
     */
    @Before
    public void setUp() {
        emptyArray = new DynamicArray<>();
        filledArray = new DynamicArray<>();
        filledArray.add("A");
        filledArray.add("B");
        filledArray.add("C");
    }

    /**
     * Tests that a newly created array is empty.
     */
    @Test
    public void testEmptyArray() {
        assertTrue(emptyArray.isEmpty());
        assertEquals(0, emptyArray.size());
    }

    /**
     * Tests adding elements at specific indices.
     */
    @Test
    public void testAddAndGet() {
        emptyArray.add(0, "X");
        assertEquals("X", emptyArray.get(0));
        emptyArray.add("Y"); // append
        assertEquals("Y", emptyArray.get(1));
    }

    /**
     * Tests removing elements from the array.
     */
    @Test
    public void testRemove() {
        String removed = filledArray.remove(1); // removes "B"
        assertEquals("B", removed);
        assertEquals(2, filledArray.size());
        assertEquals("C", filledArray.get(1));
    }

    /**
     * Tests set operation returns old value and updates element.
     */
    @Test
    public void testSet() {
        String old = filledArray.set(0, "Z");
        assertEquals("A", old);
        assertEquals("Z", filledArray.get(0));
    }

    /**
     * Tests that operations throw IndexOutOfBoundsException for invalid indices.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        emptyArray.get(0);
    }

    /**
     * Tests append method in a functional style (returns new array, does not modify original).
     */
    @Test
    public void testAppend() {
        DynamicArray<String> result = filledArray.append(emptyArray);
        assertEquals(3, filledArray.size()); // original unchanged
        assertEquals(3, result.size());
    }

    /**
     * Tests addAll inserts elements at specified index.
     */
    @Test
    public void testAddAll() {
        DynamicArray<String> other = new DynamicArray<>();
        other.add("X");
        other.add("Y");
        DynamicArray<String> result = filledArray.addAll(1, other);
        assertEquals(5, result.size());
        assertEquals("X", result.get(1));
    }

    /**
     * Tests splitSuffix returns elements from given index to end.
     */
    @Test
    public void testSplitSuffix() {
        DynamicArray<String> suffix = filledArray.splitSuffix(1);
        assertEquals(2, suffix.size());
        assertEquals("B", suffix.get(0));
    }

    /**
     * Tests splitPrefix returns elements before a given index.
     */
    @Test
    public void testSplitPrefix() {
        DynamicArray<String> prefix = filledArray.splitPrefix(2);
        assertEquals(2, prefix.size());
        assertEquals("A", prefix.get(0));
    }

    /**
     * Tests delete removes a range of elements.
     */
    @Test
    public void testDelete() {
        DynamicArray<String> result = filledArray.delete(0, 2);
        assertEquals(1, result.size());
        assertEquals("C", result.get(0));
    }

    /**
     * Tests extract returns a range of elements without modifying original.
     */
    @Test
    public void testExtract() {
        DynamicArray<String> result = filledArray.extract(1, 3);
        assertEquals(2, result.size());
        assertEquals("B", result.get(0));
    }
}