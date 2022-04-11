// 백준 17135 캐슬디펜스
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_17135 {
	
	static int N;
	static int M;
	static int DISTANCE;
	
	static int[][] rootBoard;
	static int[][] board;
	static Point[] archers;
	static Point[] targets;
	static int MAX_KILL = 0;
	
	static class Point {
		int x;
		int y;
		int distance;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.distance = 1;
		}

		public Point(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", distance=" + distance + "]";
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		DISTANCE = Integer.parseInt(st.nextToken());
		
		archers = new Point[3];
		board = new int[N][M];
		rootBoard = new int[N][M];
		
		for (int x=0; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y=0; y<M; y++) {
				rootBoard[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		combination(0, 0);
		System.out.println(MAX_KILL);
	}

	private static void combination(int start, int depth) {
		if (depth >= 3) {
			for (int x=0; x<N; x++) {
				if (M >= 0)
					System.arraycopy(rootBoard[x], 0, board[x], 0, M);
			}
			
			int kill = 0;
			for (int loop=0; loop<N; loop++) {
				setTargets();
				kill = shoot(kill);
				march();
			}
			if (kill > MAX_KILL) {
				MAX_KILL = kill;
			}
			return;
		}

		for (int y=start; y<M; y++) {
			archers[depth] = new Point(N-1, y);
			combination(y+1, depth+1);
		}
	}

	private static void march() {
		if (N - 1 >= 0)
			System.arraycopy(board, 0, board, 1, N - 1);
		board[0] = new int[M];
		
	}

	private static int shoot(int kill) {
		for (Point target : targets) {
			if (target == null)
				continue;
			if (board[target.x][target.y] == 1) {
				kill += 1;
				board[target.x][target.y] = 0;
			}
		}
		return kill;
	}

	private static void setTargets() {
		targets = new Point[3];
		for (int index=0; index<3; index++) {
			int[] dx = new int[] {0, -1, 0};
			int[] dy = new int[] {-1, 0, 1};
			Point archer = archers[index];
			
			Queue<Point> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][M];

			q.add(archer);
			visited[archer.x][archer.y] = true;
			if (board[archer.x][archer.y] == 1) {
				targets[index] = new Point(archer.x, archer.y);
				continue;
			}
			
			while (!q.isEmpty()) {
				Point point = q.poll();
				if (point.distance > DISTANCE) {
					break;
				}
				if (board[point.x][point.y] == 1) {
					targets[index] = new Point(point.x, point.y);
					break;
				}
				
				for (int d=0; d<3; d++) {
					int nx = point.x + dx[d];
					int ny = point.y + dy[d];
					
					if (nx < 0 || nx >= N)
						continue;
					if (ny < 0 || ny >= M)
						continue; 
					if (visited[nx][ny])
						continue;

					q.offer(new Point(nx, ny, point.distance +1));
				}
				
			}
			
		}
		
	}

}


// 사수 위치 Point[] archer : [사수 번호]
// 공격 대상 Point[] target : [사수 번호]

// 매 턴 마다
// 각 사수 마다 [좌 상 우] 순서로 진행 되는 BFS 탐색
// 각 사수 마다 공격 대상 선정
// 동시 공격
// 공격받은 적 제거
// 1칸씩 내려오기
// 반복
