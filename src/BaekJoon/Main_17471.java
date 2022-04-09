// 백준 17471 게리맨더링
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_17471 {
	
	static int N;
	static int[] peopleDistribute;
	static int[][] graph;
	static boolean[] sectionList;

	static List<Integer> Truelist = new ArrayList<>();
	static List<Integer> Falselist = new ArrayList<>();
	static int MIN_DIFFER = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		peopleDistribute = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int index=1; index<N+1; index++)
			peopleDistribute[index] = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][];
		graph[0] = new int[0];
		
		for (int index=1; index<N+1; index++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			graph[index] = new int[len];
			
			for (int connectIdx=0; connectIdx<len; connectIdx++) {
				graph[index][connectIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		sectionList = new boolean[N+1];
		
		// sectionCombination(int index)
		sectionCombination(1);
		
		if (MIN_DIFFER == Integer.MAX_VALUE)
			MIN_DIFFER = -1;
		
		System.out.println(MIN_DIFFER);
	}

	private static void sectionCombination(int index) {
		if (index >= N+1) {
			if (isTrueConnect() && isFalseConnect()) {
				calculatePeople();
			}
			return;
		}
		
		sectionList[index] = false;
		sectionCombination(index+1);
		sectionList[index] = true;
		sectionCombination(index+1);
	}

	// isFalseConnect()
	private static boolean isFalseConnect() {
		int root = -1;
		
		for (int index=1; index<N+1; index++) {
			if (!sectionList[index]) {
				root = index;
				break;
			}
		}
		if (root == -1)
			return false;

		Falselist = new ArrayList<>();
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		visited[root] = true;
		q.offer(root);
		Falselist.add(root);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if (!sectionList[next] && !visited[next]) {
					visited[next] = true;
					q.offer(next);
					Falselist.add(next);
				}
			}
		}
		
		for (int index=1; index<N+1; index++) {
			if (!sectionList[index]) {
				if (!Falselist.contains(index))
					return false;
			}
		}

		return true;
	}
	
	// isTrueConnect()
	private static boolean isTrueConnect() {
		int root = -1;
		
		for (int index=1; index<N+1; index++) {
			if (sectionList[index]) {
				root = index;
				break;
			}
		}
		if (root == -1)
			return false;

		Truelist = new ArrayList<>();
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		visited[root] = true;
		q.offer(root);
		Truelist.add(root);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if (sectionList[next] && !visited[next]) {
					visited[next] = true;
					q.offer(next);
					Truelist.add(next);
				}
			}	
		}
		
		for (int index=1; index<N+1; index++) {
			if (sectionList[index]) {
				if (!Truelist.contains(index))
					return false;
			}
		}
		
		return true;
	}

	private static void calculatePeople() {
		int trueSum = 0;
		int falseSum = 0;
		
		for (int index=1; index<N+1; index++) {
			if (sectionList[index]) {
				trueSum += peopleDistribute[index];
			}
			else {
				falseSum += peopleDistribute[index];
			}
		}
		
		int differ = Math.abs(trueSum - falseSum);
		MIN_DIFFER = Math.min(MIN_DIFFER, differ);
	}

}

// boolean[] Section // True False 로 구분
// 나누어진 구역이 유효한지 확인 BFS 활용
// 유효하다면 인구 차이 계산




