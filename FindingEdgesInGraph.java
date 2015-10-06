import java.util.Scanner;
import java.util.StringTokenizer;

class FindingEdgesInGraph {
	int[][] array;
	int size;
	int edgesCount;
	FindingEdgesInGraph (int size) {
		this.size = size;
		array = new int[size][size];
		edgesCount = 0;
	}

	public void createAdjacencyMatrix(int[][] array) {
		this.array = array;
	}

	public int getNoOfEdges() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (array[i][j] == 1 && i != j) {
					array[j][i] = 0;
				}
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (array[i][j] == 1) {
					edgesCount++;
				}
			}
		}
		return edgesCount;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		FindingEdgesInGraph feig =  new FindingEdgesInGraph(size);
		int array[][] = new int[size][size];
		int i = 0, j = 0;
		while (i < size) {
			String s = sc.nextLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			j = 0;
			while (st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				array[i][j] = value;
				j++;
			}
			i++;
		}
		feig.createAdjacencyMatrix(array);
		System.out.println(feig.getNoOfEdges());
	}
}