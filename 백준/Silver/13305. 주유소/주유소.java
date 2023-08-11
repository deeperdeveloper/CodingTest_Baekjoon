import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(int n, int[] dis, int[] prices) {
        long answer = 0L;
        long cntMinPrice = prices[0];
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
