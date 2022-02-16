// 백준 2138 전구와 스위치

package BaekJoon;

import java.io.*;
import java.util.*;

public class LightAndSwitch {
	
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		boolean[] Lights = new boolean[N];
		
		String startString = br.readLine();
		String answerString = br.readLine();
		boolean[] start = new boolean[N];
		boolean[] answer = new boolean[N];
		
		for (int index=0; index<N; index++) {
			if (startString.charAt(index) == '0')
				start[index] = false;
			else
				start[index] = true;
		}
		
		for (int index=0; index<N; index++) {
			if (answerString.charAt(index) == '0')
				answer[index] = false;
			else
				answer[index] = true;
		}
		
		List<boolean[]> conditions = new ArrayList<>();
		conditions.add(Arrays.copyOf(start, N));
		
		start[0] = !start[0];
		start[1] = !start[1];
		conditions.add(Arrays.copyOf(start, N));
		
		for (int c=0; c<2; c++) {
			
			Lights = conditions.get(c);
			int onOff = c;
			
			for (int index=1; index<N-1; index++) {
				if (Lights[index-1] != answer[index-1]) {
					Lights[index-1] = !Lights[index-1];
					Lights[index] = !Lights[index];
					Lights[index+1] = !Lights[index+1];
					onOff += 1;
				}
			}
			
			if (Lights[N-2] != answer[N-2]) {
				Lights[N-2] = !Lights[N-2];
				Lights[N-1] = !Lights[N-1];
				onOff += 1;
			}
			
			if (Arrays.equals(Lights, answer)) {
				result = Math.min(result, onOff);
			}
		}
		
		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
		
	}

}

/* 00000000000
 * 11000000011
 * 00000000011
 * 11000000000
 * 
 * 4 가지 경우에서 시작하여
 * Index 기준 왼쪽을 맞춘다
 * 
 * 101
 * 010
 * 
 */
