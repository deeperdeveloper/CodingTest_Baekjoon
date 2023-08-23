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
    
    public void dfs(int level, int[] subSeqs) {
        for(int i=0; i<n; i++) {
            if(level == m+1) {
                for(int j=1; j<=m; j++) {
                    sb.append(subSeqs[j]);
                    sb.append(" ");
                }
                sb.append("\n");
                return;
            }
            subSeqs[level] = seqs[i];
            dfs(level+1, subSeqs);
        }
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
        subSeqs[0] = -1;
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            seqs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(seqs); //Arrays의 원소를 오름차순 정렬
        
        main.dfs(1,subSeqs);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}