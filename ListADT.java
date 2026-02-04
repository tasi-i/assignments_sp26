/**
 * A ListADT represents an ordered, resizable sequence of elements
 * Elements are arranged by position and numbered starting at 0.
 *
 * A newly created ListADT starts empty with size of 0. The list can
 * grow and shrink as elements are added or removed.
 *
 * This interface specifies behavior only, with no assumptions
 * about the underlying implementation.
 *
 * @param <E> the type of elements stored in the list
 */
public interface ListADT<E> {

    /**
     * Returns the number of elements currently in the list
     *
     * @return the size of the list
     */
    int size();

    /**
     * Determines whether the list contains no elements.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the element stored at the specified position
     *
     * @param index the position of the element to return
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index < 0 or >= size()
     */
    E get(int index);

    /**
     * Replaces the element at the specified position with a new element
     *
     * @param index the position of the element to replace
     * @param element the element to store at the given position
     * @return the element previously stored at the given index
     * @throws IndexOutOfBoundsException if index < 0 or >= size()
     */
    E set(int index, E element);

    /**
     * Inserts an element at the specified position in the list.
     * Elements at and after the index are shifted to the right.
     *
     * @param index the position at which to insert the element
     * @param element the element to add
     * @throws IndexOutOfBoundsException if index < 0 or > size()
     */
    void add(int index, E element);

    /**
     * Removes and returns the element at the specified position.
     * Elements after the removed element are shifted to the left.
     *
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index < 0 or >= size()
     */
    E remove(int index);
}