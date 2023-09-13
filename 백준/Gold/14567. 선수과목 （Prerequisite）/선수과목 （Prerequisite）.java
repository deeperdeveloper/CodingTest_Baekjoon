import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    static int n,m; //과목 수, 선수 조건의 수
    static ArrayList<Integer>[] arr; // arr[i] => 과목 i의 선수과목 목록
    static int[] degrees; // degrees[i] => 과목 i의 선수과목 수 중, 아직 이수하지 않은 과목의 수
    static int[] answers; // answers[i] => i를 듣는데 필요한 최소

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        fillArr(); //arr의 모든 index에 arrayList를 만듦
        degrees = new int[n+1];
        answers = new int[n+1];

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //y의 선수과목
            int y = Integer.parseInt(st.nextToken());
            arr[y].add(x);
            degrees[y]++;
        }

        //answer[i]를 결정
        for(int i=1; i<=n; i++) {
            bw.write(String.valueOf(solution(i)));
            if(i!=n) bw.write(" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(int index) {
        if(arr[index].size() == 0) {
            return answers[index] = 1;
        }
        if(answers[index] > 0) return answers[index];
        for(int prv : arr[index]) {
            answers[index] = Math.max(solution(prv)+1, answers[index]);
        }

        return answers[index];
    }

    private static void fillArr() {
        for(int i=1; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
    }
}