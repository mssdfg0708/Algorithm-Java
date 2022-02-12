// 백준 17406 배열 돌리기 4
package BaekJoon;

import java.io.*;
import java.util.*;

public class RotateArray4 {
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		// 초기 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Query = Integer.parseInt(st.nextToken());
		
		int[][] originBoard = new int[N][M];
		
		for (int x=0; x<N; x++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			for (int y=0; y<M; y++) {
				originBoard[x][y] = Integer.parseInt(line.nextToken());			
			}
		}
		
		int[][] board = new int[N][M];
		
		for (int x=0; x<N; x++) {
			for (int y=0; y<M; y++) {
				board[x][y] = originBoard[x][y];
			}
		}
		
		// Permutation
		String[][] query = new String[Query][];
		int[] arr = new int[Query];
		
		for (int q=0; q<Query; q++) {
			query[q] = br.readLine().split(" ");
			arr[q] = q;
		}
		
		permutation(arr, board, originBoard, query, 0, Query, Query);
		
		System.out.println(answer);
		
	}
	
	// Permutation
	static void permutation(int[] arr, int[][] board, int[][] originBoard, String[][] query, int depth, int n, int r) {
		
		// Permutation 완료 조건
	    if (depth == r) {
	        
	    	List<String[]> orders = new ArrayList<>();
			for (int item : arr) {
				orders.add(query[item]);
			}
	    	
			// query 입력, 회전 수행
			for (int q=0; q<orders.size(); q++) {
				
				// query 입력
				String[] order = orders.get(q);			
				int centerX = Integer.parseInt(order[0]);
				int centerY = Integer.parseInt(order[1]);
				int offset = Integer.parseInt(order[2]);
				
				// 회전을 위한 Queue container 준비
				Deque<Integer>[] container = new Deque[offset];
				
				for (int index=0; index<container.length; index++) {
					container[index] = new ArrayDeque<>();
				}
				
				// Queue Container 만들기
				int indexQ = 0;
				int x = centerX-offset-1;
				int y = centerY-offset-1;
				int[] dx = {0, 1, 0, -1, 0};
				int[] dy = {1, 0, -1, 0, 0};
				int d = 0;
				
				while (indexQ < container.length) {
					container[indexQ].addLast(board[x][y]);
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < centerX-offset-1+indexQ || nx >= (centerX+offset)-indexQ) {
						d += 1;
						nx = x + dx[d];
						ny = y + dy[d];
					}
					if (ny < centerY-offset-1+indexQ || ny >= (centerY+offset)-indexQ) {
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
						x = centerX-offset-1+indexQ;
						y = centerY-offset-1+indexQ;
					}
				}
				
				// 회전
				for (Deque<Integer> integers : container) {
					int item = integers.removeLast();
					integers.addFirst(item);

				}
				
				// Queue Container 를 다시 배열에 집어 넣기
				indexQ = 0;
				x = centerX-offset-1;
				y = centerY-offset-1;
				d = 0;
				
				while (indexQ < container.length) {
					
					if (container[indexQ].isEmpty()) {
						indexQ += 1;
						d = 0;
						x = centerX-offset-1+indexQ;
						y = centerY-offset-1+indexQ;
						continue;
					}
					board[x][y] = container[indexQ].poll();
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < centerX-offset-1+indexQ || nx >= (centerX+offset)-indexQ)  {
						d += 1;
						nx = x + dx[d];
						ny = y + dy[d];
					}
					if (ny < centerY-offset-1+indexQ || ny >= (centerY+offset)-indexQ) {
						d += 1;
						nx = x + dx[d];
						ny = y + dy[d];
					}
					x = nx;
					y = ny;
				}
				
			}
			
			// 최소값 answer 찾기
			for (int[] row: board) {
				int sum = 0;
				for (int item: row) {
					sum += item;
				}
				answer = Math.min(answer, sum);
			}
			
			// 원본 배열 불러오기
			for (int x=0; x<board.length; x++) {
				for (int y=0; y<board[0].length; y++) {
					board[x][y] = originBoard[x][y];			
				}
			}
	    	
	        return;
	    }
	 
	    for (int i=depth; i<n; i++) {
	        swap(arr, depth, i);
			permutation(arr, board, originBoard, query, depth + 1, n, r);
	        swap(arr, depth, i);
	    }
	}
	 
	static void swap(int[] arr, int depth, int i) {
	    int temp = arr[depth];
	    arr[depth] = arr[i];
	    arr[i] = temp;
	}
}
