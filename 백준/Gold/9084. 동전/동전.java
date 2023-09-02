import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int t; //테스트케이스 갯수
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        answer = new int[t+1];
        StringTokenizer st;

        for(int i=1; i<=t; i++) {
            int n = Integer.parseInt(br.readLine()); //코인 갯수
            int[] coins = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                coins[j] = Integer.parseInt(st.nextToken()); //코인 종류
            }

            int m = Integer.parseInt(br.readLine()); //목표 금액
            int[][] board = new int[n][m+1];

            dp(n,m,i,coins,board);
        }

        // 답 구하는 로직 추가하기
        for(int k=1; k<=t; k++) {
            System.out.println(answer[k]);
        }
    }

    public static void dp(int n, int m, int level, int[] coins, int[][] board) {
        setInitValues(m,coins,board);
        if(n==1) {
            answer[level] = board[0][m];
            return;
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<=m; j++) {
                if(j<coins[i]) board[i][j] = board[i-1][j]; //coins[i]가 적용되지 않은 경우랑 완전히 일치
                else board[i][j] = board[i-1][j] + board[i][j-coins[i]];
            }
        }
        answer[level] = board[n-1][m];
    }

    private static void setInitValues(int m, int[] coins, int[][] board) {
        for(int j=0; j<=m; j++) {
            if(j%coins[0] == 0) board[0][j] = 1;
        }
    }
}