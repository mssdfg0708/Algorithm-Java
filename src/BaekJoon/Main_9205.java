// 백준 9205 맥주 마시면서 걸어가기
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_9205 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		int Test = Integer.parseInt(st.nextToken());
		
		for (int test=1; test<=Test; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int[] start = new int[] {x, y};
			
			List<int[]> storeList = new ArrayList<>();
			for (int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				storeList.add(new int[] {x, y});
			}
			boolean[] visited = new boolean[storeList.size()];
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			int[] endPoint = new int[] {x, y};
			
			
			String answer = bfs(start, storeList, visited, endPoint);
			System.out.println(answer);
		}
		
	}

	private static String bfs(int[] start, List<int[]> storeList, boolean[] visited, int[] endPoint) {
		String answer = "sad";
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		
		while (!q.isEmpty()) {
			int[] point = q.poll();
			int x = point[0];
			int y = point[1];
			
			int distance = Math.abs(endPoint[0] - x) + Math.abs(endPoint[1] - y);
			if (distance <= 1000) {
				answer = "happy";
				break;
			}
			
			for (int index=0; index<storeList.size(); index++) {
				if (visited[index])
					continue;
				distance = Math.abs(storeList.get(index)[0] - x) + Math.abs(storeList.get(index)[1] - y);
				if (distance <= 1000) {
					visited[index] = true;
					q.offer(new int[] {storeList.get(index)[0], storeList.get(index)[1]});
				}
				
			}
			
		}
		return answer;
	}

}






