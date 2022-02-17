// 백준 1992 쿼드트리
package BaekJoon;

import java.io.*;

public class QuadTree {
	
	static char[][] tree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		tree = new char[N][N];
		
		for (int x=0; x<N; x++) {
			String line =(br.readLine());
			for (int y=0; y<N; y++) {
				tree[x][y] = line.charAt(y);
			}
		}
		
		quadTree(0, 0, N);
		System.out.println(sb);
	}
	
	public static void quadTree(int startX, int startY, int N) {
		
		char color = tree[startX][startY];
		
		for (int x=startX; x<startX+N; x++) {
			for (int y=startY; y<startY+N; y++) {
				if (color != tree[x][y]) {
					sb.append("(");
					quadTree(startX, startY, N/2);
					quadTree(startX, startY+N/2, N/2);
					quadTree(startX+N/2, startY, N/2);
					quadTree(startX+N/2, startY+N/2, N/2);
					sb.append(")");
					return;
				}
			}
		}
		sb.append(color);
	}
}
