//1. 테스트 케이스 갯수 입력받기
//2. 각 케이스에 대한 board 입력받기
//    2-1. 각 케이스에 대해서 backTracking으로 능력치 최댓값 구하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main {

    static int c;
    static int[] answer;

    public static void solution(int a, int level, int result, int[][] board, boolean[] flags) {
        if(level == 11) {
            answer[a] = Math.max(answer[a], result);
            return;
        }
        for(int i=0; i<11; i++) {
            if(flags[i] == false && board[level][i] != 0) {
                flags[i] = true;
                solution(a, level+1, result+board[level][i] ,board, flags);
                flags[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        c = Integer.parseInt(br.readLine());
        answer = new int[c];
        for(int a=0; a<c; a++) {
            int[][] board = new int[11][11];
            boolean[] flags = new boolean[11];
            for(int i=0; i<11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solution(a,0,0,board,flags);
        }
        //answer의 배열을 모두 출력
        for(int i=0; i<c; i++) {
            bw.write(String.valueOf(answer[i]));
            if(i != c-1) {
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
}