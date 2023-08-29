import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static int n;
    static int[][] accumulScore; //이 배열 내부의 원소 2개짜리 배열의 의미는 오른쪽과 같다. => 0번째 index에 해당하는 원소는 연속 계단 2개를 밟은 상태에서의 최댓값 / 1번째 index는 연속 계단 1개(해당 계단)만 밟은 상태에서의 최댓값
    static int[] stairs;

    public static void solution() {
        if(n<=2) return;
        int k=3;
        while(k<=n) {
            accumulScore[k][0] = accumulScore[k-1][1] + stairs[k];
            accumulScore[k][1] = Math.max(accumulScore[k-2][0], accumulScore[k-2][1]) + stairs[k];
            k++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        stairs = new int[300+1];
        accumulScore = new int[300+1][2];
        for(int i=1; i<=n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        setInitValues();
        solution();

        bw.write(String.valueOf(Math.max(accumulScore[n][0], accumulScore[n][1])));
        bw.flush();
        bw.close();
    }

    private static void setInitValues() {
        accumulScore[1][0] = stairs[1];
        accumulScore[1][1] = stairs[1];
        //0->1->2 또는 0->2가 가능
        //2개 연속 계단을 밟는 경우는, 해당 경우를 맨 첫번째 요소에 저장
        //1개 계단만 연속으로(=현재 계단만 밟고 이전 계단은 안 밟은 경우) 밟는 경우, 해당 경우를 2번째 요소에 저장
        accumulScore[2][0] = stairs[1] + stairs[2]; //cnt = 2;
        accumulScore[2][1] = stairs[2]; //cnt=1;
    }
}