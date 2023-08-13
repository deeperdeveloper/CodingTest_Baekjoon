import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(String inputStr) {
        int answer = 1;
        StringTokenizer st = new StringTokenizer(inputStr);
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        //a에서 b로 연산이 된다면, b에서 a로 역연산이 가능함을 이용하자.
        while(b>a) {
            if(b%2 == 0) b=b/2;
            else {
                if(b%10 != 1) return "-1"; //b가 홀수인데, 일의 자리수가 1이 아니면, 역연산할 방법이 없다.
                else b=(b-1)/10;
            }
            answer++;
        }
        if(a!=b) return "-1"; //b에서 역연산 했는데 a가 나오지 않는다면, a에서 연산해서 b를 만들수 없다는 의미
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputStr = br.readLine();
        bw.write(main.solution(inputStr));
        bw.flush();
        bw.close();
    }
}