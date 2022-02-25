// 백준 15686 치킨배달
package BaekJoon;

import java.io.*;
import java.util.*;

public class ChickenDelivery {
	
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int SURVIVE_NUM;
	static int[][] map;
	
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chickens = new ArrayList<>();
	static boolean[] picked;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		SURVIVE_NUM = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int x=0; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y=0; y<N; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				
				if (map[x][y] == 1)
					houses.add(new int[] {x, y});
				else if (map[x][y] == 2)
					chickens.add(new int[] {x, y});
			}
		}
		
		picked = new boolean[chickens.size()];
		combination(0, chickens.size(), SURVIVE_NUM);
		System.out.println(answer);
	}

	private static void combination(int start, int size, int r) {

		if (r == 0) {	
			answer = Math.min(answer, checkDistance());
			return;
		}
		
		for (int index=start; index<size; index++) {
			picked[index] = true;
			combination(index+1, size, r-1);
			picked[index] = false;
		}
	}

	private static int checkDistance() {

		int sum = 0;
		List<int[]> survives = new ArrayList<>();

		for (int i = 0; i < picked.length; i++) {
			if (picked[i])
				survives.add(chickens.get(i));
		}
		
		for (int[] house: houses) {
			int houseDistance = Integer.MAX_VALUE;
			for (int[] survive: survives) {
				int distance = Math.abs(house[0]-survive[0]) + Math.abs(house[1]-survive[1]);
				houseDistance = Math.min(houseDistance, distance);
			}	
			sum += houseDistance;
		}
		
		return sum;
	}
}
