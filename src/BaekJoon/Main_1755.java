// 백준 1755 숫자놀이
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_1755 {

	public static void main(String[] args) throws IOException {

		String[] converter = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] array = new String[M-N+1];

		for (int index=N; index<=M; index++) {
			String target = String.valueOf(index);
			StringBuilder value = new StringBuilder();

			for (int idx=0; idx<target.length(); idx++) {
				int targetIndex = target.charAt(idx) - '0';
				value.append(converter[targetIndex]);
				value.append(" ");
			}
			value.deleteCharAt(value.length()-1);
			array[index-N] = (value.toString());
		}

		Arrays.sort(array);

		int[] answer = new int[array.length];

		for (int index=0; index<array.length; index++) {
			String targetString = array[index];
			st = new StringTokenizer(targetString);
			StringBuilder value = new StringBuilder();
			
			while (st.hasMoreTokens()) {
				String target = st.nextToken();
				for (int indexC=0; indexC<converter.length; indexC++) {
					if (converter[indexC].equals(target)) {
						value.append(indexC);
						break;
					}
				}
			}
			
			answer[index] = Integer.parseInt(value.toString());
		}

		for (int index=0; index<answer.length; index++) {
			System.out.print(answer[index] + " ");
			if (index % 10 == 9) {
				System.out.println();
			}
		}
	}

}
