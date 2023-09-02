import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n,k;
    static long[][] board; //board[i][j]는, 0~i번째 코인을 활용하여 j원을 만드는 총 경우의 수
    
    public static void dp(int coin, int i) {
        for(int j=1; j<=k; j++) {
            if(j<coin) {
                board[i][j] = board[i-1][j]; //coin을 투입할 수 없으므로, coin을 투입하기 전의 상황과 같다.
            } else if(j==coin) {
                board[i][j] = board[i-1][j] + 1; //coin이 j원과 같아지는 경우에는, coin을 반드시 투입한다면 1가지 뿐이다.
            } else {
                board[i][j] = board[i-1][j] + board[i][j-coin];
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        board = new long[n][k+1];
        int firstCoin = Integer.parseInt(br.readLine());
        setInitValues(firstCoin, 0);
        for(int i=1; i<n; i++) {
            int coin = Integer.parseInt(br.readLine());
            dp(coin, i);
        }
        
        System.out.print(board[n-1][k]);
    }
    
    private static void setInitValues(int firstCoin, int row) {
        for(int j=1; j<=k; j++) {
            if(j%firstCoin == 0) board[row][j] = 1;
        }
    }
}