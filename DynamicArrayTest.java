jaimport org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTest {

    private DynamicArray<Character> a1, a2, empty, s;

    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    private DynamicArray<Character> stringToArray(String str) {
        DynamicArray<Character> arr = new DynamicArray<>(str.length());
        for (int i = 0; i < str.length(); i++) arr.add(str.charAt(i));
        return arr;
    }

    private void compareToString(DynamicArray<Character> arr, String str) {
        assertEquals(str.length(), arr.size());
        for (int i = 0; i < str.length(); i++)
            assertEquals(str.charAt(i), (char) arr.get(i));
    }

    // ---------- Basic Tests ----------
    @Test
    public void testSizeAndEmpty() {
        DynamicArray<Character> arr = new DynamicArray<>();
        assertEquals(0, arr.size());
        assertTrue(arr.isEmpty());
        arr.add('A');
        assertEquals(1, arr.size());
        assertFalse(arr.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmpty() { new DynamicArray<Character>().get(0); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInvalid() { new DynamicArray<Character>().add(1, 'A'); }

    @Test
    public void testAddAndGet() {
        DynamicArray<Character> arr = new DynamicArray<>();
        arr.add('A'); arr.add('B'); arr.add(1, 'C');
        compareToString(arr, "ACB");
    }

    @Test
    public void testSet() {
        DynamicArray<Character> arr = stringToArray("AB");
        char old = arr.set(1, 'Z');
        assertEquals('B', old);
        compareToString(arr, "AZ");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetInvalid() { new DynamicArray<Character>().set(0, 'X'); }

    @Test
    public void testRemove() {
        DynamicArray<Character> arr = stringToArray("ABC");
        char removed = arr.remove(1);
        assertEquals('B', removed);
        compareToString(arr, "AC");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalid() { new DynamicArray<Character>().remove(0); }

    // ---------- Functional Tests ----------
    @Test
    public void testAppend() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(a1.append(empty), "abcdef");
    }

    @Test
    public void testAddAll() {
        compareToString(a1.addAll(0, a2), "wxyzabcdef");
        compareToString(a1.addAll(3, a2), "abcwxydef");
    }

    @Test
    public void testSplitPrefixSuffix() {
        compareToString(a1.splitPrefix(3), "abc");
        compareToString(a1.splitSuffix(3), "def");
    }

    @Test
    public void testDeleteExtract() {
        compareToString(a1.delete(1, 4), "af");
        compareToString(a1.extract(1, 4), "bcd");
    }
}