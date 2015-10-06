import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
@SuppressWarnings("unchecked")
class Graph {

	int[][] array;
	int size;
	ArrayList<Character> vertices;
	ArrayList<SingleLinkedList> edges;
	Graph (int size) {
		this.size = size;
		array = new int[size][size];
		edges = new ArrayList<SingleLinkedList>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = 0;
			}
		}
		vertices = new ArrayList<Character>();
	}
	
	public void createEdge(int vertex1,  int vertex2) {
		array[vertex1][vertex2] = 1;
		vertex1 = vertex1 + 97;
		vertex2 = vertex2 + 97;
		char vert1 = (char)vertex1;
		char vert2 = (char)vertex2;
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i).getHead().getElement().equals(vert1)) {
				edges.get(i).insertElement(vert2);
			}
		}
	}

	public void createVertex(char vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			SingleLinkedList<Character> sll = new SingleLinkedList<Character>();
			sll.insertElement(vertex);
			edges.add(sll);
		}
	}

	public void printAdjacencyMatrix() {
		System.out.print(" ");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + vertices.get(i));
		}
		System.out.println("\n");
		for (int i = 0; i < size; i++) {
			System.out.print(vertices.get(i));
			for (int j = 0; j < size; j++) {
				System.out.print(" " + array[i][j]);
			}
			System.out.println("\n");
		}
	}

	public void printAdjacencyList() {
		System.out.println("\n");
		for (int i = 0; i < edges.size(); i++) {
			System.out.println(edges.get(i));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		Graph g = new Graph(size);
		String s = sc.nextLine();
		StringTokenizer st = new StringTokenizer(s,"(,) ");
		while (st.hasMoreTokens()) {
			String s1 = st.nextToken();
			g.createVertex(s1.charAt(0));
			String s2 = st.nextToken();
			g.createVertex(s2.charAt(0));
			int vertex1 = (int)s1.charAt(0) - 97;
			int vertex2 = (int)s2.charAt(0) - 97;
			g.createEdge(vertex1, vertex2);
		}
		g.printAdjacencyMatrix();
		g.printAdjacencyList();
	}
}
class Node<T> {
	T element;
	Node<T> nextElement;

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return this.element;
	}

	public void setNextElement(Node<T> nextElement) {
		this.nextElement = nextElement;
	}

	public Node<T> getNextElement() {
		return this.nextElement;
	}
}
@SuppressWarnings("unchecked")

class SingleLinkedList<T extends Comparable<T>> {
  Node<T> head;
  Node<T> tail;

  public Node<T> getHead() {
    return this.head;
  }

  public Node<T> getTail() {
    return this.tail;
  }

  public void insertElement(T elemnet) {
    Node<T> node = new Node<T>();
    node.setElement(elemnet);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.setNextElement(node);
      tail = node;
    }
  }

  public void sort() {
    Node<T> sortNode = head;
    Node<T> temp1 = null;
    Node<T> temp2 = null;
    while (sortNode != null) {
      if (sortNode.getNextElement() != null) {        
        temp1 = sortNode.getNextElement();
        temp2 = temp1.getNextElement();
        if (temp2 != null && temp1.getElement().compareTo(temp2.getElement()) > 0) {
          temp1.setNextElement(temp2.getNextElement());
          temp2.setNextElement(temp1);
          sortNode.setNextElement(temp2);
        }
      }
      sortNode = sortNode.getNextElement();
    }
  }
  public String toString() {
    sort();
    StringBuilder linkedData = new StringBuilder();
    Node<T> printNode = head;
    while (printNode != null) {
      linkedData.append(printNode.getElement() + "->");
      printNode = printNode.getNextElement();
    }
    linkedData.append("null");
    return linkedData.toString();
  }
}
