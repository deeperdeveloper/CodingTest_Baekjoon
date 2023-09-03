import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//board 정의를 잘 해야 함. 맨 처음 풀이에서는, board[i][j] => 첫번째 문자열의 0~i번째 알파벳을 조합해서 j길이를 만들 수 있는 부분 수열 중, 두번째 문자열의 부분수열의 가장 마지막 index로 가능한 값 중 최솟값으로 정의함.

public class Main {

    static int[][] board; //board[i][j] => 첫번째 문자열의 0~i번째 알파벳으로 이루어진 부분수열 중, 두번째 문자열의 0~j번까지의 알파벳으로 이루어진 부분수열과 일치하는 경우 중, 그 길이의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int n = str.length();
        String anotherStr = br.readLine();
        int m = anotherStr.length();

        board = new int[n][m];
        setInitRow(str, anotherStr, m);
        setInitColumn(anotherStr, str, n);

        //m이나 n이 1이면 이미 board는 완성되어 있는 셈.
        if(m != 1) {
            for(int i=1; i<n; i++) { //첫번째 row부터 채워나가기
                dp(i,m,str,anotherStr);
            }
        }

        //답 구하는 로직 작성
        System.out.print(board[n-1][m-1]);
    }

    private static void dp(int row, int m, String str, String anotherStr) {
        for(int j=1; j<m; j++) {
            if(str.charAt(row) != anotherStr.charAt(j)) {
                board[row][j] = Math.max(board[row-1][j], board[row][j-1]);
            } else {
                board[row][j] = board[row-1][j-1] + 1;
            }
        }
    }

    private static void setInitRow(String str, String anotherStr, int m) {
        for(int j=0; j<m; j++) {
            if(str.charAt(0) == anotherStr.charAt(j)) {
                for(int k=j; k<m; k++) {
                    board[0][k] = 1;
                }
                break;
            }
        }
    }

    private static void setInitColumn(String anotherStr, String str, int n) {
        for(int i=0; i<n; i++) {
            if(anotherStr.charAt(0) == str.charAt(i)) {
                for(int k=i; k<n; k++) {
                    board[k][0] = 1;
                }
                break;
            }
        }
    }
}