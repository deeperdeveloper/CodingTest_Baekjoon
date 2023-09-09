import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Matrix[] matrixes;
    static int[][] board; //board[i][j] => i번째 matrix ~ j번째 matrix 대상으로 할 때, 최소 연산값.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        matrixes = new Matrix[n+1];
        board = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int rowCnt = Integer.parseInt(st.nextToken());
            int columnCnt = Integer.parseInt(st.nextToken());
            matrixes[i] = new Matrix(rowCnt,columnCnt);
        }

        for(int j=2; j<=n; j++) {
            dp(1,j);
        }

        System.out.print(board[1][n]);
    }

    //board[x][y] 결정
    private static int dp(int x, int y) {
        if(board[x][y] > 0) return board[x][y];
        if(x==y) return 0;

        board[x][y] = dp(x,y-1) + dp(y,y) + calculate(x,y-1,y); //board[x][y] 초깃값 설정
        for(int k=y-2; k>=x; k--) {
            int result = dp(x,k) + dp(k+1,y) + calculate(x,k,y);
            board[x][y] = Math.min(result, board[x][y]);
        }
        return board[x][y];
    }

    private static int calculate(int begin, int middle, int last) {
        return matrixes[begin].rowCnt * matrixes[middle].columnCnt * matrixes[last].columnCnt;
    }

    private static class Matrix {
        int rowCnt;
        int columnCnt;

        Matrix(int rowCnt, int columnCnt) {
            this.rowCnt = rowCnt;
            this.columnCnt = columnCnt;
        }
    }
}