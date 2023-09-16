import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n,m; //도시의 수 n, 여행 계획에 속한 도시의 수
    static int[] parents; //parents[i] => i보다 작은 도시 중, i와 연결되어 있는 또 다른 도시
    static int[] tripOrders; //m개의 여행도시
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        parents = new int[n+1];
        fillAnswers();
        tripOrders = new int[m+1];
        
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                int relation = Integer.parseInt(st.nextToken());
                if(relation == 1 && i>=j) union(i,j);
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=m; i++) {
            tripOrders[i] = Integer.parseInt(st.nextToken());
        }
        
        String result = checkIfPossible();
        System.out.print(result);
    }
    
    private static String checkIfPossible() {
        for(int k=1; k<m; k++) {
            int fa = find(tripOrders[k]);
            int fb = find(tripOrders[k+1]);
            if(fa!=fb) return "NO";
        }
        return "YES";
    }
    
    private static void union(int i, int j) {
        int fa = find(i);
        int fb = find(j);
        if(fa!=fb) parents[fa] = fb;
    }
    
    private static int find(int x) {
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
    
    private static void fillAnswers() {
        for(int i=1; i<=n; i++) {
            parents[i] = i;
        }
    }
}