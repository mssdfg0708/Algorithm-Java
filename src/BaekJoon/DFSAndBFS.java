// 백준 1260 DFS와 BFS
package BaekJoon;

import java.io.*;
import java.util.*;

public class DFSAndBFS {
	
	static int Edge;
	static int Vertex;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Edge = Integer.parseInt(st.nextToken());
		Vertex = Integer.parseInt(st.nextToken());
		visited = new boolean[Edge+1];
		int root = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[Edge+1];
		for (int edge=0; edge<Edge+1; edge++) {
			graph[edge] = new ArrayList<>();
		}
		
		for (int vertex=0; vertex<Vertex; vertex++) {
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			graph[start].add(end);
			graph[end].add(start);
		}
		
		for (int edge=0; edge<Edge+1; edge++) {
			List<Integer> list = graph[edge];
			Collections.sort(list);
		}
		
		visited[root] = true;
		dfs(graph, root);
		
		System.out.println();
		
		visited = new boolean[Edge+1];
		visited[root] = true;
		bfs(graph, root);
	}

	private static void dfs(List<Integer>[] graph, int vertex) {
		System.out.print(vertex + " ");
		
		for (int next: graph[vertex]) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(graph, next);
			}
		}
	}

	private static void bfs(List<Integer>[] graph, int root) {
		
		Deque<Integer> q = new ArrayDeque<>();
		q.addLast(root);
		
		while (!q.isEmpty()) {
			int vertex = q.poll();
			System.out.print(vertex + " ");
			for (int next: graph[vertex]) {
				if (!visited[next]) {
					visited[next] = true;
					q.addLast(next);
				}
			}
		}
	}
}





