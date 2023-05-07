package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class OwnBinarySearchTreeTester {

	private BinarySearchTree<Integer> e1;
	private BinarySearchTree<Integer> e2;
	private BinarySearchTree<String> e3;

	@BeforeEach
	void setUp() throws Exception { // skapar 3 BST
		e1 = new BinarySearchTree<Integer>(); // testar första konstruktorn
		e2 = new BinarySearchTree<Integer>((a, b) -> a - b); // testar andra konstruktorn med lambdauttryck
		e3 = new BinarySearchTree<String>(); // testar med annat element
	}

	@AfterEach
	void tearDown() throws Exception { // tömmer alla BST
		e1 = null;
		e2 = null;
		e3 = null;
	}

	@Test
	void testHeight() {

		assertEquals(0, e1.height(), "Ett tomt träd ska ha height 0");
		assertEquals(0, e2.height(), "Ett tomt träd ska ha size 0");
		assertEquals(0, e3.height(), "Ett tomt träd ska ha size 0");

		assertTrue(e1.add(10));
		assertTrue(e1.add(1));
		assertTrue(e1.add(3));
		assertTrue(e1.add(4));

		assertEquals(4, e1.height(), "Fel height");

		assertTrue(e2.add(4));
		assertTrue(e2.add(3));
		assertTrue(e2.add(1));
		assertTrue(e2.add(10));
		assertEquals(3, e2.height(), "Fel height");

		assertTrue(e3.add("hej"));
		assertFalse(e3.add("hej"));
		assertTrue(e3.add("hopp"));
		assertEquals(2, e3.height(), "Fel height");
	}

	@Test
	void testSizeandAdd() {

		assertEquals(0, e1.size(), "Ett tomt träd ska ha size 0");
		assertEquals(0, e2.size(), "Ett tomt träd ska ha size 0");
		assertEquals(0, e3.size(), "Ett tomt träd ska ha size 0");

		assertTrue(e1.add(1));
		assertTrue(e1.add(2));
		assertFalse(e1.add(1), "Du har satt in en dublett");

		assertTrue(e2.add(3));
		assertFalse(e2.add(3));

		assertTrue(e3.add("hej"));
		assertFalse(e3.add("hej"), "Du har satt in en dublett");
		assertTrue(e3.add("hopp"));

		assertEquals(2, e1.size(), "Fel storlek på trädet");
		assertEquals(1, e2.size(), "Fel storlek på trädet");
		assertEquals(2, e3.size(), "Fel storlek på trädet");

	}

	@Test
	void testClear() {
		assertTrue(e1.add(10));
		assertTrue(e2.add(1));
		assertTrue(e2.add(3));
		assertTrue(e3.add("hej"));
		
		e1.clear();
		e2.clear();
		e3.clear();
		
		assertEquals(0, e1.size(), "Trädet är inte tomt");
		assertEquals(0, e2.size(), "Trädet är inte tomt");
		assertEquals(0, e3.size(), "Trädet är inte tomt");

	}

}
