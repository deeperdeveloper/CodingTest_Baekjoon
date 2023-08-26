import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Main {

    static int n;
    static int[][] board;
    static int answer;
    static int[] dx = {-1,-1,1,1};
    static int[] dy = {-1,1,-1,1};

    public static void dfs(int level) {
        if(level == n) {
            answer++;
            return;
        }
        int visit = 1;
        int cancelVisit = 0;
        for(int i=0; i<n; i++) {
            if(board[level][i] == 0) { //board[level][i]에 퀸을 위치시킨다.
                columnVisit(i,visit);
                diagonalVisit(level, i, visit);
                dfs(level+1);
                columnVisit(i,cancelVisit);
                diagonalVisit(level,i,cancelVisit);

            }
        }
    }

    private static void columnVisit(int columnIdx, int visitOrCancel) {
        if(visitOrCancel == 1) {
            for(int k=0; k<n; k++) board[k][columnIdx]++;
        } else {
            for(int k=0; k<n; k++) board[k][columnIdx]--;
        }
    }

    private static void diagonalVisit(int x, int y, int visitOrCancel) {
        for(int p=0; p<n; p++) {
            for(int t=0; t<4; t++) {
                int a = x + dx[t] * p;
                int b = y + dy[t] * p;
                if(0<=a && a<n && 0<=b && b<n) {
                    if(visitOrCancel == 1) board[a][b]++;
                    else board[a][b]--;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        dfs(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}