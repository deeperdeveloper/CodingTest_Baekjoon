import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int n,k; //먹이의 갯수 n, 도달 최소 만족도 k
    static long[] seqs; //seqs[i] => i번째 입력된 먹이에 대한 만족도
    static long[] prefixSeqs; // prefixSeqs[k] => seqs[1] + seqs[2] + ... + seqs[k]
    static long[] answers; // answers[k] => seqs[1] ~ seqs[k]를 잘 활용하여 도출한 최대 축적 탈피 에너지
    static int p1,p2; //포인터 개념. seqs[p1] ~ seqs[p2] 에 대해 다룬다는 의미이며, 항상 p1<=p2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        seqs = new long[n+1]; //index 1 ~ index n까지 사용
        prefixSeqs = new long[n+1]; //index 1 ~ index n까지 사용
        answers = new long[n+1]; //index 1 ~ index n 까지 사용

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            seqs[i] = Long.parseLong(st.nextToken());
            prefixSeqs[i] += prefixSeqs[i-1] + seqs[i];
        }

        p1 = 1;
        p2 = 2;
        setInitAnswers();

        solution();

        System.out.print(answers[n]);
    }

    private static void setInitAnswers() {
        if(seqs[1] >= k) {
            answers[1] = seqs[1] - k;
            p1++;
        }
    }

    //answers[p2] 결정
    private static void solution() {
        if(n==1) return;
        while(p2 <= n) {
            if(p1 > p2) {
                p2++;
                continue;
            }

            //아래에는 p1 <= p2인 경우만 다루게 된다.
            if(prefixSeqs[p2] - prefixSeqs[p1-1] < k) {
                if(answers[p2] > 0) { //이미 answers[p2]는 갱신된 값이므로, 손대지 않고 바로 다음 index로 넘어간다.
                    p2++;
                    continue;
                }
                answers[p2] = answers[p2-1];
                p2++;
                continue;
            }

            if(prefixSeqs[p2] - prefixSeqs[p1-1] >= k) {
                long tmp = prefixSeqs[p2] - prefixSeqs[p1-1] - k; //p1~p2 까지의 seqs의 탈피에너지
                answers[p2] = Math.max(Math.max(answers[p2], answers[p2-1]), tmp + answers[p1-1]); //seqs[p2]가 포함되지 않은 경우의 최대 탈피에너지와, seqs[p2]를 포함했을 때의 최대 탈피에너지 중, 최댓값을 택한다.
                p1++;
                continue;
            }
        }
    }
}