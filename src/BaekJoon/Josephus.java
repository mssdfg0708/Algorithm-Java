// 백준 1158 요세푸스 문제

package BaekJoon;

import java.io.*;
import java.util.*;

public class Josephus {

	public static void main(String[] args) throws IOException {
		
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer line = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(line.nextToken());
		int K = Integer.parseInt(line.nextToken());
		
		List<Integer> list = new ArrayList<>();
		for (int num=1; num<=N; num++) {
			list.add(num);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		// List 가 비워질때까지 반복
		int index = 0;
		while (!list.isEmpty()) {
			// K 회 반복
			index = checkIndex(index, list);
			index = (index + K - 1) % list.size();
			// 결과 저장
			sb.append(list.get(index)).append(", ");
			list.remove(index);
		}	
		// 결과 출력
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}
	
	// index OutOfRange 검사
	static int checkIndex(int index, List<Integer> list) {
		
		if (index >= list.size()) {
			index=0;
		}
		return index;
	}
}
