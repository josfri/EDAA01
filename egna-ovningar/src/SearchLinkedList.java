public class SearchLinkedList<E> {
	private Node<E> first;

	/** Skapar en tom lista. */
	public SearchLinkedList() {
		first = null;
	}

	/** Sätter in ett element i listan. */
	public void insert(E e) {

		if (first == null) {
			first = new Node<>(e);

		} else if (first.next == null) {

			if (first.searched == true) {
				first.next = new Node<>(e);

			} else {
				Node<E> temp = new Node<>(e);
				temp.next = first;
				first = temp;

			}

		} else {

			Node<E> temp = first.next;
			Node<E> follow = first;

			if (follow.searched == false) {

				Node<E> n = new Node<>(e);
				n.next = first;
				first = n;
				return;
			}

			while (temp != null) {

				if (temp.searched == true) {

					if (temp.next == null) {
						temp.next = new Node<>(e);
						return;
					}
					follow = temp;
					temp = temp.next;

				} else {
					Node<E> n = new Node<>(e);
					follow.next = n;
					n.next = temp;
					return;
				}

			}
		}

	}

	/**
	 * Söker senast insatta förekomsten av ett element som matchar x i listan. Om
	 * ett sådant finns returneras detta, i annat fall returneras null.
	 */
	public E find(E x) {
		if (first == null) {
			return null;
		}

		Node<E> temp = first.next;
		Node<E> follow = first;

		if (x.equals(follow.data)) {
			follow.searched = true;
			return x;
		}

		while (temp != null) {

			if (x.equals(temp.data)) {

				temp.searched = true;

				if (follow != null) {
					follow.next = temp.next;
				}

				temp.next = first;
				first = temp;

				return x;
			}
			follow = temp;
			temp = temp.next;
		}
		return null;
	}

	/* Nästlad klass. */
	private static class Node<E> {
		private E data;
		private boolean searched;
		private Node<E> next;

		/* Skapar en nod som innehåller e. */
		private Node(E e) {
			data = e;
			searched = false;
			next = null;
		}
	}

	public static void main(String[] args) {
		SearchLinkedList<Integer> list = new SearchLinkedList<Integer>();
		list.insert(30);
		list.insert(42);
		list.insert(31);
		list.insert(33);

		System.out.println(list.find(42));

	}
}