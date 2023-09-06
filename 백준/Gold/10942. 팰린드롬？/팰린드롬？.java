import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int n,m; //수열 갯수 n, 질문 갯수 m
    static int[] seqs; //총 크기는 n+1 이며, idx를 1부터 n까지 사용
    static int[][] board; //board[i][j] => idx i가 시작 idx (s)이며, s+e가 j인 문자열이 펠린드롬인지 판별 (0은 미정, 1은 yes, 2는 no)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        seqs = new int[n+1];
        board = new int[n+1][2*n+1]; //열 idx는 1~n, 행 idx는 2~2n까지 사용
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            seqs[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dp(s,e);
            if(board[s][s+e] == 1) bw.write("1"); //펠린드롬이므로 1 출력
            if(board[s][s+e] == 2) bw.write("0"); // 펠린드롬이 아니므로 0 출력
            if(i!=m) bw.write("\n"); //맨 마지막 질문의 경우를 제외하고는, 각 질문의 결과물을 출력하고 나면 커서를 한 줄 아래로, 맨 앞으로 옮긴다.
        }
        bw.flush();
        bw.close();
    }

    //board[][beginIdx+endIdx]의 값을 설정
    private static void dp(int beginIdx, int endIdx) {
        if(board[beginIdx][beginIdx+endIdx] > 0) return;
        int sum = beginIdx + endIdx;
        for(int i=sum/2; i>=1; i--) {
            if(sum-i > n) return;
            if(seqs[i] == seqs[sum-i]) board[i][sum] = 1; //적어도 시작 idx가 i이고 끝이 sum-i 인 문자열은 펠린드롬임
            else {
                for(int k=i; k>=1; k--) {
                    board[k][sum] = 2; //시작 idx가 i이고 끝이 sum-i 인 문자가 일치하지 않으면, 이 두 문자를 비교대상으로 삼는 문자열은 모두 펠린드롬이 아님은 명확하다.
                }
                return;
            }
        }
    }
}