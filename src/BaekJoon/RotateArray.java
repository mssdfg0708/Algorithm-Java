/*
백준 16926 배열 돌리기 1

돌아가는 하나의 세트를 Deque로 만들어 돌리고
다시 Deque를 배열에 집어 넣는다

전체 Deque의개수는 Math.min(N, M)/2 이고
Deque[] container 로 관리
*/
package BaekJoon;

import java.io.*;
import java.util.*;

public class RotateArray {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		
		int minValue = Math.min(N, M);
		Deque<Integer>[] container = new Deque[minValue/2];
		
		for (int index=0; index<container.length; index++) {
			container[index] = new ArrayDeque<Integer>();
		}
		
		for (int x=0; x<N; x++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			for (int y=0; y<M; y++) {
				board[x][y] = Integer.parseInt(line.nextToken());			
			}
		}
		
		int indexQ = 0;
		int x = 0;
		int y = 0;
		int[] dx = {0, 1, 0, -1, 0};
		int[] dy = {1, 0, -1, 0, 0};
		int d = 0;
		
		while (indexQ < container.length) {
			container[indexQ].addLast(board[x][y]);
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < indexQ || nx >= N-indexQ) {
				d += 1;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			if (ny < indexQ || ny >= M-indexQ) {
				d += 1;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
			if (d == 4) {
				container[indexQ].removeLast();
				indexQ += 1;
				d = 0;
				x = indexQ;
				y = indexQ;
			}
		}
		
		for (int rotate=0; rotate<R; rotate++) {
			for (int index=0; index<container.length; index++) {
				int item = container[index].poll();
				container[index].offer(item);
			}
		}
		
		indexQ = 0;
		x = 0;
		y = 0;
		d = 0;
		
		while (indexQ < container.length) {
			
			if (container[indexQ].isEmpty()) {
				indexQ += 1;
				d = 0;
				x = indexQ;
				y = indexQ;
				continue;
			}
			board[x][y] = container[indexQ].poll();
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < indexQ || nx >= N-indexQ) {
				d += 1;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			if (ny < indexQ || ny >= M-indexQ) {
				d += 1;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
		}
		
		for (int[] row: board) {
			for (int item: row) {
				System.out.print(item + " ");
			}
			System.out.println();
		}
	}
}
