import java.util.*;
import java.io.*;

public class Main {
    
    public String solution(int n, String colors) {
        int answer = 1;
        char[] colors_arr = colors.toCharArray();
        char first_color = colors_arr[0];
        
        int t = -1;
        //맨 첫번째 color랑 다른 color인 배열의 원소 중, 가장 큰 index를 t에 저장
        for(int i=n-1; i>=0; i--) {
            if(colors_arr[i] != first_color) {
                t=i;
                break;
            } 
        }
        
        //t==-1이면, 모두 첫번째 color와 동일한 color로 이루어져 있으므로, 1을 반환
        if(t==-1) return String.valueOf(answer);
        else {
            for(int i=0; i<n-1; i++) {
                if(colors_arr[i] == colors_arr[t] && colors_arr[i] != colors_arr[i+1]) {
                    answer++; // 서로 다른 색깔이 나타나는 index에 대해, 작업횟수를 더함.
                }
            }
            if(t==n-1) answer++; // t== n-1이면, colors_arr의 맨 끝 부분에는 (1개 이상의 연속된 색깔인) colors_arr[t] 이 존재하므로, 이 경우까지 고려
        }
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String colors = br.readLine();
        bw.write(main.solution(n,colors));
        bw.flush();
        bw.close();
    }
}