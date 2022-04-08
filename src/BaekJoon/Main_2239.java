// 백준 2239 스도쿠
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_2239 {
	
	static int[][] board = new int[9][9];
	static List<Point> pointList = new ArrayList<>();
	static boolean isAnswer = false;
	
	static class Point {
		int x;
		int y;
		int zoneX;
		int zoneY;
		List<Integer> candidateNumbers = new ArrayList<>();
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.zoneX = (x/3) * 3;
			this.zoneY = (y/3) * 3;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", zoneX=" + zoneX + ", zoneY=" + zoneY + ", candidateNumbers="
					+ candidateNumbers + "]";
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for (int x=0; x<9; x++) {
			String line = br.readLine();
			for (int y=0; y<9; y++) {
				board[x][y] = (line.charAt(y)) - '0';
			}
		}
		
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				if (board[x][y] == 0) {
					Point point = new Point(x, y);
					makePoint(point);
					pointList.add(point);
				} 
			}
		}

		// dfs(int pointIndex) 
		dfs(0);
		
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				sb.append(board[x][y]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}



	private static void dfs(int pointIndex) {
		
		if (isAnswer)
			return;
		
		if (pointIndex == pointList.size()) {
			isAnswer = true;
			return;
		}
		
		Point point = pointList.get(pointIndex);
		List<Integer> targetList = point.candidateNumbers;
		
		int px = point.x;
		int py = point.y;
		
		for (Integer num : targetList) {
			boolean isDuplicate = false;
			
			if (isAnswer)
				return;
			
			for (int x=0; x<9; x++) {
				if (board[x][py] == num) {
					isDuplicate = true;
					break;
				}
			}
			
			if (isDuplicate)
				continue;
			
			for (int y=0; y<9; y++) {
				if (board[px][y] == num) {
					isDuplicate = true;
					break;
				}
			}
			
			if (isDuplicate)
				continue;
			
			for (int x=point.zoneX; x<point.zoneX+3; x++) {
				for (int y=point.zoneY; y<point.zoneY+3; y++) {
					if (isDuplicate)
						break;
					if (board[x][y] == num) {
						isDuplicate = true;
						break;
					}
				}		
			}
			
			if (isDuplicate)
				continue;			
			
			board[px][py] = num;
			dfs(pointIndex+1);
			
			if (isAnswer)
				return;
			
			board[px][py] = 0;
		}
		
	}

	private static void makePoint(Point point) {
		List<Integer> targetList = point.candidateNumbers;
		
		for (int n=1; n<10; n++) {
			targetList.add(n);
		}
	}
	
}


// DFS 활용해 사전식으로 앞서는 것 부터 계산
// 각 칸 마다 가능한 숫자 후보를 모두 고름




