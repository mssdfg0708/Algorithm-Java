// 백준 3109 빵집
package BaekJoon;

import java.io.*;
import java.util.*;

public class Bakery {
	
	static int N;
	static int M;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		
		for (int x=0; x<N; x++) {
			String line = br.readLine();
			for (int y=0; y<M; y++) {
				if (line.charAt(y) == '.') {
					map[x][y] = true;
				}
			}
		}
		
		for (int x=0; x<N; x++) {
			int y = 0;
			boolean isFinish = dfs(map, x, y);
			if (isFinish)
				count += 1;
		}
		
		System.out.println(count);
	}
	
	public static boolean dfs(boolean[][] map, int x, int y) {
		
		int[] dx = new int[]{-1, 0, 1};

		if (y >= M-1) {
			return true;
		}

		for (int d=0; d<3; d++) {
			int nx = x + dx[d];
			int ny = y + 1;
			
			if (nx < 0 || nx >= N)
				continue;
			if (ny < 0 || y >= M)
				continue;
			if (!map[nx][ny])
				continue;
			
			map[nx][ny] = false;
			
			if (dfs(map, nx, ny)) {
				return true;
			}
		}
		return false;
	}

}

/*
 * 가장 위쪽 행 부터 DFS를 이용하여 파이프 설치
 * 파이프 설치가 완료되면 최대한 위쪽으로 설치되도록 DFS 순서 만들기
 * 도착 완료 순간 DFS를 Return 하여 이외 경로의 Visited를 false로 유지
 * 
 * 가장 아래쪽 파이프까지 반복
 *
 * 0 0 0 0 x 0 0
 * 0 0 0 0 x 0 0
 * 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0
 * 0 0 x 0 0 0 0
 * 0 0 x 0 0 0 0
 */