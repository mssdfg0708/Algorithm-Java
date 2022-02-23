// 백준 16236 아기상어
package BaekJoon;

import java.io.*;
import java.util.*;

public class BabyShark {
	
	// 물고기 크기가 저장된 map
	static int[][] map;
	static int N;
	
	static int sharkX = 0;
	static int sharkY = 0;
	static int sharkSize = 2;
	static int countToSizeUp = 2;
	static int totalTime = 0;
	
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {

		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int x=0; x<N; x++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int y=0; y<N; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if (map[x][y] == 9) {
					sharkX = x;
					sharkY = y;
					map[x][y] = 0;
				}
			}
		}
		
		// 먹을 수 있는 물고기가 존재한다면 반복
		while (true) {
			// 현재 위치에서 BFS 실행하여 최단거리에 위치한 먹이 후보 가져오기
			List<int[]> candidates = bfs(sharkX, sharkY, 0);
			
			// 후보가 없다면 종료
			if (candidates.isEmpty()) {
				System.out.println(totalTime);
				break;
			}
			
			// 후보들을  x값, y값 순서로 정렬
			candidates.sort(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					int differ = o1[0] - o2[0];
					if (differ != 0)
						return differ;
					differ = o1[1] - o2[1];
					return differ;
				}
			});
			
			// 가장 좌상단에 위치한 후보 선택
			int[] target = candidates.get(0);
			int x = target[0];
			int y = target[1];
			int time = target[2];
			
			map[x][y] = 0;
			totalTime += time;
			sharkX = x;
			sharkY = y;
			
			if (sharkSize < 8) {
				countToSizeUp -= 1;
				if (countToSizeUp == 0) {
					sharkSize += 1;
					countToSizeUp = sharkSize;
				}
			}
		}
	}

	// BFS 모듈
	private static List<int[]> bfs(int x, int y, int time) {
		
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> candidates = new ArrayList<>();
		
		int breakTime = Integer.MAX_VALUE;
		
		visited[x][y] = true;
		q.offer(new int[] {x, y, time});
		
		while (!q.isEmpty()) {

			int[] info = q.poll();
			x = info[0];
			y = info[1];
			time = info[2];
			
			if (time > breakTime)
				break;
			
			if (map[x][y] > 0 && map[x][y] < sharkSize) {
				candidates.add(new int[] {x, y, time});
				breakTime = time;
			}
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N)
					continue;
				if (ny < 0 || ny >= N)
					continue;
				if (map[nx][ny] > sharkSize)
					continue;
				if (visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, time+1});
			}
		}
		
		return candidates;
	}
}


/*

상어의 크기는 7까지만 증가한다

bfs를 이용하는데 먹을 수 있는 물고기를 발견한 순간 breakTime 지정
같은 거리에 있는 물고기들을 후보List 에 저장하고 x값, y값 순서로 정렬
가장 앞의 물고기를 먹고 time += breakDepth

먹을 수 있는 물고기가 없을 때까지 반복

*/





