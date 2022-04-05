// 백준 1194 달이 차오른다 가자
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_1194 {
	
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	
	static char[][] board;
	static boolean[][][] visited;
	static int X;
	static int Y;
	
	static class State {
		
		int x;
		int y;
		int moveCount;
		int keyCode;
		
		public State(int x, int y, int moveCount, int keyCode) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
			this.keyCode = keyCode;
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		board = new char[X][Y];
		visited= new boolean[X][Y][65];
		
		State state = null;
		
		for (int x=0; x<X; x++) {
			String line = br.readLine();
			for (int y=0; y<Y; y++) {
				board[x][y] = line.charAt(y);
				if (board[x][y] == '0') {
					state = new State(x, y, 0, 0);
				}
			}
		}
		
		int answer = bfs(state);
		System.out.println(answer);
		
	}

	private static int bfs(State rootState) {
		
		int result = -1;
		
		Queue<State> q = new ArrayDeque<>();
		q.offer(rootState);
		visited[rootState.x][rootState.y][rootState.keyCode] = true;

		while (!q.isEmpty()) {
			State state = q.poll();
			
			if (board[state.x][state.y] == '1') {
				result = state.moveCount;
				break;
			}
					
			for (int d=0; d<4; d++) {	
				int nextMoveCount = state.moveCount + 1;
				int nx = state.x + dx[d];
				int ny = state.y + dy[d];
				
				if (nx < 0 || nx >= X)
					continue;
				if (ny < 0 || ny >= Y)
					continue;
				if (board[nx][ny] == '#')
					continue;
				if (visited[nx][ny][state.keyCode])
					continue;
				
				char item = board[nx][ny];
				
				if (Character.isAlphabetic(item) && Character.isUpperCase(item)) {
					int hasKey = (1 << board[nx][ny] - 'A') & state.keyCode;
					if (hasKey > 0) {
						visited[nx][ny][state.keyCode] = true;
						q.offer(new State(nx, ny, nextMoveCount, state.keyCode));
					}
					continue;
				}
				
				if (Character.isAlphabetic(item) && Character.isLowerCase(item)) {
					int nextKeyCode = (1 << board[nx][ny] - 'a') | state.keyCode;
						
					if (!visited[nx][ny][nextKeyCode]) {
						visited[nx][ny][nextKeyCode] = true;
						q.offer(new State(nx, ny, nextMoveCount, nextKeyCode));
					}
					continue;
				}

				visited[nx][ny][state.keyCode] = true;
				q.offer(new State(nx, ny, nextMoveCount, state.keyCode));

			}
			
		}
		return result;
		
	}

}

// 열쇠를 얻을 때마다 visited 초기화
// 이를 위해 State 객체는 x, y, visited, ketList 로 구성 (메모리 초과 발생!)
// visited, ketList 를 bitMask KeyCode 로 변경
// BFS 진행
// 1에 도착 하면 종료
