package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {

		QueueNode<E> n = new QueueNode<E>(e);

		// två fall, tom/ej tom lista
		if (size == 0) {
			// nod som pekar på sig själv som next
			n.next = n;

		} else {
			// n pekar på första obj i listan som nästa
			n.next = last.next;
			// föregående sista pekar på n som nästa
			last.next = n;

		}
		// sätter n sist i list
		last = n;

		// ökar storleken med 1
		size++;

		// alltid true enligt labbhandledning
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		// om kön är tom
		if (size == 0) {
			return null;
		}
		// första elementet
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		// om kön är tom
		if (size == 0) {
			return null;

		}

		E first = last.next.element;
		last.next = last.next.next;
		size--;

		// om listan tom efter borttagning -> återställer listan
		if (size == 0) {
			last = null;
		}

		return first;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (this == q) {
			throw new IllegalArgumentException();
		}

		// om listan är tom -> last får ett värde
		if (size == 0) {
			last = q.last;

		} else if (q.size() == 0) { // om q är tom händer ingenting

		} else { // om det finns objekt i båda listorna
			QueueNode<E> firstQ = q.last.next;
			QueueNode<E> lastQ = q.last;

			// hänger på q-listan på originallistan
			lastQ.next = last.next;
			last.next = firstQ;
			last = lastQ;

		}
		// ökar storlek
		size += q.size;

		// tömmer q
		q.size = 0;
		q.last = null;

	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {

		private QueueNode<E> pos;

		/* Konstruktor */
		private QueueIterator() {

			if (size == 0) {
				pos = null;
			} else {
				pos = last.next;
			}
		}

		public boolean hasNext() {

			return pos != null;
		}

		public E next() {

			// felmeddelande
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			E temp = pos.element;

			// inte loopa
			if (pos == last) {
				pos = null;
			} else {
				pos = pos.next;
			}

			return temp;

		}
	}

}
