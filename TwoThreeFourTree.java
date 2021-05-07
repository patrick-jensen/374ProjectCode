/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 */
import java.util.*;
public class TwoThreeFourTree<T> {

	//Global data
	protected final TwoThreeFourTree<T> divorcedAndChildless = null;
	protected TwoThreeFourTree<T> children[] = new TwoThreeFourTree[4];

	protected Comparator<T> c;

	private T[] elems = (T[]) new Object[3];


	protected TwoThreeFourTree<T> 	parent;
	protected int	numberOfElems;

	public TwoThreeFourTree() {
		numberOfElems = 0;
		c = new Comparator<T>();
	}

	public TwoThreeFourTree(Comparator<T> ca) {
		numberOfElems = 0;
		c = ca;
	}
	public int numElements() {
		return numberOfElems;
	}


	public int treeSize() {
		if (this.leaf() == true) {
			return numberOfElems;
		}
		int num = numberOfElems;
		for (int i = 0; i < numberOfElems + 1; i++) {
			num += getChild(i).treeSize();
		}
		return num;
	}

	public boolean leaf() {
		TwoThreeFourTree<T> temp = firstChild();
		return temp == divorcedAndChildless;
	}

	public void makeEdge(TwoThreeFourTree<T> node, int i) {
		children[i] = node;
		if(node != divorcedAndChildless) {
			node.parent = this;
		}
	}

	public TwoThreeFourTree<T> removeEdge(int n) {
		TwoThreeFourTree<T> temp = getChild(n);
		children[n] = divorcedAndChildless;
		return temp;
	}
	
	public int add(T node) {
		numberOfElems++;
		int rVal = 0;
		for(int i=elems.length-1; i>=0; i--)  { 
				if(elems[i] == null)
					continue;
				else {

					T k = elems[i];
					if (c.compare(node, k ) < 0) {
						elems[i+1] = elems[i];
					} else {
						elems[i + 1] = node;
						rVal = i + 1;
						break;
						}
				}
		}
		elems[rVal] = node;
		return rVal;
	}


	public int find(T n) {
		int flag = -1;

		for (int i = 0; i < 3; i++)  {
				if(elems[i] == null) {
					break;
				}
				else if (c.compare(elems[i], n) == 0) {
					flag = i;
				}
		}
		return flag;
	}

	public int searchG(T x, T max) {
		int check = -1;
		for(int i = 0; i < 3; i++)  {
			if (elems[i] == null) {
				break;
			}
			else if (0 > (c.compare(elems[i], max)) &&  0 < (c.compare(elems[i], x))) {
				check = i;
				break;
			}
		}
		return check;
	}

	public int searchL(T n, T min) {
		int check = -1;
		for(int i=2; i>=0; i--)  {
			if(elems[i] == null) {
				continue;
			}
			else if(0 < (c.compare(elems[i], min)) && 0 >(c.compare(elems[i], n) )) {
				check = i;
				break;
			}
		}
		return check;
	}

	public int searchGE(T n, T max) {
		int check = -1;
		for (int i = 0; i < 3; i++)  {
			if (elems[i] == null) {
				break;
			}
			else if ( 0 <= (c.compare(elems[i], n)) && 0 > (c.compare(elems[i], max))) {
				check = i;
				break;
			}
		}
		return check;
	}

	public T remove() {
		numberOfElems--;
		T temp = elems[numberOfElems];
		elems[numberOfElems] = null;
		return temp;
	}

	T removeElement(int index) {
		int i;
		T rVal = getElem(index);
		for (i = index; i < numberOfElems - 1; i++) {
			elems[i] = elems[i + 1];
		}
		elems[i] = null;
		numberOfElems--;
		return rVal;

	}

	public String toString() {
		String str = "";
		for (int i = 0; i < numElements(); i++) {
			str += elems[i] + " ";
		}
		return str;
	}

	public T largestElem() {
		return elems[numberOfElems-1];
	}
	
	public T getElem(int n) {
		return elems[n];
	}

	public T smallestElem() {
		return elems[0];
	}

	public boolean full() {
		return numberOfElems >= 3;
	}

	public TwoThreeFourTree<T> parent() {
		return parent;
	}
	
	public TwoThreeFourTree<T> firstChild() {
		return children[0];
	}

	public TwoThreeFourTree<T> lastChild() {
		return children[numberOfElems];
	}

	public TwoThreeFourTree<T> getChild(int i) {
		return children[i];
	}

	public TwoThreeFourTree<T>[] getChildren() {
		return children;
	}


}
