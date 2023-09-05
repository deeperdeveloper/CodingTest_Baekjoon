import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    
    static int n;
    static int[] results; //results[i] => i번째 작업을 끝내는데 걸리는 시간 (index를 1부터 사용함) 
    static int finalAnswer; //results[i] 의 최댓값을 출력
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        results = new int[n+1];
        
        //1번째 작업을 입력받음
        st = new StringTokenizer(br.readLine());
        int firstTime = Integer.parseInt(st.nextToken());
        int firstPrvNeeded = Integer.parseInt(st.nextToken()); //필연적으로 0이다.
        setInitValue(firstTime, firstPrvNeeded);
        
        //i번째 작업을 입력받음 (i>=2)
        for(int i=2; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int prvNeededCnt = Integer.parseInt(st.nextToken());
            ArrayList<Integer> prvNeededList = new ArrayList<>();
            
            while(st.hasMoreTokens()) {
                int prvNeeded = Integer.parseInt(st.nextToken());
                prvNeededList.add(prvNeeded);
            }
            dp(i, time, prvNeededCnt, prvNeededList);
        }
        
        System.out.print(finalAnswer);
    }
    
    private static void dp(int idx, int time, int prvNeededCnt, ArrayList<Integer> prvNeededList) {
        if(prvNeededCnt == 0) results[idx] = time;
        else {
            int prvTime = 0;
            for(int prvNeededIdx : prvNeededList) {
                prvTime = Math.max(prvTime, results[prvNeededIdx]);
            }
            results[idx] = prvTime + time;
        }
        finalAnswer = Math.max(finalAnswer, results[idx]);
    }
    
    private static void setInitValue(int firstTime, int prvNeeded) {
        results[1] = firstTime;
        finalAnswer = results[1];
    }
}