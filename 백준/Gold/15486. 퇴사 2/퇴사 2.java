import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[] answer; //answer[k]의 값은 오른쪽과 같다 => n,n-1,....,k일에 시작되는 상담을 종합했을 때, 최대 상담비 가격
    static Counseling[] schedules;
    
    public static void dp() {
        if(n==1) return;
        int k=n;
        while(k>=1) {
            if(schedules[k].time + k > n+1) { //해당 상담은 일정을 벗어나므로, 어떠한 상담 조합에도 해당 상담을 넣을 수 없다.
                answer[k] = answer[k+1]; //해당 상담(k일에 시작되는 상담)이 있는 경우와 없는 경우가 동일하다. 
                k--;
                continue;
            }
            answer[k] = Math.max(answer[k+1], (answer[k+schedules[k].time] + schedules[k].price)); //k일째 상담이 있는 경우의 최댓값과, 없는 경우의 최댓값 중 최댓값을 택한다.
            k--;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = new int[n+2];
        schedules = new Counseling[n+2];
        
        StringTokenizer st;
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            schedules[i] = new Counseling(time, price);
        }
        
        setInitValues();
        dp();
        
        System.out.print(answer[1]);
    }
    
    private static void setInitValues() {
        // 맨 마지막 index인 n에 대해서 조사
        if(schedules[n].time + n == n+1) {
            answer[n] = schedules[n].price;
        }
    }
    
    private static class Counseling {
        int time;
        int price;
        
        Counseling(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }
}