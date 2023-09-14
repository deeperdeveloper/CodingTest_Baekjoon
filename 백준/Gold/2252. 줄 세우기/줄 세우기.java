import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

//위상정렬 구현용 문제
public class Main {

    static int n,m; // 학생들의 수 n, 2명씩 키를 비교한 횟수 m
    static int[] degrees; //degrees[cur] => cur 이전에 위치하는 학생의 수
    static ArrayList<Integer>[] arr; // arr[i] => i보다 뒤에 있는 사람들의 ArrayList
    static int[] answers; //학생들 배치 순서를 고려하여, n명의 학생들이 삽입된 배열 (index=1 부터 시작)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        degrees = new int[n+1];
        arr = new ArrayList[n+1];
        answers = new int[n+1];
        fillArrayAsArrayList();

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int prv = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            degrees[cur]++;
            arr[prv].add(cur);
        }

        solution(); //answers 배열의 각 칸에 학생들을 배치시킴
        for(int i=1; i<=n; i++) {
            bw.write(String.valueOf(answers[i]));
            if(i!=n) bw.write(" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        Queue<Integer> queue = new LinkedList<>(); //여기에 담겨져있다가 poll 되는 cur원소는, cur원소 다음에 위치하는 모든 원소에 대해 영향을 미친 후, 배열에 담김을 의미한다.

        //본인보다 앞선 사람이 없는 경우에 먼저 queue에 담음
        for(int i=1; i<=n; i++) {
            if(degrees[i] == 0) queue.offer(i);
        }

        //queue에서 1개씩 뽑아서 answers에 배치하며, 뽑은 학생 바로 뒤에 위치한 학생에 대해 degree 1감소(거쳤음을 확인)
        int index = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            answers[index++] = cur;
            for(int next : arr[cur]) {
                degrees[next]--;
                if(degrees[next] == 0) queue.offer(next);
            }
        }
    }

    private static void fillArrayAsArrayList() {
        for(int i=1; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
    }
}