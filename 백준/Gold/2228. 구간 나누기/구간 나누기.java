import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int n,m; //n개의 수,  m개의 구간의 갯수
    static int[] arr;
    static int[] sums; //sums[i] => arr[1] + arr[2] + ..... + arr[i];
    static int[][] board; //(j>=1) board[i][j] => 1~i에 해당하는 index의 수열 원소 중, 가능한 j개 구간의 합의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        sums = new int[n+1];
        board = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sums[i] = sums[i-1] + arr[i];
        }

        setAllInitElement();
        setFirstRow();
        setFirstColumn();

        for(int i=2; i<=n; i++) {
            dp(i);
        }

        System.out.print(board[n][m]);
    }

    //board[rowLevel][j] 구하는 과정
    private static void dp(int rowLevel) {
        for(int j=2; j<=m; j++) {
            if((rowLevel+1)/2 < j) break; //애초에 만들 수 없으므로 break;
            board[rowLevel][j] = board[rowLevel-1][j]; //rowLevel 번째 수를 넣지 않았음을 가정
            for(int k=rowLevel; k>=1; k--) {
                if(board[k-2][j-1] == Integer.MIN_VALUE) break; //1~(k-2)번째 까지의 수로 j-2개의 구간을 만들 수 없는 순간이 도래하면, 더 이상 따지지 않아도 된다.
                board[rowLevel][j] = Math.max(board[rowLevel][j], board[k-2][j-1] + sums[rowLevel] - sums[k-1]);
            }
        }
    }

    //i=1인 경우 setting
    private static void setFirstRow() {
        board[1][1] = arr[1];
    }

    private static void setFirstColumn() {
        for(int i=2; i<=n; i++) {
            board[i][1] = board[i-1][1]; //i번째를 안 넣은 경우를 가정
            for(int k=i; k>=1; k--) {
                board[i][1] = Math.max(board[i][1], sums[i]-sums[k-1]); //i번째를 넣은 경우와 비교하여 더 큰 경우를 할당.
            }
        }
    }

    private static void setAllInitElement() {
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=m; j++) {
                board[i][j] = Integer.MIN_VALUE;
            }
        }
    }
}