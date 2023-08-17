import java.util.*;
import java.io.*;

public class Main {
    
    static int n; // 격자판 가로 길이 및 세로 길이
    static int[][] board; // 자리 배치 격자판
    static HashSet<Integer>[] list; // k번째 학생이 좋아하는 학생 리스트
    static int[] students; //입력받은 줄의 맨 첫번째 학생을 순서대로 배열에 저장 (각 줄의 맨 첫번째 원소로)
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        students = new int[n*n];
        list = new HashSet[n*n + 1];
        //list = new HashSet<Integer>[n*n + 1]; 은 컴파일 에러가 뜬다. (아래 출처 참조)
        
        //아래 과정은 입력받기
        int p = 0; //students 배열에 저장하기 위한 index 선언
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                //맨 첫번째 원소는, students[p]에 저장
                students[p] = Integer.parseInt(st.nextToken());
                list[students[p]] = new HashSet<>();
                //이후 원소는, students[p] index의 HashSet에 저장 (총 4개의 원소가 저장된다)
                while(st.hasMoreTokens()) {
                    list[students[p]].add(Integer.parseInt(st.nextToken()));
                }
                p++;
            }
        }
        
        board[1][1] = students[0]; //규칙에 의해, 가장 작은 행 번호와 열 번호의 칸에 할당된다.
        
        bw.write(main.solution());
        bw.flush();
        bw.close();
    }
    
    /**
    * // 1. students[1] ~ students[n*n -1]까지, 아래의 과정을 진행
    *    
    *    //     1-1. board의 모든 빈 칸에 대해, students[k]가 들어갈 수 있는 후보라고 생각
    *    
    *    
    *    //     1-2. 빈 칸이 있다면, 해당 빈 칸에 아래의 정보들을 기입하고, PriorityQueue에 대입한다.
    *    //         - 해당 빈 칸 상하 좌우에 대해, students[k]의 친한 사람 수
    *    //         - 해당 빈 칸 상하 좌우에 대해, 빈 칸의 수
    *    
    *    //     1-3. 이제 1-2로부터 도출된 PriorityQueue의 가장 첫번째 원소를 
    *    
    *    
    *    // 2. 각 board[i][j]에 대해서, 만족도를 조사한다.
    */
    public String solution() {
        int[] dx = {-1,0,0,1};
        int[] dy = {0,1,-1,0};
        
        for(int i=1; i<n*n; i++) {
            PriorityQueue<Coordinate> pq = new PriorityQueue<>();
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    if(board[j][k] != 0) continue;
                    else {
                        Coordinate cor = new Coordinate(j,k);
                        for(int t=0; t<4; t++) {
                            int nx = j+dx[t];
                            int ny = k+dy[t];
                            if(nx<0 || nx>n-1 || ny<0 || ny>n-1) continue;
                            else {
                                if(board[nx][ny] == 0) cor.emptyCnt++;
                                else {
                                    if(list[students[i]].contains(board[nx][ny])) cor.cnt++;
                                }
                            }
                        }
                        pq.offer(cor);
                    }
                }
            }
            int x = pq.peek().i;
            int y = pq.peek().j;
            board[x][y] = students[i];
        }
        
        //각각의 격자판에 있는 학생에 대해서, 만족도를 조사
        int answer = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int tmp = 0;
                for(int t=0; t<4; t++) {
                    int nx = i+dx[t];
                    int ny = j+dy[t];
                    if(nx<0 || nx>n-1 || ny<0 || ny>n-1) continue;
                    if(list[board[i][j]].contains(board[nx][ny])) tmp++;
                }
                if(tmp == 0) answer += 0;
                if(tmp == 1) answer += 1;
                if(tmp == 2) answer += 10;
                if(tmp == 3) answer += 100;
                if(tmp == 4) answer += 1000;
            }
        }
        return String.valueOf(answer);
    }
}

class Coordinate implements Comparable<Coordinate>{
    
    int i;
    int j;
    int cnt = 0;
    int emptyCnt = 0;
    
    Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
    @Override
    public int compareTo(Coordinate c) {
        if(this.cnt == c.cnt) {
            if(this.emptyCnt == c.emptyCnt) {
                if(this.i == c.i) {
                    return this.j - c.j;
                }
                return this.i - c.i;
            }
            return c.emptyCnt - this.emptyCnt;
        }
        return c.cnt - this.cnt;
    }
}

//HashSet 사용되는 곳

//배열 vs generic
//출처 : https://www.baeldung.com/java-generic-array