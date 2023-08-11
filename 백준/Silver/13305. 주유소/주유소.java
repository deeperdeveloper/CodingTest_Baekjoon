import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(int n, int[] dis, int[] prices) {
        long answer = 0L; //answer에 저장되는 정수값은 4byte를 초과하는 저장공간을 차지할 수 있으므로 long 형으로 선언
        long cntMinPrice = prices[0]; //prices[0] 값 자체는 int형 타입 변수에 저장이 되지만, dis[i]와 곱한 결과물은 long 형 변수에 저장하므로 자동 타입 변환을 유도하기 위해서 prices[0] 값을 long 형 타입에 저장함.
        for(int i=0; i<dis.length; i++) {
            if(cntMinPrice > prices[i]) {
                cntMinPrice = prices[i];
            }
            answer += cntMinPrice * dis[i];
        }
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); //도시 갯수
        int[] dis = new int[n-1];
        int[] prices = new int[n];
        StringTokenizer dis_string = new StringTokenizer(br.readLine());
        StringTokenizer prices_string = new StringTokenizer(br.readLine());
        
        main.setDistances(n,dis,dis_string); //입력받은 거리값 세팅
        main.setPrices(n,prices,prices_string); //입력받은 기름 가격 세팅
        
        bw.write(main.solution(n, dis, prices));
        bw.flush();
        bw.close();
    }
    
    private void setDistances(int n, int[] dis, StringTokenizer dis_string) {
        for(int i=0; i<n-1; i++) {
            dis[i] = Integer.parseInt(dis_string.nextToken());
        }
    }
    
    private void setPrices(int n, int[] prices, StringTokenizer prices_string) {
        for(int i=0; i<n; i++) {
            prices[i] = Integer.parseInt(prices_string.nextToken());
        }
    }
}
