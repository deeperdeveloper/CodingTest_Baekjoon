import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[] seqs;
    static int[] results; // results[k]는 seqs[k]를 마지막 원소로 반드시 포함하는 seqs의 (연속)부분 수열의 가능한 모든 원소 합 중, 가장 큰 값
    static int answer;
    
    public static void dp() {
        if(n==1) return;
        int k=2;
        while(k<=n) {
            boolean flag = true; //아래의 for문을 한번도 적용을 받지 못했다면, 이것은 seqs[k]를 마지막 원소로 갖는 모든 부분 수열에서 seqs[k]가 가장 작은 값임을 의미
            for(int i=1; i<=k-1; i++) {
                if(seqs[k] > seqs[i]) {
                    flag = false;
                    results[k] = Math.max(results[k], results[i] + seqs[k]);
                }
            }
            if(flag) results[k] = seqs[k];
            answer = Math.max(answer, results[k]);
            k++;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        seqs = new int[n+1];
        results = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            seqs[i] = Integer.parseInt(st.nextToken());
        }
        
        setInitValues();
        dp();
        
        System.out.print(answer);
    }
    
    private static void setInitValues() {
        results[1] = seqs[1];
        answer = results[1];
    }
}