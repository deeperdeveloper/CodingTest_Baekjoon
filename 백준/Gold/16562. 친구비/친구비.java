import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    
    static int n,m,k; //학생 수 n, 친구 관계 수 m, 가지고 있는 돈 k
    static int[] fees; //fees[i] => i번 학생과 친구 맺는데 드는 돈 (index => 1~n)
    static int[] relations; //relations[i] => i번 학생과 친구인 학생 중, 가장 fee가 적은 학생
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        relations = new int[n+1];
        fillRelations();
        fees = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            fees[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v,w);
        }
        
        int finalFee = calculateMinimumFee();
        if(finalFee <= k) System.out.print(finalFee);
        else System.out.print("Oh no");
    }
    
    private static int calculateMinimumFee() {
        HashMap<Integer, Integer> map = new HashMap<>(); //각 그룹의 부모 노드 번호에 대해, 이미 사용했다면 키로 저장.
        int sum = 0;
        for(int i=1; i<=n; i++) {
            int fa = find(relations[i]);
            if(!map.containsKey(fa)) {
                map.put(fa,1);
                sum += fees[fa];
            }
        }
        return sum;
    }
    
    private static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        //relations[k]를 설정 시, 교류비가 가장 적은 학생을 가리키도록 한다.
        if(fees[fb] >= fees[fa]) relations[fb] = fa;
        else relations[fa] = fb; 
    }
    
    private static int find(int x) {
        if(x == relations[x]) return x;
        return relations[x] = find(relations[x]);
    }
    
    private static void fillRelations() {
        for(int i=1; i<=n; i++) {
            relations[i] = i;
        }
    }
}
