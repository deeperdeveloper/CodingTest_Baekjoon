import java.util.*;
import java.io.*;

public class Main {

    static int n,m; //격자판 가로 길이, 마법 시전 줄 갯수
    static int[][] board;
    static int[] row;
    static int[] direction = {1,2,3,4}; // 마법 방향 : 위,아래,왼,오
    static int[] fireBeads; //각 i에 대해, i번 index는 모든 마법 동안 i번 구슬이 폭발한 총 갯수를 의미.

    public static void solution(int dir, int num) {
        // 1. 마법 한 번 실행 후, row의 빈 칸(0)을 메우기
        //    - tmpArr에 row의 1이상의 숫자의 원소를(맨 첫번째 0 제외)옮겨 담은 후, 다시 row로 복사할 것이다.


        doMagic(dir, num);
        removeZeros();

        // 2. row에서 1이상의 연속된 숫자가 4개 이상 존재할 때마다, 해당 연속된 숫자를 0으로 바꾸고 빈칸을 메우는 과정
        //    - 이 때, 폭발한 구슬 번호와 해당 번호가 몇 개 폭발했는지 기록
        
        while(true){
            if(row[1] == 0) return;
            int cnt = 1;
            boolean flag = false; //한번이라도 폭발한 적이 있다면 flag = true;
            int k = 2;
            for(; k<n*n; k++) {
                if(row[k] == row[k-1]) {
                    cnt++;
                }
                else {
                    if(cnt >= 4) {
                        fireBeads[row[k-1]] += cnt;
                        for(int j=k-1; j>k-1-cnt; j--) {
                            row[j] = 0;
                        }
                        flag = true;
                    }
                    cnt = 1;
                    if(row[k] == 0) break;
                }
            }
            if(!flag) break; //폭발이 한번이라도 안 일어났다면, 이제 앞으로의 폭발이 없으므로, 폭발 과정 종료

            //폭발이 일어나고 난 이후, 0의 배열을 정리
            removeZeros();
        }

        // 3. row에서 연속된 숫자(1개 포함)을 그룹 지어서, 아래의 2가지 구슬로 재탄생 시킨 후, 탄생 시킨 순서대로 row에 배치
        //    - 그룹 내 해당 숫자 번호의 구슬 갯수, 구슬 숫자 번호
        int cnt = 1;
        int idx = 1;
        int[] newRow = new int[n*n];
        int i=2;
        for( ; i<n*n; i++) {
            if(row[i] == row[i-1]) {
                cnt++;
                continue;
            } else {
                newRow[idx++] = cnt;
                if(idx == n*n) break;
                newRow[idx++] = row[i-1];
                if(row[i] == 0 || idx == n*n) break;
                cnt = 1;
            }
        }
        //    - 맨 마지막 연속된 구슬의 경우를 처리
        if(i == n*n && idx<n*n) {
            newRow[idx++] = cnt;
            if(idx < n*n) {
                newRow[idx] = row[i-1];
            }
        }
        //    -newRow 배열을 row로 복사
        rowAfterMagic(newRow);
    }

    private static void doMagic(int dir, int num) {
        int start;
        if(dir == 1) start = 7;
        else if(dir == 2) start = 3;
        else if(dir == 3) start = 1;
        else start = 5;
        doMagic_setZero(start, num);
    }

    private static void doMagic_setZero(int start, int num) {
        int diff = 0;
        int index = 0;
        int cnt = 1;
        while(cnt <= num) {
            diff = (start + 8*(cnt-1));
            index += diff;
            row[index] = 0;
            cnt++;
        }
    }

    private static void removeZeros() {
        int[] tmpArr = new int[n*n];
        int i=1;
        int p=0;
        for(int j=1; j<n*n; j++) {
            if(row[j] != 0) {
                tmpArr[i++] = row[j];
                p = j;
            }
        }
        for(int k=1; k<=p; k++) {
            row[k] = tmpArr[k];
        }
    }

    private static void rowAfterMagic(int[] newRow) {
        for(int i=1; i<n*n; i++) {
            row[i] = newRow[i];
        }
    }

    public static void main(String[] args) throws IOException {
        //입력 값 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        row = new int[n*n];
        fireBeads = new int[4]; //i번째 원소는, i번 구슬이 폭발한 총 갯수 (i=1 ~ i=3)

        //board 설정
        int tmp = 0;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int j=0;
            while(st.hasMoreTokens()) {
                board[tmp][j++] = Integer.parseInt(st.nextToken());
            }
            tmp++;
        }

        //board의 원소를 row에 옮겨담기.

        boolean flag = true; //true이면 board의 왼쪽, 아래쪽 이동. false이면 board의 오른쪽, 위쪽 이동
        boolean isBreakable = false;
        int cnt = 1;
        int row_index = 1; //row의 0번째는 상어임.
        int x = (n-1)/2;
        int y = (n-1)/2;
        while(true) {
            if(flag) {
                for(int i=1; i<=cnt; i++){
                    //board의 맨 위쪽 줄에서, 왼쪽으로 갈 때 board의 경계를 넘을 수 있는 경우는 별도의 식으로 처리
                    if(n*n - row_index < n+1) {
                        for(int j=1; j<=cnt-1; j++) {
                            row[row_index++] = board[x][--y];
                        }
                        isBreakable = true;
                        break;
                    }
                    row[row_index++] = board[x][--y];
                }
                if(isBreakable) break;

                for(int i=1; i<=cnt; i++){
                    row[row_index++] = board[++x][y];
                }
                cnt++;
                flag = false;
            } else {
                for(int i=1; i<=cnt; i++){
                    row[row_index++] = board[x][++y];
                }

                for(int i=1; i<=cnt; i++){
                    row[row_index++] = board[--x][y];
                }
                cnt++;
                flag = true;
            }
        }

        //마법 m번 실행
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            solution(dir, num);
        }

        //폭발한 총 구슬 갯수를 이용하여, 문제가 요구하는 답 도출
        bw.write(answer());
        bw.flush();
        bw.close();
    }

    private static String answer() {
        int sum = 0;
        for(int i=1; i<=3; i++) {
            sum += (i * fireBeads[i]);
        }
        return String.valueOf(sum);
    }
}