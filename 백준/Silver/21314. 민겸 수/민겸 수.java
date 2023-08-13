import java.util.*;
import java.io.*;

public class Main {
    
    public String maximizing(String inputStr) {
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(inputStr, "MK", true); //각각의 token은 M 또는 K이다.
        int cnt = 0; //연속된 M의 갯수
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            if(token.equals("M")) cnt++;
            else {
                answer.append("5");
                for(int i=0; i<cnt; i++) answer.append("0"); //MMK이면, 500으로 치환된다.
                cnt = 0;
            }
        }
        if(cnt > 0) {
            for(int i=0; i<cnt; i++) answer.append("1");
        }
        return answer.toString();
    }
    
    public String minimizing(String inputStr) {
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(inputStr, "MK", true); //각각의 token은 M 또는 K이다.
        boolean flag = false; //바로 직전의 문자가 M인 경우 true.
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            if(token.equals("K")) {
                answer.append("5");
                flag = false; //만약 연속된 M이 재등장한다면, 해당 연속된 M의 맨 첫번째에는 1을 부여하기 위함.
            }
            else {
                //M이 맨 처음에 있거나, M 바로 직전에 K인 경우
                if(!flag) {
                    answer.append("1");
                    flag = true;
                } else {
                    answer.append("0");
                }
            }
        }
        return answer.toString();
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputStr = br.readLine();
        bw.write(main.maximizing(inputStr));
        bw.write("\n");
        bw.write(main.minimizing(inputStr));
        bw.flush();
        bw.close();
    }
}