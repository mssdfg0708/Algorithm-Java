// 백준 12869 뮤탈리스크
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_12869 {
	
	static int[] d1 = new int[] {-9, -9, -3, -3, -1, -1};
	static int[] d2 = new int[] {-3, -1, -9, -1, -9, -3};
	static int[] d3 = new int[] {-1, -3, -1, -9, -3, -9};
	
	static int[][][] dp;
	static int minCount = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] scvList = new int[3];
		
		for (int i=0; i<N; i++) {
			scvList[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[61][61][61];
		
		dfs(scvList, 0);
		System.out.println(minCount);
		
	}

	private static void dfs(int[] scvList, int count) {

		int scv1 = scvList[0];
		int scv2 = scvList[1];
		int scv3 = scvList[2];
		
		if (count >= minCount)
			return;
		
		if (dp[scv1][scv2][scv3] != 0 && count >= dp[scv1][scv2][scv3]) 
			return;
		
		dp[scv1][scv2][scv3] = count;
		
		if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
			minCount = Math.min(minCount, count);
			return;
		}
		
		for(int i=0;i<6;i++) {
			dfs(new int[] {Math.max(scv1 + d1[i], 0),
					Math.max(scv2 + d2[i], 0),
					Math.max(scv3 + d3[i], 0)}, count+1);
		}
	}

}

// DP[scv1][scv2][scv3]
