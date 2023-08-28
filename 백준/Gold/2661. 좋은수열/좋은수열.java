import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


//substring을 쓰자. 배열로 해결하려니 너무 힘들다.
public class Main {

    static int n;
    static int[] answer;
    static boolean isComplete;

    public static void backTracking(int level) {
        if(level == n) {
            isComplete = true;
            return;
        }
        for(int i=1; i<=3; i++) {
            //i를 추가했을 때에도, 좋은 수열을 만족하면 된다.
            if(checkIfGood(level,i)) {
                answer[level] = i;
                backTracking(level+1);
                if(isComplete) return; //answer배열을 형성했다면, 이 배열을 통해서 바로 정답을 추출할 수 있으므로 dfs 중단.
            }
        }
    }

    private static boolean checkIfGood(int level, int i) {
        if(level == 0) return true;
        answer[level] = i;
        for(int j=1; j<=(level+1)/2; j++) {
            int cnt = 0;
            for(int k=0; k<=j-1; k++) {
                //길이가 j인 인접한 부분수열이 완전히 같은 경우가 존재한다면, false 반환
                if(answer[level-k] == answer[level-j-k]) {
                    cnt++;
                }
            }
            if(cnt == j) {
                answer[level] = 0;
                return false;
            }
        }
        answer[level] = 0;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        answer = new int[n];

        backTracking(0);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(answer[i]);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}