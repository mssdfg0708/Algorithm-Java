// 정올 1681 해밀턴 순환회로
package ETC;

import java.io.*;
import java.util.*;

public class Main_1681 {
	
	static int N;
	static boolean[] visited;
	static int[][] graph;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new boolean[N];
		
		for (int x=0; x<N; x++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int y=0; y<N; y++) {
				graph[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0] = true;
		dfs(0, 0, 0);
		System.out.println(answer);
	}

	private static void dfs(int count, int distance, int vertex) {
		
		if (count >= N-1) {
			if (graph[vertex][0] != 0) {
				distance += graph[vertex][0];
				answer = Math.min(distance, answer);
			}
			return;
		}
		if (distance > answer)
			return;
		
		for (int next=0; next<N; next++) {
					
			if (visited[next])
				continue;
			if (graph[vertex][next] == 0)
				continue;
			
			visited[next] = true;
			dfs(count+1, distance + graph[vertex][next], next);
			visited[next] = false;
		}
	}
}

/*
5
0 14 4 10 20
14 0 7 8 7
4 5 0 7 16
11 7 9 0 2
18 7 17 4 0
*/





