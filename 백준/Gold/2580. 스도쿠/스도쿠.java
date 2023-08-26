import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static int[][] answer;
    static int cnt; //board에서 0의 갯수
    static StringBuilder sb;

    public static void dfs(int rowLevel) {
        if(rowLevel == 9) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    answer[i][j] = board[i][j];
                }
            }
            return;
        }
        ArrayList<Integer> arr = findRestNumbers(rowLevel);
        ArrayList<Integer> zeroIdxes = findIdxes(rowLevel);

        int[] numUsed = new int[10]; //arr의 원소 중, 빈 칸을 채우는데 사용된 원소는, 해당 원소값을 index로 가지는 요소에 대해 1증가.
        fillIn(rowLevel, arr, zeroIdxes, numUsed, 0);
    }

    private static void fillIn(int rowLevel, ArrayList<Integer> arr, ArrayList<Integer> zeroIdxes, int[] numUsed, int level) {
        if(level == arr.size()) {
            dfs(rowLevel+1);
            return;
        }

        int idx = zeroIdxes.get(level);
        for(int num : arr) {
            if(numUsed[num] == 0 && checkColumn(idx, num) && checkBelongedSquare(rowLevel,idx,num)) {
                board[rowLevel][idx] = num;
                numUsed[num] = 1;
                cnt--;
                fillIn(rowLevel, arr, zeroIdxes, numUsed, level+1);
                if(answer[0][0] != 0) return; //답을 찾았으므로 중지
                board[rowLevel][idx] = 0;
                numUsed[num] = 0;
                cnt++;
            }
        }
    }

    private static boolean checkColumn(int idx, int num) {
        for(int i=0; i<9; i++) {
            if(board[i][idx] == num) return false;
        }
        return true;
    }

    private static boolean checkBelongedSquare(int rowLevel, int idx, int num) {
        int a = (rowLevel / 3) * 3;
        int b = (idx / 3) * 3;
        for(int i=a; i<a+3; i++) {
            for(int j=b; j<b+3; j++) {
                if(board[i][j] == num) return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> findRestNumbers(int rowLevel) {
        int[] tmpArr = new int[10];

        for(int j=0; j<9; j++) {
            tmpArr[board[rowLevel][j]]++; //board에 있는 숫자와 같은 index에 해당하는 원소를 1 증가
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(int k=1; k<=9; k++) {
            if(tmpArr[k] == 0) arr.add(k);
        }
        return arr;
    }

    private static ArrayList<Integer> findIdxes(int rowLevel) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int j=0; j<9; j++) {
            if(board[rowLevel][j] == 0) arr.add(j);
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        board = new int[9][9];
        answer = new int[9][9];
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) cnt++;
            }
        }
        dfs(0);

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(answer[i][j]);
                if(j != 8) sb.append(" ");
            }
            if(i != 8) sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}