package OOP_Labs.logistics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomBinaryTreeTest {

    @Test
    void toSortedListReturnsAscendingOrder() {
        CustomBinaryTree<Integer> tree = new CustomBinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(9);
        tree.add(1);

        List<Integer> sorted = tree.toSortedList();

        assertEquals(List.of(1, 2, 5, 9), sorted);
    }

    @Test
    void removeDeletesElementAndKeepsOrder() {
        CustomBinaryTree<Integer> tree = new CustomBinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(9);
        tree.add(1);
        tree.add(3);

        tree.remove(2);

        assertEquals(List.of(1, 3, 5, 9), tree.toSortedList());
    }

    @Test
    void isEmptyReflectsState() {
        CustomBinaryTree<Integer> tree = new CustomBinaryTree<>();
        assertTrue(tree.isEmpty());

        tree.add(1);
        assertFalse(tree.isEmpty());
    }
}

