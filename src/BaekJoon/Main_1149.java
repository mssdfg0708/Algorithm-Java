// 백준 1149 RGB 거리
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_1149 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][3];
		
		for (int house=1; house<=N; house++) {
			st = new StringTokenizer(br.readLine());
			for (int color=0; color<3; color++) {
				dp[house][color] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int house=1; house<=N; house++) {
			for (int color=0; color<3; color++) {
				int cost = findMinCost(dp, house, color);
				dp[house][color] = cost + dp[house][color];
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int color=0; color<3; color++) {
			answer = Math.min(answer, dp[N][color]);
		}
		
		System.out.println(answer);
	}

	private static int findMinCost(int[][] costs, int house, int color) {
		int cost = Integer.MAX_VALUE;
		for (int lastColor=0; lastColor<3; lastColor++)	 {
			if (color != lastColor) {
				cost = Math.min(cost, costs[house-1][lastColor]);
			}
		}
		return cost;
	}

}

// 2차원 배열 활용 dp
// R, G, B = 0, 1, 2

// dp[2][3] = 2번 집을 Blue(3) 로 칠하는 dp
// dp[5][1] = 5번 집을 Red(1) 로 칠하는 dp

// dp[h][c] = min(dp[h-1][d], dp[h-1][d]) where (d != c)





