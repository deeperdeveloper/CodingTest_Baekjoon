import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static int n,m; //가수 수 n, 보조 pd 수 m
    static ArrayList<Integer>[] nextSingersList; //arr[i] => i의 바로 다음 가수 목록
    static int[] degrees; //degrees[i] => 가수 i 바로 이전의 가수의 수
    static int[] answers; //순서를 고려하여 최종적으로 배치한 가수 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nextSingersList = new ArrayList[n+1];
        fillNextSingersList();
        degrees = new int[n+1];
        answers = new int[n+1];

        //가수 관계 설정 및 degrees 설정
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] tmp = new int[cnt+1];
            for(int j=1; j<=cnt; j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
                if(j!=1) {//i가 2이상일 때
                    nextSingersList[tmp[j-1]].add(tmp[j]);
                    degrees[tmp[j]]++;
                }
            }
        }

        solution();

        if(answers[n] == 0) bw.write("0"); //가수 배치가 불가능한 경우. queue가
        else {
            for(int i=1; i<=n; i++) {
                bw.write(String.valueOf(answers[i]));
                if(i!=n) bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        Queue<Integer> queue = new LinkedList<>();

        //제일 최초로 나올 수 있는 가수 선정
        for(int i=1; i<=n; i++) {
            if(degrees[i] == 0) queue.offer(i);
        }

        //queue에서 poll한 원소는 바로 다음 가수에 대한 관계에 대해 확인 logic을 수행한 후, answers에 배치
        int index = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : nextSingersList[cur]) {
                degrees[next]--;
                if(degrees[next] == 0) queue.offer(next);
            }
            answers[index++] = cur;
        }
    }

    private static void fillNextSingersList() {
        for(int i=1; i<=n; i++) {
            nextSingersList[i] = new ArrayList<>();
        }
    }


}