// 백준 1600 말이 되고픈 원숭이
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_1600 {
	
	static int[] dx = new int[] {0, -1, 0, 1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static int[] jumpDx = new int[] {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] jumpDy = new int[] {-1, 1, -2, 2, -2, 2, -1, 1};
	
	static int[][] map;
	static boolean[][][] visited;
	
	static int X;
	static int Y;
	static int JUMP;
	static int answer = -1;
	
	public static class Monkey {
		int x;
		int y;
		int jumpAmount;
		int moveCount;
		
		public Monkey(int x, int y, int jumpAmount, int moveCount) {
			this.x = x;
			this.y = y;
			this.jumpAmount = jumpAmount;
			this.moveCount = moveCount;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		JUMP = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new int[X][Y];
		visited = new boolean[X][Y][JUMP+1];
		
		for (int x=0; x<X; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y=0; y<Y; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		// monkey = (x, y, jumpAmount, moveCount)
		Monkey monkey = new Monkey(0, 0, 0, 0);
		searchRoute(monkey);
		
		System.out.println(answer);
	}

	private static void searchRoute(Monkey rootMonkey) {
		
		Queue<Monkey> q = new ArrayDeque<>();
		visited[rootMonkey.x][rootMonkey.y][rootMonkey.jumpAmount] = true;
		q.offer(rootMonkey);
		
		while(!q.isEmpty()) {
			Monkey monkey = q.poll();
			
			if (monkey.x == X-1 && monkey.y == Y-1) {
				answer = monkey.moveCount;
				break;
			}	
			
			for (int d=0; d<4; d++) {
				int nx = monkey.x + dx[d];
				int ny = monkey.y + dy[d];
				int nextJumpAmount = monkey.jumpAmount;
				int nextMoveCount = monkey.moveCount+1;
				
				if (nx < 0 || nx >= X)
					continue;
				if (ny < 0 || ny >= Y)
					continue;
				if (visited[nx][ny][nextJumpAmount])
					continue;
				if (map[nx][ny] == 1)
					continue;
				
				visited[nx][ny][nextJumpAmount] = true;
				q.offer(new Monkey(nx, ny, nextJumpAmount, nextMoveCount));
			}
			
			if (monkey.jumpAmount < JUMP) {
				for (int d=0; d<8; d++) {
					int nx = monkey.x + jumpDx[d];
					int ny = monkey.y + jumpDy[d];
					int nextJumpAmount = monkey.jumpAmount+1;
					int nextMoveCount = monkey.moveCount+1;

					if (nx < 0 || nx >= X)
						continue;
					if (ny < 0 || ny >= Y)
						continue;
					if (visited[nx][ny][nextJumpAmount])
						continue;
					if (map[nx][ny] == 1)
						continue;
					
					visited[nx][ny][nextJumpAmount] = true;
					q.offer(new Monkey(nx, ny, nextJumpAmount, nextMoveCount));
				}
				
			}
			
		}
		
	}

}

// 각 좌표 마다 k번 상태 중첩 가능





