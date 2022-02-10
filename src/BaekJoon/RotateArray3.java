// 백준 16935 배열 돌리기 3
package BaekJoon;

import java.io.*;
import java.util.*;

public class RotateArray3 {
	
	static int N;
	static int M;
	static int LEN;
	static int COUNT_LIMIT;
	
	static int[][] CUR_MAP;
	static int[][] NEXT_MAP;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer line = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(line.nextToken());
		M = Integer.parseInt(line.nextToken());
		COUNT_LIMIT = Integer.parseInt(line.nextToken());
		
		// 초기 배열 입력
		LEN = Math.max(N, M);
		int[][] map0 = new int[LEN][LEN];
		for (int x=0; x<N; x++) {
			line = new StringTokenizer(br.readLine());
			for (int y=0; y<M; y++) {
				map0[x][y] = Integer.parseInt(line.nextToken());
			}
		}
		
		// 배열 추가 생성
		int[][] map1 = new int[LEN][LEN];
		int[][][] container = {map0, map1};
		
		// 연산 수행
		line = new StringTokenizer(br.readLine());
		for (int count=0; count<COUNT_LIMIT; count++) {
			
			if (count % 2 == 0) {
				CUR_MAP = container[0];
				NEXT_MAP = container[1];
			}
			else {
				CUR_MAP = container[1];
				NEXT_MAP = container[0];
			}
			
			int query = Integer.parseInt(line.nextToken());
			
			if (query==1) {
				query1();
				continue;
			}
			if (query==2) {
				query2();
				continue;
			}
			if (query==3) {
				query3();
				continue;
			}
			if (query==4) {
				query4();
				continue;
			}
			if (query==5) {
				query5();
				continue;
			}
			if (query==6) {
				query6();
				continue;
			}
		}
		
		// 결과 출력
		for (int[] row: NEXT_MAP) {
			StringBuilder sb = new StringBuilder();
			for (int item: row) {
				if (item != 0)
					sb.append(item).append(" ");
			}
			if (sb.length() > 1) {
				System.out.println(sb);
			}
		}
	}
	
	static void query1() {
		
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[LEN-1-x][y] = CUR_MAP[x][y];
			}
		}
	}
	
	static void query2() {
		
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[x][LEN-1-y] = CUR_MAP[x][y];
			}
		}
	}
	
	static void query3() {
		
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[y][LEN-1-x] = CUR_MAP[x][y];
			}
		}
		
		int temp = N;
		N = M;
		M = temp;
	}
	
	static void query4() {
		
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[LEN-1-y][x] = CUR_MAP[x][y];
			}
		}
		
		int temp = N;
		N = M;
		M = temp;
	}
	
	static void query5() {
		
		int xOffest = 0;
		int yOffset = 0;
		
		if (CUR_MAP[0][LEN-1] == 0 && (CUR_MAP[0][0] == 0)) {
			xOffest = M-N;
		}
		if (CUR_MAP[LEN-1][0] == 0 && (CUR_MAP[0][0] == 0)) {
			yOffset = N-M;
		}
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[x][y] = 0;
			}
		}

		
		for (int x=0; x<N; x++) {
			for (int y=0; y<M; y++) {
				// 그룹 1
				if (x<N/2 && y<M/2) {
					NEXT_MAP[x][y+M/2] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 2
				if (x<N/2 && y>=M/2) {
					NEXT_MAP[x+N/2][y] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 3
				if (x>=N/2 && y>=M/2) {
					NEXT_MAP[x][y-M/2] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 4
				if (x>=N/2 && y<M/2) {
					NEXT_MAP[x-N/2][y] = CUR_MAP[x+xOffest][y+yOffset];
				}
			}
		}
	}
	
	static void query6() {
		
		int xOffest = 0;
		int yOffset = 0;
		
		if (CUR_MAP[0][LEN-1] == 0 && (CUR_MAP[0][0] == 0)) {
			xOffest = M-N;
		}
		if (CUR_MAP[LEN-1][0] == 0 && (CUR_MAP[0][0] == 0)) {
			yOffset = N-M;
		}
		for (int x=0; x<LEN; x++) {
			for (int y=0; y<LEN; y++) {
				NEXT_MAP[x][y] = 0;
			}
		}

		
		for (int x=0; x<N; x++) {
			for (int y=0; y<M; y++) {
				// 그룹 1
				if (x<N/2 && y<M/2) {
					NEXT_MAP[x+N/2][y] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 2
				if (x<N/2 && y>=M/2) {
					NEXT_MAP[x][y-M/2] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 3
				if (x>=N/2 && y>=M/2) {
					NEXT_MAP[x-N/2][y] = CUR_MAP[x+xOffest][y+yOffset];
				}
				// 그룹 4
				if (x>=N/2 && y<M/2) {
					NEXT_MAP[x][y+M/2] = CUR_MAP[x+xOffest][y+yOffset];
				}
			}
		}
	}
}






