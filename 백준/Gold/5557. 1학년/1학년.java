import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[] seqs;
    static long[][] board;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        seqs = new int[n+1]; //첫번째 index부터 입력받을 것이다.
        board = new long[n+1][21]; //board[i][j] => 앞에서 i개의 숫자를 연산한 결과물이 j인 모든 연산의 경우의 수를 나타냄.
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=1; i<=n; i++) {
            seqs[i] = Integer.parseInt(st.nextToken()); //seqs[n]은 만들어진 등식의 결과물을 나타냄.
        }
        
        //첫번째 row 설정 및 n-1번째 row까지 설정 완료
        setInitRow();
        dp();
        
        System.out.print(board[n-1][seqs[n]]);
    }
    
    private static void dp() {
        int k=1;
        while(k<=n-2) { //k+1번째 row를 결정지음.
            for(int j=0; j<=20; j++) {
                if(board[k][j] > 0L && j-seqs[k+1] >= 0)  {
                    board[k+1][j-seqs[k+1]] += board[k][j];
                }
                if(board[k][j] > 0L && j+seqs[k+1] <= 20) {
                    board[k+1][j+seqs[k+1]] += board[k][j];
                }
            }
            k++;
        }
    }
    
    private static void setInitRow() {
        //0번째 row는 그대로 두고, 1번째 row를 설정
        board[1][seqs[1]] = 1;
    }
}