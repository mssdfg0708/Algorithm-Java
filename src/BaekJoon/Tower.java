// 백준 2493 탑

package BaekJoon;

import java.io.*;
import java.util.*;

public class Tower {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Tower = Integer.parseInt(br.readLine());
		int[] heights = new int[Tower];
		int[] received = new int[Tower];
		Stack<Integer[]> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int index=0; index<Tower; index++) {
			heights[index] = Integer.parseInt(st.nextToken());
		}
	
		for (int index=Tower-1; index>=0; index--) {

			Integer[] info = {heights[index], index};
			
			if (stack.isEmpty()) {
				stack.add(info);
				continue;
			}
			
			while (!stack.isEmpty()) {
				
				if (stack.peek()[0] >= heights[index])
					break;
		
				received[stack.pop()[1]] = index+1;
			}
			stack.add(info);
		}
		
		//출력
		for (int result: received) {
			System.out.print(result + " ");
		}
	}
}
