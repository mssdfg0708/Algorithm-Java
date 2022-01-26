package BaekJoon;

import java.util.*;

public class TetroMino {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();
        int[][] board = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int x=0; x<N; x++) {
            for (int y=0; y<M; y++) {
                board[x][y] = scan.nextInt();
            }
        }

        int result = 0;
        for (int x=0; x<N; x++) {
            for (int y=0; y<M; y++) {
                visited[x][y] = true;
                List<Integer> local = new ArrayList<>();
                dfs(visited, board, x, y, 0, board[x][y], local);
                visited[x][y] = false;
                if (!(local.isEmpty())) {
                    int localMax = Collections.max(local);
                    result = Math.max(result, localMax);
                }
            }
        }
        System.out.println(result);
    }

    static void dfs(boolean[][] visited, int[][] board, int x, int y, int depth, int sum, List<Integer> local) {
        if (depth >= 3) {
            local.add(sum);
            return;
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= board.length)
                continue;
            if (ny < 0 || ny >= board[0].length)
                continue;
            if (visited[nx][ny])
                continue;

            // ㅗ 모양에 대한 EdgeCase
            if (depth == 1) {
                visited[nx][ny] = true;
                dfs(visited, board, x, y, depth+1, sum + board[nx][ny], local);
                visited[nx][ny] = false;
            }

            visited[x][y] = true;
            dfs(visited, board, nx, ny, depth+1, sum + board[nx][ny], local);
            visited[x][y] = false;
        }
    }
}
