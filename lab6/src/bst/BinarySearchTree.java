package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {

	public static void main(String[] args) {

		BSTVisualizer v = new BSTVisualizer("Träd", 200, 300);
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		t.add(14);
		t.add(12);
		t.add(-1);
		t.add(2);
		t.add(14);
		t.add(100);
		t.add(99);
		t.add(15);
		t.add(16);
		t.add(11);
		t.add(50);
		t.add(44);
		t.add(30);
		t.add(72);

		//t.rebuild();

		v.drawTree(t);
		t.printTree();

	}

	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comp;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);

	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comp) {
		root = null;
		size = 0;
		this.comp = comp;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {

		if (root == null) { // specialfall om trädet är tomt
			root = new BinaryNode<E>(x);
			size = 1;
			return true;
		}

		return add(root, x); // annars kalla på hjälpmetod

	}

	private boolean add(BinaryNode<E> bn, E x) {

		if (comp.compare(bn.element, x) == 0) { // Fall 1: hittar vi samma E -> returnerar false direkt
			return false;

		} else if (comp.compare(bn.element, x) > 0) { // Fall 2: x är mindre är nuvarande nod, fortsätt ner vänster

			if (bn.left == null) {// tomt nere vänster, lägger till x
				bn.left = new BinaryNode<E>(x);
				size++;
				return true;

			} else { // fortsätt nedåt ett steg till rekursivt

				return add(bn.left, x);
			}

		} else { // Fall 3: x är större är nuvarande nod, fortsätt ner höger

			if (bn.right == null) { // tomt nere höger, lägger till x
				bn.right = new BinaryNode<E>(x);
				size++;
				return true;

			} else { // fortsätt nedåt ett steg till rekursivt
				return add(bn.right, x);
			}
		}

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {

		return height(root); // anropar privat hjälpmetod med root
	}

	private int height(BinaryNode<E> e) {

		if (e != null) {

			int left = height(e.left); // hur långt man kommer ner till vänster
			int right = height(e.right); // hur långt man kommer till höger
			return 1 + Math.max(left, right); // lägsta nivån får 0, så + 1 för varje nivå upp

		} else {

			return 0; // när man kommit till ett löv alt tomt träd
		}

	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() { // återställer trädet
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> bn) { // skriver ut inorder

		if (bn != null) {
			printTree(bn.left);
			System.out.print(bn.element.toString() + " ");
			printTree(bn.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

		ArrayList<E> list = new ArrayList<>();
		toArray(root, list);
		root = buildTree(list, 0, list.size() - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted); // går ner och letar till vänster
			sorted.add(n.element); // lägger till (imitten -> blir inorder)
			toArray(n.right, sorted); // går ner och letar till höger
		}

	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) { // speciallfall samt löv
			return null;
		}

		int mid = (last + first + 1) / 2; // tar +1 för vill hellre fylla vänster sida
		BinaryNode<E> temp = new BinaryNode<E>(sorted.get(mid)); // skapar en nod
		temp.left = buildTree(sorted, first, mid - 1); // tar mitten av vänstra sida av listan, blir vänster barn
		temp.right = buildTree(sorted, mid + 1, last); // tar mitten av högra sida av listan, blir höger barn
		return temp;

	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
