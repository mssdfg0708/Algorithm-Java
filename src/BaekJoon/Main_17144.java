// 백준 17144 미세먼지 안녕!
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main_17144 {
	
	static int[] dx = new int[] {0, -1, 0, 1};
	static int[] dy = new int[] {1, 0, -1, 0};
	
	static int[][] board;
	static int[][] spreadBoard;
	
	static int topAirCleaner;
	static boolean isTopFill = false;
	static int bottomAirCleaner;
	
	static int TIME;
	static int X;
	static int Y;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		TIME = Integer.parseInt(st.nextToken());
		
		board = new int[X][Y];
		
		// 데이터 입력
		for (int x=0; x<X; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y=0; y<Y; y++) {
				board[x][y] = Integer.parseInt(st.nextToken());
				// 공기 청정기 좌표 입력
				if (board[x][y] == -1) {
					getAirCleanerLocation(x);	
				}
			}
		}
		
		// 시간 진행
		for (int time = 0; time < TIME; time++) {
			
			spreadBoard = new int[X][Y];
			
			// 미세먼지 동시 전파
			for (int x=0; x<X; x++) {
				for (int y=0; y<Y; y++) {
					if (board[x][y] > 0) {
						spread(x, y);
					}
				}
			}
			
			// 미세먼지 전파 계산
			calculateSpread();
			
			// 공기청정기 가동
			clockWise();
			otherWise();

		}
		
		// 결과 출력
		int answer = 0;
		for (int x=0; x<X; x++) {
			for (int y=0; y<Y; y++) {
				if (board[x][y] > 0) {
						answer += board[x][y];
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void getAirCleanerLocation(int x) {
		if (!isTopFill) {
			topAirCleaner = x;	
			isTopFill = true;
		}
		else {
			bottomAirCleaner = x;	
		}
	}
	
	private static void otherWise() {
		int x = bottomAirCleaner;
		int y = 0;
		int d = 4;
		int save = -1;
		
		while (true) {
			int nx = x + dx[d % 4];
			int ny = y + dy[d % 4];
			
			if (nx < 0 || nx >= X) {
				d -= 1;
				continue;
			}
			if (ny < 0 || ny >= Y) {
				d -= 1;
				continue;
			}
			if (board[nx][ny] == -1)
				break;
			
			int nextSave = board[nx][ny];
			if (save == -1)
				board[nx][ny] = 0;
			else
				board[nx][ny] = save;
			save = nextSave;
			
			x = nx;
			y = ny;

		}
	}

	private static void clockWise() {
		int x = topAirCleaner;
		int y = 0;
		int d = 0;
		int save = -1;
		
		while (true) {
			int nx = x + dx[d % 4];
			int ny = y + dy[d % 4];
			
			if (nx < 0 || nx >= X) {
				d += 1;
				continue;
			}
			if (ny < 0 || ny >= Y) {
				d += 1;
				continue;
			}
			if (board[nx][ny] == -1)
				break;
			
			int nextSave = board[nx][ny];
			if (save == -1)
				board[nx][ny] = 0;
			else
				board[nx][ny] = save;
			save = nextSave;
			
			x = nx;
			y = ny;

		}
	}

	private static void calculateSpread() {
		for (int x=0; x<X; x++) {
			for (int y=0; y<Y; y++) {
				board[x][y] += spreadBoard[x][y];
			}
		}
	}

	private static void spread(int x, int y) {
		
		int spreadAmount = board[x][y] / 5;
		
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= X)
				continue;
			if (ny < 0 || ny >= Y)
				continue;
			if (board[nx][ny] == -1)
				continue;
			
			spreadBoard[x][y] -= spreadAmount;
			spreadBoard[nx][ny] += spreadAmount;

		}
		
	}

}

// 현재 미세먼지 양과 가중치 양을 분리
// 미세먼지 동시 전파 -> 미세먼지 전파 계산 -> 공기청정기 가동 순서로 진행

// 데이터 입력
// 값이 1이상이라면 전파
// 가중치 board 에 전파 가중치 입력
// 전파 실행
// 공기청정기 가동




