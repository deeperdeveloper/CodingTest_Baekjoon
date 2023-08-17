import java.util.*;
import java.io.*;

public class Main {
    
    static int n;
    static ArrayList<Schedule> arr;
    static int[] cnt = new int[366]; //1일~365일 각 날짜에 대해서(=각 원소는), 해당 날짜의 일정의 갯수를 의미
    
    public String solution() {
        Collections.sort(arr); //각 일정을 시작일 기준 오름차순 정렬. (시작일이 동일시, 종료일 기준 내림차순 정렬)
        //각각의 일정의 시작날짜~종료날짜 사이의 날짜에 대해,해당 날짜값을 index로 가지는 cnt배열의 원소를 모두 1증가  
        for(Schedule s : arr) {
            int begin = s.startDay;
            int end = s.endDay;
            for(int i=begin; i<=end; i++) {
                cnt[i]++;
            }
        }
        
        //코팅지 작업은 아래와 같이 진행
        //    1. cnt배열의 앞쪽부터 진행
        //        1-1. 맨 처음 원소값이 0이 아닌 index값을 기록
        //        1-2. 진행하다가, 원소값이 0이 최초로 등장하는 index값을 기록
        //            1-2-1. 진행하면서, cnt값의 최댓값을 기록해두기
        //        1-3. 이제, (가로값) * (세로값)은 아래와 같이 구할 수 있다.
        //            1-3-1. ( (1-2에서의 index값) - (1-1에서의 index값)) * (1-2에서의 cnt 최댓값)
        //        1-4. 1-3의 결과값을(코팅지 면적) 최종 답에다가 더하기.
        //    2. 1번의 과정을 cnt배열의 마지막까지 진행하기
        
        int answer = 0;
        int maxCnt = 0;
        int coatingStart = 0;
        int coatingEnd = 0;
        boolean flag = false;
        for(int k=1; k<=365; k++) {
            if(cnt[k] == 0) {
                if(!flag) continue; //endDay 직후의 cnt[k] == 0이 아닌 경우.
                else { //endDay 직후의 cnt[k] == 0 인 경우
                    flag = false;
                    coatingEnd = k-1; //코팅지의 종료 날짜
                    answer += (coatingEnd - coatingStart + 1) * maxCnt;
                    coatingStart = 0; //코팅지의 시작 날짜 초기화
                    coatingEnd = 0; //코팅지의 종료 날짜 초기화
                    maxCnt = 0;
                }
            }
            else if(cnt[k] != 0) {
                if(!flag) { //코팅지에서, cnt[k] != 0인 최초의 k인 경우. (즉, 시작날짜의 k인 경우)
                    flag = true;
                    coatingStart = k;
                    maxCnt = cnt[k];
                } else {
                    if(maxCnt < cnt[k]) maxCnt = cnt[k]; //코팅지의 시작~종료 전까지는, cnt배열 원소의 최댓값을 갱신(즉, 코팅지의 세로 길이를 구함)
                }
            }
        }
        //cnt[365] != 0인 경우에는(=즉, 맨 마지막 날짜에도 일정이 있다면), 해당 코팅지의 경우는 위 for문에 고려되어 있지 않으므로, 아래에서 고려하여 answer에 더해준다.
        if(flag) {
            coatingEnd = 365;
            answer += (coatingEnd - coatingStart + 1) * maxCnt;
        }
        
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startDay = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            arr.add(new Schedule(startDay, endDay));
        }
        
        bw.write(main.solution());
        bw.flush();
        bw.close();
    }
}

class Schedule implements Comparable<Schedule> {
    int startDay;
    int endDay;
    
    Schedule(int startDay, int endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }
    
    @Override
    public int compareTo(Schedule s) {
        if(this.startDay == s.startDay) {
            return -(this.endDay - s.endDay); //종료일 기준 내림차순 정렬
        }
        return this.startDay - s.startDay; //시작일 기준 오름차순 정렬
    }
}