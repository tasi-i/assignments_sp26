## A0 Tests

| Test name | Setup (steps) | Operation | Expected result | Exception? |
|----------|---------------|-----------|-----------------|------------|
| size on empty list | Create empty list | size() | 0 | No |
| isEmpty on empty list | Create empty list | isEmpty() | true | No |
| add to empty list | Create empty list | add(0, "A") | list size becomes 1 | No |
| get first element | Create empty list → add(0, "A") | get(0) | "A" | No |
| get on empty list | Create empty list | get(0) | n/a | IndexOutOfBoundsException |
| add at invalid index | Create empty list | add(1, "A") | n/a | IndexOutOfBoundsException |
| remove only element | Create empty list → add(0, "A") | remove(0) | "A" | No |
| isEmpty after removal | Create empty list → add(0, "A") → remove(0) | isEmpty() | true | No |
| remove from empty list | Create empty list | remove(0) | n/a | IndexOutOfBoundsException |
| set element at valid index | Create empty list → add(0, "A") | set(0, "B") | "A" | No |
| set at invalid index | Create empty list → add(0, "A") | set(1, "B") | n/a | IndexOutOfBoundsException |
| get with negative index | Create empty list → add(0, "A") | get(-1) | n/a | IndexOutOfBoundsException |
| remove at size | Create empty list → add(0, "A") | remove(1) | n/a | IndexOutOfBoundsException |