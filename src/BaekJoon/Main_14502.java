// 백준 14502 연구소
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_14502 {
	
	static List<int[]> emptyList = new ArrayList<>();
	static List<int[]> candidateList = new ArrayList<>();
	static List<int[]> wallList = new ArrayList<>();
	static List<int[]> rootVirusList = new ArrayList<>();
	
	static int N;
	static int M;
	static int[][] board;
	
	static int MAX_SAFE_ZONE = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for (int x=0; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y=0; y<M; y++) {
				board[x][y] = Integer.parseInt(st.nextToken());
				if (board[x][y] == 0)
					emptyList.add(new int[] {x, y});
				if (board[x][y] == 1)
					wallList.add(new int[] {x, y});
				if (board[x][y] == 2)
					rootVirusList.add(new int[] {x, y});
			}
		}

		combination(0, 0);
		System.out.println(MAX_SAFE_ZONE);
	}

	private static void combination(int start, int depth) {
		if (depth >= 3) {
			buildWall();
			spreadVirus();
			breakWall();
			return;
		}

		for (int index=start; index<emptyList.size(); index++) {
			int[] candidate = emptyList.get(index);
			candidateList.add(candidate);
			combination(index+1, depth+1);
			candidateList.remove(candidateList.size()-1);
		}
	}

	private static void breakWall() {
		for (int[] point : candidateList) {
			board[point[0]][point[1]] = 0;
		}
	}

	private static void buildWall() {
		for (int[] point : candidateList) {
			board[point[0]][point[1]] = 1;
		}
	}

	private static void spreadVirus() {
		
		int[] dx = new int[] {-1, 0, 1, 0};
		int[] dy = new int[] {0, -1, 0, 1};

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int[] virus : rootVirusList) {
			q.offer(virus);
			visited[virus[0]][virus[1]] = true;
		}

		while (!q.isEmpty()) {
			int[] virus = q.poll();
			int x = virus[0];
			int y = virus[1];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N)
					continue;
				if (ny < 0 || ny >= M)
					continue; 
				if (board[nx][ny] != 0)
					continue;
				if (visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
		int safeZone = 0;
		for (int x=0; x<N; x++) {
			for (int y=0; y<M; y++) {
				if (board[x][y] == 0 && !visited[x][y])
					safeZone += 1;
			}
		}
		
		MAX_SAFE_ZONE = Math.max(MAX_SAFE_ZONE, safeZone);
	}

}




