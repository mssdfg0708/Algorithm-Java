// 백준 1987 알파벳
package BaekJoon;

import java.io.*;
import java.util.*;

public class Alphabet {
	
	static Stack<Character> stack = new Stack<>();
	static Set<Character> set = new HashSet<>();
	static int answer = Integer.MIN_VALUE;
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {-1, 0, 1, 0};
	static int X;
	static int Y;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[X][Y];
		for (int x=0; x<X; x++) {
			String line = br.readLine();
			for (int y=0; y<Y; y++) {
				map[x][y] = line.charAt(y);
			}
		}
		stack.push(map[0][0]);
		set.add(map[0][0]);
		dfs(map, 0, 0);
		System.out.println(answer);
	}
	
	static void dfs(char[][] map, int x, int y) {
		int len = stack.size();
		answer = Math.max(answer, len);
		
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= X)
				continue;
			if (ny < 0 || ny >= Y)
				continue;
			if (set.contains(map[nx][ny]))
				continue;

			stack.push(map[nx][ny]);
			set.add(map[nx][ny]);
			dfs(map, nx, ny);
			char info = stack.pop();
			set.remove(info);
		}
		
	}

}
