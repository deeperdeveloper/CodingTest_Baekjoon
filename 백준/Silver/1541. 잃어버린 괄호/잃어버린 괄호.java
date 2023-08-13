import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(String inputStr) {
        int answer = 0;
        StringTokenizer st = new StringTokenizer(inputStr, "-", true); //+와 -를 기준으로 문자열을 token들로 나누고, +와 - 역시 token 화한다.
        boolean flag = true; // answer에 -가 적용되면, flag=false로 변환된다.
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            int cnt = 0;
            if(token.equals("-")){
                flag = false;
                continue;
            } else {
                try {
                    int n = Integer.parseInt(token); //token 단일 숫자이면 try 내부에서 처리 
                    if(flag) answer += n;
                    else answer -= n;
                } catch (NumberFormatException e) {
                    StringTokenizer subSt = new StringTokenizer(token, "+"); // +와 숫자로만 이루어진 token을, 숫자만 추출해낸다.
                    while(subSt.hasMoreTokens()) {
                        int a = Integer.parseInt(subSt.nextToken());
                        cnt += a; //+로 이루어진 숫자식을 연산함.
                    }
                    if(flag) answer += cnt;
                    else answer -= cnt;
                }
            }
        }
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