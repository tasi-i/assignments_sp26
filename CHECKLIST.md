Assignment 1 Checklist | CSC 210
Listed below are various aspects of the assignment. When you turn in your work, please indicate the status of each item

YES: indicates that the item is fully complete
NO: indicates that the item is not attempted
PART: indicates that the item is attempted but not fully complete
Grade-ability Check
Please confirm the following minimum criteria are met:

__yes___ Program compiles without errors

___yes__ All required files included with submission (including basic readme info and completed checklist file)

___yes__ README.md contains answers to any questions and your reflection on the assignment
y
Assignments that do not meet the above criteria cannot be graded

Coding Points (13 pts):
___yes__ 1 pt: ListADT (from A0) included and includes all specified methods

___yes__ 1 pt: DynamicArray method call signatures correctly implement ListADT

___yes__ 1 pt: Uses a backing array (T[] data) + a size field (int size)

___yes__ 1 pt: Maintains invariant: 0 ≤ size ≤ data.length

___yes__ 1 pt: Logical index i maps to backing array index i for 0 ≤ i < size and items beyond size are not incorporated into operations

____yes_ 1 pt: get(i) returns element at logical index i

__yes___ 1 pt: set(i,x) updates element at logical index i

__yes___ 1 pt: add(i,x) inserts at logical index i (shifts right)

__yes___ 1 pt: remove(i) removes logical index i (shifts left)

____yes_ 1 pt: Bounds errors throw an appropriate unchecked exception (e.g., IndexOutOfBoundsException)

____yes_ 1 pt: No checked exceptions used for normal ADT misuse

___yes__ 1 pt: Adds that would make size > data.length trigger a resize

___yes__ 1 pt: Resize correctly allocates a new array and copies existing elements in the correct order

Code Hygiene (4 pts):
____yes 1 pt: No copy/paste near-duplicate code blocks for the same behavior (reusing your code is better for everyone!)

__yes___ 1 pt: Common logic is factored into helpers (e.g., checkIndex, resizeIfNeeded, shiftLeft/Right)

__yes___ 1 pt: Methods are short enough to read (no 100-line monster methods unless justified)

__yes___ 1 pt: Names communicate intent (especially for helper methods)

General Items (6 pts):
___yes__ 1 pt: Student-written code compiles without warnings that indicate correctness problems

____yes_ 2 pts: Student-provided code runs and executes without unexpected crashing

____yes_ 2 pt: Javadoc builds without errors/warnings

____yes_ 1 pt: Indentation and other style norms are followed