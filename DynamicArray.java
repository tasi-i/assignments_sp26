/**
 * A DynamicArray is a resizable array-backed implementation of a list.
 * Supports typical list operations such as get, set, add, remove, and size.
 *
 * @param <T> the type of elements stored in this array
 */
public class DynamicArray<T> implements ListADT<T> {

    /** The backing array storing elements */
    private T[] data;

    /** logical number of elements in the array */
    private int size;

    /** Default initial capacity for the array */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs a DynamicArray with default capacity.
     */
    public DynamicArray() {
        this.data = makeArray(DEFAULT_CAPACITY);
        this.size = 0;
    }

    /**
     * Constructs a DynamicArray with a specified initial capacity.
     *
     * @param capacity the initial capacity of the array
     */
    public DynamicArray(int capacity) {
        this.data = makeArray(capacity);
        this.size = 0;
    }

    /**
     * creates a deep copy of another DynamicArray.
     *
     * @param other the DynamicArray to copy
     */
    public DynamicArray(DynamicArray<T> other) {
        this.data = makeArray(other.data.length);
        this.size = other.size;
        for (int i = 0; i < size; i++) {
            this.data[i] = other.data[i];
        }
    }

    /**
     * Creates a new generic array of the given capacity.
     *
     * @param capacity the capacity of the new array
     * @return a new array of type T[]
     */
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    /**
     * Returns the number of elements in the array
     *
     * @return the size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks whether the array is empty
     *
     * @return true if array is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the element at a given index.
     *
     * @param index the index to retrieve
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index &lt; 0 or &gt;= size
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Replaces the element at a given index and returns the old value
     *
     * @param index the index to set
     * @param value the new value to store
     * @return the previous value at the index
     * @throws IndexOutOfBoundsException if index &lt; 0 or &gt;= size
     */
    @Override
    public T set(int index, T value) {
        checkIndex(index);
        T old = data[index];
        data[index] = value;
        return old;
    }

    /**
     * Inserts a value at the given index, shifting elements to the right.
     *
     * @param index the index to insert at
     * @param value the value to insert
     * @throws IndexOutOfBoundsException if index &lt; 0 or &gt; size
     */
    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        resizeIfNeeded();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    /**
     * Appends a value to the end of the array.
     *
     * @param value the value to append
     */
    public void add(T value) {
        add(size, value);
    }

    /**
     * Removes the element at a given index, shifting elements left.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index &lt; 0 or &gt;= size
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Returns a string representation of the array in [a, b, c] format.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        return sb.toString();
    }

    /**
     * Ensures there is enough capacity for a new element, resizing if necessary.
     */
    private void resizeIfNeeded() {
        if (size == data.length) {
            T[] newData = makeArray(data.length * 2 + 1);
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    /**
     * Checks if an index is within bounds for get, set, remove.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index &lt; 0 or &gt;= size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Group 3 methods (functional-style) — append, addAll, splitSuffix, splitPrefix, delete, extract
    /** Returns a new DynamicArray that appends another array to this one */
    public DynamicArray<T> append(DynamicArray<T> other) {
        DynamicArray<T> result = new DynamicArray<>(this.size + other.size);
        for (int i = 0; i < size; i++) result.add(data[i]);
        for (int i = 0; i < other.size; i++) result.add(other.data[i]);
        return result;
    }

    /** Returns a new DynamicArray that inserts another array at index */
    public DynamicArray<T> addAll(int index, DynamicArray<T> other) {
        DynamicArray<T> result = new DynamicArray<>(this.size + other.size);
        for (int i = 0; i < index; i++) result.add(data[i]);
        for (int i = 0; i < other.size; i++) result.add(other.data[i]);
        for (int i = index; i < size; i++) result.add(data[i]);
        return result;
    }

    /** Returns a new DynamicArray containing elements from index to end */
    public DynamicArray<T> splitSuffix(int fromIndex) {
        checkIndex(fromIndex);
        DynamicArray<T> result = new DynamicArray<>(size - fromIndex);
        for (int i = fromIndex; i < size; i++) result.add(data[i]);
        return result;
    }

    /** Returns a new DynamicArray containing elements from start to index (exclusive) */
    public DynamicArray<T> splitPrefix(int toIndex) {
        if (toIndex < 0 || toIndex > size) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(toIndex);
        for (int i = 0; i < toIndex; i++) result.add(data[i]);
        return result;
    }

    /** Returns a new DynamicArray with elements [fromIndex, toIndex) removed */
    public DynamicArray<T> delete(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(size - (toIndex - fromIndex));
        for (int i = 0; i < fromIndex; i++) result.add(data[i]);
        for (int i = toIndex; i < size; i++) result.add(data[i]);
        return result;
    }

    /** Returns a new DynamicArray containing elements [fromIndex, toIndex) */
    public DynamicArray<T> extract(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) result.add(data[i]);
        return result;
    }
}