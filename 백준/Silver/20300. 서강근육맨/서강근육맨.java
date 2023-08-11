import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(int n, long[] strengths) {
        long answer = 0L;
        Arrays.sort(strengths); //강도를 오름차순으로 정렬. 파라미터로 int[] 타입 배열 뿐만 아니라 long[] 타입 배열도 받을 수 있다.
        if(n % 2 == 0) {
            for(int i=0; i<n/2; i++) {
                //아래 로직이 최적화된 로직임이 수학적으로 증명되어 있음. 가장 작은 값과 가장 큰 값을 기준으로 안 쪽으로 좁혀 나가면 된다.
                if(answer < strengths[i] + strengths[n-1-i]) answer = strengths[i] + strengths[n-1-i];
            }
        } else {
            answer = strengths[n-1]; //가장 큰 값을 우선 배정
            for(int i=0; i<(n-1)/2; i++) {
                if(answer < strengths[i] + strengths[n-2-i]) answer = strengths[i] + strengths[n-2-i];
            }
        }
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); //헬스 운동 기구 갯수
        long[] strengths = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            strengths[i] = Long.parseLong(st.nextToken());
        }
        bw.write(main.solution(n,strengths));
        bw.flush();
        bw.close();
    }
}