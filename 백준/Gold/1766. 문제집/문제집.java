import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {
    
    static int n,m; //문제 갯수 n, 문제 선후관계 정보 갯수 m
    static int[] prvDegrees; // degrees[i] => i번 문제에 선행해서 풀어야할 문제의 갯수
    static ArrayList<Integer>[] nextProblemList; //nextProblemList[i] => i번 문제 뒤에 풀어야 할 문제 목록
    static int[] answers;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        prvDegrees = new int[n+1];
        nextProblemList = new ArrayList[n+1];
        fillArrays();
        answers = new int[n+1];
        
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int prv = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            prvDegrees[cur]++;
            nextProblemList[prv].add(cur);
        }
        
        solution();
        
        for(int i=1; i<=n; i++) {
            bw.write(String.valueOf(answers[i]));
            if(i!=n) bw.write(" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void solution() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //자연스럽게 조건 3번을 충족하게끔 한다.
        
        //맨 처음 풀 수 있는 문제의 목록을 넣음
        for(int i=1; i<=n; i++) {
            if(prvDegrees[i] == 0) pq.offer(i);
        }
        
        int index = 1;
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            for(int next : nextProblemList[cur]) {
                prvDegrees[next]--;
                if(prvDegrees[next] == 0) pq.offer(next);
            }
            answers[index++] = cur;
        }
    }
    
    private static void fillArrays() {
        for(int i=1; i<=n; i++) {
            nextProblemList[i] = new ArrayList<>();
        }
    }
}