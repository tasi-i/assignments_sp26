/**
 * DynamicArray implements resizable array based list similar to the ArrayList
 *
 * @param <T> the type of elements stored in this array
 */
public class DynamicArray<T> implements ListADT<T> {

    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    // - Constructors -
    public DynamicArray(int capacity) {
        data = makeArray(capacity);
        size = 0;
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(DynamicArray<T> other) {
        data = makeArray(other.size);
        for (int i = 0; i < other.size; i++) data[i] = other.data[i];
        size = other.size;
    }

    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    // helper
    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) return;
        int newCapacity = Math.max(minCapacity, data.length * 2);
        T[] newData = makeArray(newCapacity);
        for (int i = 0; i < size; i++) newData[i] = data[i];
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for add with size " + size);
    }

    private void shiftRight(int index) {
        for (int i = size; i > index; i--) data[i] = data[i - 1];
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        data[size - 1] = null;
    }

    //  ListADT Methods 
    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T old = data[index];
        data[index] = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        shiftRight(index);
        data[index] = element;
        size++;
    }

    public void add(T element) { add(size, element); }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = data[index];
        shiftLeft(index);
        size--;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Functional / Group 3 
    public DynamicArray<T> append(DynamicArray<T> other) {
        DynamicArray<T> result = new DynamicArray<>(size + other.size);
        for (int i = 0; i < size; i++) result.add(data[i]);
        for (int i = 0; i < other.size; i++) result.add(other.data[i]);
        return result;
    }

    public DynamicArray<T> addAll(int index, DynamicArray<T> other) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(size + other.size);
        for (int i = 0; i < index; i++) result.add(data[i]);
        for (int i = 0; i < other.size; i++) result.add(other.data[i]);
        for (int i = index; i < size; i++) result.add(data[i]);
        return result;
    }

    public DynamicArray<T> splitSuffix(int fromIndex) {
        if (fromIndex < 0 || fromIndex > size) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(size - fromIndex);
        for (int i = fromIndex; i < size; i++) result.add(data[i]);
        return result;
    }

    public DynamicArray<T> splitPrefix(int toIndex) {
        if (toIndex < 0 || toIndex > size) throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(toIndex);
        for (int i = 0; i < toIndex; i++) result.add(data[i]);
        return result;
    }

    public DynamicArray<T> delete(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(size - (toIndex - fromIndex));
        for (int i = 0; i < fromIndex; i++) result.add(data[i]);
        for (int i = toIndex; i < size; i++) result.add(data[i]);
        return result;
    }

    public DynamicArray<T> extract(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        DynamicArray<T> result = new DynamicArray<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) result.add(data[i]);
        return result;
    }
}