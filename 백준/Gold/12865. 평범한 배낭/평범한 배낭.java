import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n,k; //물품 수, 버틸 수 있는 무게
    static int[][] board; //board[i][j] => 0~i번 물품으로 j의 무게를 만들 때 가능한 가치의 최댓값
    static int answer; //최대가치
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][k+1];
        
        st = new StringTokenizer(br.readLine());
        int firstWeight = Integer.parseInt(st.nextToken());
        int firstValue = Integer.parseInt(st.nextToken());
        setInitValues(firstWeight, firstValue);
        
        for(int i=1; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            dp(i,weight,value);
        }
        
        System.out.print(answer);
    }
    
    private static void dp(int i, int weight, int value) {
        for(int j=1; j<=k; j++) {
            if(j<weight) {
                board[i][j] = board[i-1][j]; //i번 물품을 투입 못 하므로, 이전의 최적화 상황과 동일
                continue;
            }
            else if(j==weight) board[i][j] = Math.max(board[i-1][j], value); //i번 물품 투입 이전과, i번 물품만 투입했을 때 최적값을 비교
            else {
                if(board[i-1][j-weight] != 0) {
                    board[i][j] = Math.max(board[i-1][j-weight] + value, board[i-1][j]); //i번 물품은 1개 뿐이므로, i번 물품 투입 이전 상황을 명확히 하기 위해서는 i-1번째 row의 데이터를 활용해야 함이 맞다.
                } else {
                    board[i][j] = board[i-1][j]; //역시 i번 물품을 투입해서 j를 만들 수 없으므로, 이전의 최적화 상황과 동일
                }
            }
            answer = Math.max(answer, board[i][j]);
        }
    }
    
    private static void setInitValues(int firstWeight, int firstValue) {
        if(firstWeight <= k) {
            board[0][firstWeight] = firstValue;
            answer = board[0][firstWeight];
        }
    }
}