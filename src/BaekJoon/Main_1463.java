// 백준 1463 1로 만들기
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_1463 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int[] dp = new int[x+1];
		
		for (int index = 2; index <= x; index++) {
			dp[index] = dp[index-1] + 1;
			if (index % 2 == 0) {
				dp[index] = Math.min(dp[index], dp[index/2] + 1);
			}
			if (index % 3 == 0) {
				dp[index] = Math.min(dp[index], dp[index/3] + 1);
			}
		}

		System.out.println(dp[x]);
	}

}
