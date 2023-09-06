import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n,m; //저울에 올리는 무게 측정 도우미용 추의 갯수 n, 무게 측정 가능한지 확인 대상 추의 갯수 m
    static int[] targets; //무게 측정 가능한지 확인 대상의 추의 무게 저장
    static int[][] board; //board[i][j] => 0~i번째 추로 j무게를 만들 수 있다면, 해당 값은 1이다.
    static ArrayList<Integer> possibleWeights; //무게 측정 가능한 무게를 순차적으로 누적시킬 것임

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        possibleWeights = new ArrayList<>();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][500*n+1];
        st = new StringTokenizer(br.readLine());

        int firstHelper = Integer.parseInt(st.nextToken());
        setInitRow(firstHelper);
        setInitColumn();
        for(int i=1; i<n; i++) {
            int helper = Integer.parseInt(st.nextToken());
            dp(helper,i);
        }

        m = Integer.parseInt(br.readLine());
        targets = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
            String result = isPossibleToMeasure(targets[i]);
            bw.write(result);
            if(i!=m-1) bw.write(" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static String isPossibleToMeasure(int target) {
        if(target > 500 * n) return "N"; //ArrayIndexOutOfBounds 방지
        if(board[n-1][target] == 1) return "Y";
        return "N";
    }

    private static void dp(int helper, int row) {
        ArrayList<Integer> tmpArr = new ArrayList<>(); //이전과 다른 새로운 무게가 가능할 때, 여기에 추가시킬 것
        for(int w : possibleWeights) {
            //지금 이전에 무게 w를 측정 가능했음을 이번 row에 누적시키기.
            board[row][w] = 1;
            //이전에 w+helper의 무게를 측정 불가능했다면, 이번부터 측정 가능한 것이므로 tmpArr에 추가시키기
            if(board[row-1][w+helper] == 0) {
                board[row][w+helper] = 1;
                tmpArr.add(w+helper);
            }
            //이전에 w-helper의 무게를 측정 불가능했다면, 이번부터 측정 가능한 것이므로 tmpArr에 추가시키기
            if(board[row-1][Math.abs(w-helper)] == 0) { //w==helper가 되는 경우는, 없다. (setInitColumn() 에서 제외시킴)
                board[row][Math.abs(w-helper)] = 1;
                tmpArr.add(Math.abs(w-helper));
            }
        }
        //helper 단독으로도 측정할 수 있으므로 추가하기
        if(board[row][helper] == 0) {
            board[row][helper] = 1;
            tmpArr.add(helper);
        }

        //이번부터 측정 가능한 무게를, possibleWeights에 추가시키기
        for(int now : tmpArr) {
            possibleWeights.add(now);
        }
    }

    private static void setInitRow(int firstHelper) {
        board[0][firstHelper] = 1;
        possibleWeights.add(firstHelper);
    }

    private static void setInitColumn() {
        for(int i=0; i<n; i++) {
            board[i][0] = 1; //0의 무게는 항상 측정가능함을 명시하기
        }
    }
}