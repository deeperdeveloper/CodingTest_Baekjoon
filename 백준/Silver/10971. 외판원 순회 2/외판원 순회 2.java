import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[][] distances;
    static boolean[] isVisited;
    static int answer = Integer.MAX_VALUE;
    static int startCity;
    static int subSum;
    
    //p도시를 방문한 상태
    public static void dfs(int level, int p){
        if(level == n){
            answer = Math.min(answer, subSum);
            return;
        }

        isVisited[p] = true; //p 도시를 방문했을 때, 방문 도장을 찍는다.
        for(int i=0; i<n; i++) {
            if(level == n-1) {
                if(distances[p][startCity] == 0) break;
                subSum += distances[p][startCity];
                dfs(level+1, startCity);
                subSum -= distances[p][startCity];
                break;
            }
            if(!isVisited[i] && distances[p][i] > 0) {
                subSum += distances[p][i]; // 다음번에 갈 도시에 대해 미리 거리를 더한다. (p -> i) 거리를 더함.
                dfs(level+1, i);
                subSum -= distances[p][i]; // i-> p로 다시 back할 때, 위에서 더한 거리를 다시 차감하여, p도시를 방문했을 때로 원상복귀
                isVisited[i] = false; //i -> p로 back할 때,i를 방문했다는 표시를 제거
            }
        }
        isVisited[p] = false; //p도시에서 나올 때, 방문 도장을 다시 제거한다.
      
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        distances = new int[n][n];
        isVisited = new boolean[n]; //방문 여부
        StringTokenizer st;
        
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                distances[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int p=0; p<n; p++) {
            isVisited[p] = true;
            startCity = p;
            dfs(0,p);    
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}