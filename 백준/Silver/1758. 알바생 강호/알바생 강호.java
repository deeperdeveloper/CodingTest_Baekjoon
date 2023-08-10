import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(int n, int[] tips) {
        Arrays.sort(tips); //팁 기준 오름차순 정렬. 
        long answer = 0L; //answer는 100억까지 가능하기 때문에, long 형으로 정의
        for(int i=0; i<n; i++) {
            int money = tips[i] - ((n-i)-1); //팁이 작을수록, 대기줄의 뒤쪽에 위치하게끔 하자.
            if(money > 0) answer += money; //팁이 작은 사람은 가능한 큰 등수를 부여하여, answer가 최대한 크게끔 하는 것이 핵심.
        }
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] tips = new int[n];
        for(int i=0; i<n; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
        bw.write(main.solution(n,tips));
        bw.flush();
        bw.close();
    }
}

/**
* 연산자 규칙
*     long + int -> long + long 형으로 변환된다.
*     출처 : https://kookyungmin.github.io/language/2018/05/14/java_03/
*/