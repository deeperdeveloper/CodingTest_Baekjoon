import java.util.*;
import java.io.*;

class Main {
    
    public String solution(int n, int[] weights) {
        Arrays.sort(weights); //입력받은 배열을 무게의 오름차순으로 정렬
        int totalMaxWeight = Integer.MIN_VALUE;
        int cnt = 1;
        for(int i=n-1; i>=0; i--) { //가장 큰 중량을 견디는 로프부터 체크함.
            if(cnt * weights[i] > totalMaxWeight) {
                totalMaxWeight = cnt * weights[i]; //이 떄까지 체크한 cnt개의 로프 중, 가장 작은 중량을 견디는 로프가 총 중량을 견디는 기준점이 된다.
            }
            cnt++; //로프는 많을수록 많은 중량을 견디므로, 로프의 갯수는 가능한 많이 넣을 것이다.
        }
        return String.valueOf(totalMaxWeight); //BufferedWriter의 매개변수로 String 형을 넘길 것이다.
    }
    
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];
        for(int i=0; i<n; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }
        bw.write(main.solution(n,weights));
        bw.flush();
        bw.close();
    }
}