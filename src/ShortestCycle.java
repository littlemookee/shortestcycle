
import edu.princeton.cs.algs4.*;

public class ShortestCycle {
	private Stack<Integer> shortestCycle;
	
	public ShortestCycle(Digraph graph) {
		int N = graph.V();
		int shortestDist = Integer.MAX_VALUE;
		
		for (int i=0; i<N; i++) {
			Queue<Integer> queue = new Queue<Integer>();

			boolean[] marked = new boolean[N];
			int[] pathTo = new int[N];
			
			queue.enqueue(i);
			marked[i] = true;
			
			while (!queue.isEmpty()) {
				int v = queue.dequeue();				
				for (int w : graph.adj(v)) {
					if (marked[w]) {
						Stack<Integer> cycle = new Stack<Integer>();

						for (int x=w; pathTo[x]!=w; x=pathTo[x]) cycle.push(x);
						
						if (shortestDist > cycle.size()) {
							shortestCycle = cycle;
							shortestDist = cycle.size();
						}
					} else {
						marked[w] = true;
						pathTo[w] = v;
						queue.enqueue(w);
					}
				}
			}
		}
	}
	
	public Iterable<Integer> getShortestCycle() {
		return shortestCycle;		
	}	
}