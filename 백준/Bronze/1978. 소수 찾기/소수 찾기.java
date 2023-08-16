import java.util.*;
import java.io.*;

public class Main{
    
    public String solution(int n, String nums) {
        int answer = 0;
        StringTokenizer st = new StringTokenizer(nums);
        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if(num == 1) continue;
            if(num == 2 || num == 3) {
                answer++;
                continue;
            }
            int k=1;
            //루트n보다 크거나 같은 정수 중, 가장 작은 정수를 k라고 명명.
            while(k*k < num) {
                k++;
            }
            //아래에서, 2~(k) 까지의 숫자가 num을 나누지 못하면, num은 소수라고 말할 수 있다.
            boolean flag = true; //num이 소수이면 true
            for(int i=2; i<=k; i++) {
                if(num % i == 0) {
                    flag = false; //num이 합성수
                    break;
                }
            }
            if(flag) answer++;
        }
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String nums = br.readLine();
        bw.write(main.solution(n, nums));
        bw.flush();
        bw.close();
    }
}