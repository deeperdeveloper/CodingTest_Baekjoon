import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); //건물 갯수
            int k = Integer.parseInt(st.nextToken()); //규칙 갯수

            Construction[] buildings = new Construction[n+1];
            for(int p=1; p<=n; p++) {
                buildings[p] = new Construction();
            }

            //선후 관계 고려하여, 각 개별 빌딩 짓는데 실질 소요시간 저장할 공간
            int[] answers = new int[n+1]; //1~n 번째 건물 각각 짓는데 총 필요한 시간(선 건설이 필요한 건물까지 고려)

            //각 개별 빌딩 짓는데 소요시간 입력받아 저장(선후 관계 고려 x)
            st = new StringTokenizer(br.readLine());
            for(int p=1; p<=n; p++) {
                buildings[p].constTime = Integer.parseInt(st.nextToken());
            }

            //빌딩 건설 선후 관계 설정
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine());
                int prv = Integer.parseInt(st.nextToken());
                int cur = Integer.parseInt(st.nextToken());

                buildings[cur].prvNeeded.add(prv);
            }

            int w = Integer.parseInt(br.readLine());
            dp(n,k,buildings,answers,w);

            //목표하고자 하는 건물 번호 w에 대해, 최소 건설 시간을 출력
            bw.write(String.valueOf(answers[w]));
            if(i!= t-1) {
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    //answers[w]를 결정하는 과정
    private static int dp(int n, int k, Construction[] buildings, int[] answers, int w) {
        if(buildings[w].flag) return answers[w];
        int result = buildings[w].constTime;

        for(int prv : buildings[w].prvNeeded) {
            result = Math.max(result, buildings[w].constTime + dp(n,k,buildings,answers,prv)); //w건물을 짓기 위한 바로 필요한 전단계의 건물들의 건설시간들 중, 가장 큰걸 선택해야 한다.
        }
        buildings[w].flag = true; //answers[w] 값이 도출되었음을 의미 (누적 건설시간(=answers[w])이 0인 경우까지 포함하기 위해 해당 코드 작성)

        return answers[w] = result;
    }

    private static class Construction {

        private ArrayList<Integer> prvNeeded = new ArrayList<>();
        private int constTime; //이 건물만 생각했을 때, 건설 시간
        private boolean flag = false;
    }
}