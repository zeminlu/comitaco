package roops.core.objects;

import static junit.framework.Assert.*;

import org.junit.Test;

public class SinglyLinkedListTest {

	@Test
    public void testContainsEmpty() {
		SinglyLinkedList l = new SinglyLinkedList();
		assertFalse(l.contains(1));
		assertFalse(l.contains(null));
	}
	
	@Test
    public void testContains_1() {
		SinglyLinkedList l = new SinglyLinkedList();
		l.insertBack(1, new SinglyLinkedListNode());
		assertTrue(l.contains(1));
		assertFalse(l.contains(null));
	}

	@Test
    public void testContains_2() {
		SinglyLinkedList l = new SinglyLinkedList();
		l.insertBack(1, new SinglyLinkedListNode());
		l.insertBack(2, new SinglyLinkedListNode());
		l.insertBack(3, new SinglyLinkedListNode());
		assertTrue(l.contains(1));
		assertTrue(l.contains(3));
		assertTrue(l.contains(2));
		assertFalse(l.contains(null));
	}


	@Test
    public void testContains_null() {
		SinglyLinkedList l = new SinglyLinkedList();
		l.insertBack(null, new SinglyLinkedListNode());
		assertTrue(l.contains(null));
	}
}
