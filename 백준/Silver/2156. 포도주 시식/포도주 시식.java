import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[] drinks;
    static int[][] accuMaxDrinks; //각 요소(내부 배열)의 i번째 요소는, "현재 위치"기준 i개만큼 연속할 때 누적 포도주양의 최댓값을 의미
    
    public static void dp() {
        if(n<=2) return;
        int k=3;
        while(k<=n) {
            accuMaxDrinks[k][0] = Math.max(Math.max(accuMaxDrinks[k-1][0], 
                                                    accuMaxDrinks[k-1][1]), accuMaxDrinks[k-1][2]); //k를 선택하지 않는 경우의 최댓값
            accuMaxDrinks[k][1] = accuMaxDrinks[k-1][0] + drinks[k]; //k를 선택하되, k-1번째는 선택하지 않는 경우의 최댓값
            accuMaxDrinks[k][2] = accuMaxDrinks[k-1][1] + drinks[k]; //k를 선택하고 k-1번째도 선택하는 경우의 최댓값
            k++;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        drinks = new int[n+2]; //index를 1부터 채울 예정
        accuMaxDrinks = new int[n+2][3];
        
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            drinks[i] = Integer.parseInt(st.nextToken());
        }
        
        setInitValues();
        dp();
        
        //답 구하는 로직
        int answer = 0;
        for(int j=0; j<3; j++) {
            answer = Math.max(answer, accuMaxDrinks[n][j]);
        }
        
        System.out.print(answer);
    }
    
    private static void setInitValues() {
        accuMaxDrinks[1][1] = drinks[1];
        accuMaxDrinks[2][0] = drinks[1];
        accuMaxDrinks[2][1] = drinks[2];
        accuMaxDrinks[2][2] = drinks[1] + drinks[2];
    }
}