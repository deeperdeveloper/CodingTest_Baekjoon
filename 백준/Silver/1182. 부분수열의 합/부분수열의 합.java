import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    
    static int n,s;
    static int[] seq;
    static int answer;
    static int subSum;
    
    public void dfs(int level, boolean flag) {
        if(level == n) {
            return;
        }
        if(flag) {
            subSum += seq[level];
            if(subSum == s) answer++;
        }
        dfs(level+1, flag);
        dfs(level+1, !flag);
        //parameter가 level인 dfs() 메서드 종료 직전, subSum을 dfs()메서드 실행 전으로 돌려둠
        if(flag) {
            subSum -= seq[level];
        }
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        seq = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) seq[i] = Integer.parseInt(st.nextToken());
       
        main.dfs(0,true); //seq[level]을 합에 포함
        main.dfs(0,false); //seq[level]을 합에 포함x
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}