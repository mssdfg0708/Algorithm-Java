// 백준 15961 회전초밥
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_15961 {
	
	static Queue<Integer> q;
	static int[] COUNT_ARRAY;
	static int[] SUSHI_ARRAY;
	static int SELECT_TYPES;
	
	static int DISHES;
	static int MAX_SUSHI_TYPE;
	static int K;
	static int COUPON;
	
	static int ANSWER;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		DISHES = Integer.parseInt(st.nextToken());
		MAX_SUSHI_TYPE = Integer.parseInt(st.nextToken()) + 1;
		K = Integer.parseInt(st.nextToken());
		COUPON = Integer.parseInt(st.nextToken());
		
		q = new ArrayDeque<>();
		COUNT_ARRAY = new int[MAX_SUSHI_TYPE];
		SUSHI_ARRAY = new int[DISHES + K];
		SELECT_TYPES = 0;
		
		for (int index=0; index<DISHES; index++) {
			SUSHI_ARRAY[index] = Integer.parseInt(br.readLine());
		}

		if (K >= 0) System.arraycopy(SUSHI_ARRAY, 0, SUSHI_ARRAY, DISHES, K);
		
		for (int index=0; index<K; index++) {
			offerModule(index);
		}
		ANSWER = SELECT_TYPES;
		
		for (int index=K; index<SUSHI_ARRAY.length; index++) {
			offerModule(index);
			pollModule();
			
			int selectedTypes = SELECT_TYPES;
			if (checkCoupon())
				selectedTypes += 1;
			ANSWER = Math.max(ANSWER, selectedTypes);
		}
		System.out.println(ANSWER);
	}

	private static boolean checkCoupon() {

		return COUNT_ARRAY[COUPON] == 0;
	}

	private static void pollModule() {
		int target = q.poll();
		COUNT_ARRAY[target] -= 1;
		if (COUNT_ARRAY[target] == 0)
			SELECT_TYPES -= 1;
	}

	private static void offerModule(int index) {
		int target = SUSHI_ARRAY[index];
		if (COUNT_ARRAY[target] == 0)
			SELECT_TYPES += 1;
		COUNT_ARRAY[target] += 1;
		q.offer(target);
	}

}

// 담겨 있는 초밥 Queue q
// index 번호 초밥이 몇개 담겨있는지 나타내는 Array COUNT_ARRAY
// 현재 담겨 있는 초밥 종류의 수 int SELECT_TYPES
// Queue 에  들어가는 초밥과 나가는 초밥 2개만 확인하여 연산량 줄이기 가능




