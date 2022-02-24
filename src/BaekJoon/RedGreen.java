// 백준 10026 적록색약
package BaekJoon;

import java.io.*;
import java.util.*;

public class RedGreen {

	static int N;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	static char[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int normal = 0;
		int other = 0;
		graph = new char[N][N];
		visited = new boolean[N][N];
		
		for (int x=0; x<N; x++) {
			String line = br.readLine();
			for (int y=0; y<N; y++) {
				graph[x][y] = line.charAt(y);
			}
		}
			
		for (int x=0; x<N; x++) {
			for (int y=0; y<N; y++) {
				if (!visited[x][y]) {
					bfs(x, y, graph[x][y]);
					normal += 1;
				}
			}
		}
		
		visited = new boolean[N][N];
		
		for (int x=0; x<N; x++) {
			for (int y=0; y<N; y++) {
				if (!visited[x][y]) {
					otherBFS(x, y, graph[x][y]);
					other += 1;
				}
			}
		}
		
		System.out.println(normal + " " + other);
	}

	private static void bfs(int x, int y, char c) {
		
		Queue<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			x = info[0];
			y = info[1];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N)
					continue;
				if (ny < 0 || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (graph[nx][ny] != c)
					continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	private static void otherBFS(int x, int y, char c) {
		
		Queue<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			x = info[0];
			y = info[1];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N)
					continue;
				if (ny < 0 || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (c == 'R' && graph[nx][ny] == 'B')
					continue;
				if (c == 'G' && graph[nx][ny] == 'B')
					continue;
				if (c == 'B' && graph[nx][ny] != 'B')
					continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
}
