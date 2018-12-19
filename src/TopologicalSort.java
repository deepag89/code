import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
class Graph1 {
	int v;
	LinkedList<Integer> adj[];
	
	Graph1(int v1) {
		v = v1;
		adj = new LinkedList[v];
		for(int i=0; i<v; i++)
			adj[i] = new LinkedList<>();
	}
	
	void addEdge(int u, int v) {
		adj[u].add(v);
	}
	
	void printTopoSort() {
		boolean visited[] = new boolean[v];
		for(int i=0; i<v; i++)
			visited[i] = false;
		
		Stack<Integer> topoStack = new Stack<Integer>();
		
		for(int i=0; i<v; i++) {
			if(!visited[i]) {
				dfsTopoSort(i, visited, topoStack);
			}
		}
		
		while(topoStack.empty() == false) {
			System.out.println(topoStack.pop());
		}
	}

	private void dfsTopoSort(int cur, boolean visited[], Stack<Integer> topoStack) {
		visited[cur] = true;
		
		for(Iterator<Integer> it = adj[cur].iterator(); it.hasNext() ;) {
			int next = it.next();
			if(visited[next] == false) {
				dfsTopoSort(next, visited, topoStack);
			}
		}
		
		topoStack.push(cur);
	}
}

public class TopologicalSort {
	public static void main(String args[]) {
		Graph1 g = new Graph1(3);
//        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(2, 1);
        
        g.printTopoSort();
	}
}
