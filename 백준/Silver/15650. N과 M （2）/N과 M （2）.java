import java.io.*;
import java.util.*;

public class Main {
    
    static int n,m;
    static int[] seqs;
    static StringBuilder answer;
    
    public void dfs(int n, int m, int level, int[] seqs) {
        for(int i=1; i<=n; i++) {
            if(level == m+1) {
                for(int j=1; j<=m; j++) {
                    answer.append(seqs[j]);
                    answer.append(" ");
                }
                answer.append("\n");
                return;
            } else {
                if(seqs[level-1] < i){
                    seqs[level] = i;
                    dfs(n,m,level+1,seqs);
                }
            }
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        seqs = new int[m+1];
        answer = new StringBuilder();
        
        main.dfs(n,m,1,seqs);
        
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}