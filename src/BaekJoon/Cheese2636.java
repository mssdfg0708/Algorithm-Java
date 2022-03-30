// 백준 2336 치즈
package BaekJoon;

import java.io.*;
import java.util.*;

public class Cheese2636 {

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};

    static int[][] cheeses;
    static int maxCost = Integer.MIN_VALUE;
    static int time = 0;
    static int X;
    static int Y;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        cheeses = new int[X][Y];

        for (int x=0; x<X; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y=0; y<Y; y++) {
                cheeses[x][y] = Integer.parseInt(st.nextToken());
                if (cheeses[x][y] == 1) {
                    cheeses[x][y] = -1;
                }
            }
        }

        while (checkCheese()) {
            time += 1;
            List<int[]> contactList = new ArrayList<>();

            for (int x=0; x<X; x++) {
                for (int y=0; y<Y; y++) {
                    if (cheeses[x][y] == -1 && isContactWithAir(x, y)) {
                        contactList.add(new int[] {x, y});
                    }
                }
            }

            for (int[] contact: contactList) {
                int x = contact[0];
                int y = contact[1];
                cheeses[x][y] = time;
            }
        }

        for (int x=0; x<X; x++) {
            for (int y=0; y<Y; y++) {
                maxCost = Math.max(maxCost, cheeses[x][y]);
            }
        }

        int survivedCheeses = 0;

        for (int x=0; x<X; x++) {
            for (int y=0; y<Y; y++) {
                if (cheeses[x][y] >= maxCost) {
                    survivedCheeses += 1;
                }
            }
        }

        System.out.println(maxCost);
        System.out.println(survivedCheeses);
    }

    private static boolean checkCheese() {
        for (int x=0; x<X; x++) {
            for (int y=0; y<Y; y++) {
                if (cheeses[x][y] == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isContactWithAir(int rootX, int rootY) {

        boolean[][] visited = new boolean[X][Y];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{rootX, rootY});
        visited[rootX][rootY] = true;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int x = info[0];
            int y = info[1];

            for (int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= X)
                    return true;
                if (ny < 0 || ny >= Y)
                    return true;
                if (visited[nx][ny])
                    continue;
                if (cheeses[nx][ny] < 0)
                    continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }

        }
        return false;
    }

}




