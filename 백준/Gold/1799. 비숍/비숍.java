import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    static int n;
    static int[][] board;
    static ArrayList<Coordinate>[] arr;
    static int[] diff;
    static int answer;

    public static void dfs(int level, int result, boolean flag) {
        if(level == 2*n-1) {
            answer = Math.max(answer,result);
            return;
        }
        if(result + ((2*n-2)-level+1) <= answer) return;
        if(flag){//level 번째에서 선택하는 경우 (설사 level번째의 list가 비어 있더라도, else에서 처리되므로 문제되지 않는다.)
            for(Coordinate cor : arr[level]) {
                if(diff[cor.i - cor.j + n-1] == 0) {
                    diff[cor.i-cor.j+n-1]++; //추후에 i-j+n-1 인 점은 선택되지 못한다.
                    result++;
                    dfs(level+1, result, true); //level+1 번째의 점을 선택하겠다
                    dfs(level+1, result, false); // level+1 번째의 점을 선택하지 않겠다.
                    result--;
                    diff[cor.i-cor.j+n-1]--;
                }
            }
        } else { //level 번째에서 선택하지 않는 경우(바로 level+1번째로 이동)
            dfs(level+1, result, true);
            dfs(level+1, result, false);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        arr = new ArrayList[2*n-1]; //index가 0~(2n-2)까지이며, 각 index는 x좌표와 y좌표의 합을 의미. 해당 원소는 그러한 점을 모아둔 ArrayList
        diff = new int[2*n-1]; //각 index의 원소는, i-j+(n-1)가 index인 좌표의 갯수
        for(int i=0; i<2*n-1; i++) arr[i] = new ArrayList<Coordinate>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) {
                    arr[i+j].add(new Coordinate(i,j));
                }
            }
        }

        dfs(0,0,true);
        dfs(0,0,false);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }


    private static class Coordinate {
        int i;
        int j;

        Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}