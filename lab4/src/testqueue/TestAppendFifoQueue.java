package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {

	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	void testTwoEmpty() {
		q1.append(q2);
		assertTrue(q1.size() == 0, "Två ihoplagda tomma köer blir inte tomma");
	}

	@Test
	void testOneEmptyA() {
		q1.offer(10);
		q1.offer(20);

		q1.append(q2);
		assertTrue(q1.size() == 2, "Köns storlek blir inkorrekt");
		assertEquals(q1.poll(), 10, "Objekten hamnar inte i rätt ordning");
		assertEquals(q1.poll(), 20, "Objekten hamnar inte i rätt ordning");
	}

	@Test
	void testOneEmptyB() {
		q1.offer(10);
		q1.offer(20);

		q2.append(q1);
		assertTrue(q2.size() == 2, "Köns storlek blir inkorrekt");
		assertEquals(q2.poll(), 10, "Objekten hamnar inte i rätt ordning");
		assertEquals(q2.poll(), 20, "Objekten hamnar inte i rätt ordning");
		assertEquals(q1.size(), 0, "Q1 blir ej tom");
	}

	@Test
	void testTwoNonEmpty() {
		q1.offer(30);
		q2.offer(20);
		q1.offer(10); // q1 : 30 , 10; q2: 20

		q1.append(q2);
		assertTrue(q1.size() == 3, "Köns storlek blir inkorrekt");
		assertEquals(q1.poll(), 30, "Objekten hamnar inte i rätt ordning");
		assertEquals(q1.poll(), 10, "Objekten hamnar inte i rätt ordning");
		assertEquals(q1.peek(), 20, "Objekten hamnar inte i rätt ordning");
		assertEquals(q2.size(), 0, "Q2 blir ej tom");
	}

	// @Test
	void testSelfAppend() {

		assertThrows(IllegalArgumentException.class, () -> q1.append(q1), "Förväntade sig IllegalArgumentException");

	}

}
