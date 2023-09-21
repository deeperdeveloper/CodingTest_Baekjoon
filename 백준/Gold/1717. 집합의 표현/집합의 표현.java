import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n,m;
    static int[] answers;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answers = new int[n+1];
        fillAnswers();
        
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(operation == 0) union(a,b);
            else {
                String result = check(a,b);
                bw.write(result);
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void union(int a, int b) {
        int fa = find(a); //뿌리를 찾기
        int fb = find(b); //뿌리를 찾기
        if(fa != fb) answers[fa] = fb;
    }
    
    private static int find(int v) {
        if(answers[v] == v) return v;
        return answers[v] = find(answers[v]); //각 집합을 구성하는 tree의 depth를 1로 제한시킨다.
    }
    
    private static String check(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa == fb) return "YES";
        return "NO";
    }
    
    private static void fillAnswers() {
        for(int i=0; i<=n; i++) {
            answers[i] = i;
        }
    }
}