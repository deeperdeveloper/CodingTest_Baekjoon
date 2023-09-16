import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int n; //지시 횟수 n
    static Robot[] parts; //index = 1 ~ 10^6 ()
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        parts = new Robot[1000000+1];
        fillParts();
        StringTokenizer st;
        
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();
            if(operation.equals("I")) {
                int prv = Integer.parseInt(st.nextToken());
                int aft = Integer.parseInt(st.nextToken());
                union(prv, aft);
            } else {
                int cur = Integer.parseInt(st.nextToken());
                int rootCur = find(cur);
                bw.write(String.valueOf(parts[rootCur].partCnt));
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa != fb) {
            parts[fa].partCnt += parts[fb].partCnt;
            parts[fb].robotNum = parts[fa].robotNum;
        }
    }
    
    private static int find(int x) {
        if(x == parts[x].robotNum) return x;
        return parts[x].robotNum = find(parts[x].robotNum);
    }
    
    private static void fillParts() {
        for(int i=1; i<=1000000; i++) {
            parts[i] = new Robot(i,1);
        }
    }
    
    private static class Robot {
        int robotNum; //제일 부모의 부품 번호
        int partCnt; //부모의 부품 번호를 제외할 떄는 값이 1 / 부모의 부품 번호인 경우, 해당 로봇의 부품 갯수
        
        Robot(int robotNum, int partCnt) {
            this.robotNum = robotNum;
            this.partCnt = partCnt;
        }
    }
}