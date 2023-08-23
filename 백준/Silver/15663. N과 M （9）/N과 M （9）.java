import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    
    static int n,m;
    static int[] seqs;
    static StringBuilder sb;
    
    public void dfs(int level, int[] subSeqs, boolean[] flags) {
        for(int i=0; i<n; i++) {
            if(level == m+1) {
                for(int j=1; j<=m; j++) {
                    sb.append(subSeqs[j]);
                    sb.append(" ");
                }
                sb.append("\n");
                return;
            }
            if(!flags[i] && subSeqs[level] != seqs[i]) {
                subSeqs[level] = seqs[i];
                flags[i] = true;
                dfs(level+1, subSeqs, flags);
                flags[i] = false;
            }
        }
        subSeqs[level] = 0; //이 과정이 있어야 subSeqs = [7,9,9] -> subSeqs = [9,9,7] 의 반례가 막아진다. (즉, index = level-1 에 해당하는 원소가 바뀌면, index = level 에 해당하는 원소부터는 아무 원소나 배치가 가능하게끔 하는 것이다.)
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        seqs = new int[n];
        sb = new StringBuilder();
        int[] subSeqs = new int[m+1];
        boolean[] flags = new boolean[n]; //수열에 숫자가 등장했는지 판별해주는 도구
        subSeqs[0] = -1;
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            seqs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(seqs); //Arrays의 원소를 오름차순 정렬
        
        main.dfs(1,subSeqs,flags);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}