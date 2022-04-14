// 백준 17143 낚시왕
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_17143 {
	
	// 상, 우, 하, 좌
	static int[] convertDirection = new int[] {-1, 0, 2, 1, 3};
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	static int X;
	static int Y;
	static int M;
	
	static Shark[][] board;
	
	static class Shark {
		int x;
		int y;
		int speed;
		int d;
		int size;
		
		public Shark(int x, int y, int speed, int d, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", speed=" + speed + ", d=" + d + ", size=" + size + "]";
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken())+1;
		Y = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		
		board = new Shark[X][Y];
		
		for (int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			d = convertDirection[d];
			int size = Integer.parseInt(st.nextToken());
			
			board[x][y] = new Shark(x, y, speed, d, size);
		}
		
		int fisherY = 0;
		int answer = 0;
		while (fisherY < Y-1) {
			fisherY += 1;
			answer += catchShark(fisherY);
			
			List<Shark> nextList = new ArrayList<>();
			moveShark(nextList);
			eatShark(nextList);
		}
		System.out.println(answer);
	}

	private static void eatShark(List<Shark> nextList) {
		for (Shark shark : nextList) {
			int x = shark.x;
			int y = shark.y;
			int size = shark.size;
			
			if (board[x][y] == null) {
				board[x][y] = (shark);
			}
			else if(board[x][y].size < size) {
				board[x][y] = (shark);
			}
			
		}
		
	}

	private static void moveShark(List<Shark> nextList) {
		for (int x=1; x<X; x++) {
			for (int y=1; y<Y; y++) {
				if (board[x][y] != null) {
					Shark shark =  board[x][y];
					int Speed = shark.speed;
					int sharkX = shark.x;
					int sharkY = shark.y;
					int d = shark.d;
					
					int cycle;
					if (d == 0 || d == 2)
						cycle = (X-2)*2;
					else 
						cycle = (Y-2)*2;
					
					Speed = Speed % cycle;
					for (int speed=0; speed<Speed; speed++) {
						int nx = sharkX + dx[d];
						int ny = sharkY + dy[d];
						
						if (nx < 1 || nx >= X) {
							d = (d+2) % 4;
							nx = sharkX + dx[d];
							ny = sharkY + dy[d];
						}
						else if (ny < 1 || ny >= Y) {
							d = (d+2) % 4;
							nx = sharkX + dx[d];
							ny = sharkY + dy[d];
						}
						
						sharkX = nx;
						sharkY = ny;
					}
					board[x][y] = null;
					nextList.add(new Shark(sharkX, sharkY, Speed, d, shark.size));
				}
			}
		}
	}

	private static int catchShark(int y) {
		int result = 0;
		for (int x=0; x<X; x++) {
			if (board[x][y] != null) {
				result = board[x][y].size;
				board[x][y] = null;
				break;
			}
		}
		return result;
	}
	
}

/*
cycle = (X-2)*2 or (Y-2)*2
move = speed % cycle;

*/










