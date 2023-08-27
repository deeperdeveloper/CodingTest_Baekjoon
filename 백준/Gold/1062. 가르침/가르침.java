import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    static int n,k;
    static ArrayList<HashSet> arr_set;
    static char[] alphabets = {'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l','m','o',
                               'p','q','r','s','u','v','w','x','y','z'}; //a,n,t,i,c 제외
    static boolean[] flags;
    static int answer;
    static int result;
    
    
    public static void solution(int idx, int level) {
        if(level == k-5) { //k-5개의 알파벳을 모두 선택시
            ArrayList<Character> choosed = new ArrayList<>();
            for(int i=0; i<21; i++) {
                if(flags[i]) choosed.add(alphabets[i]);
            }

            int result = 0;
            int[] cnts = new int[n]; //각 HashSet이 담고 있는 알파벳의 갯수를 저장
            
            for(int i=0; i<n; i++) {
                cnts[i] = arr_set.get(i).size(); //각 HashSet의 사이즈를 저장
            }
            
            for(char c : choosed) {
                for(int i=0; i<arr_set.size(); i++) {
                    if(arr_set.get(i).contains(c)) cnts[i]--;
                }
            }
            
            for(int i=0; i<arr_set.size(); i++) {
                if(cnts[i] == 0) result++;
            }
            
            answer = Math.max(result, answer);
            return;
        }
        //k-5개 알파벳 선택 과정 중, level번째 알파벳 선택 과정 (level은 0을 포함)
        for(int i=idx; i<21; i++) {
            if(flags[i]) continue;
            flags[i] = true;
            solution(i, level+1);
            flags[i] = false;
        }
    }

    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr_set = new ArrayList<HashSet>();
        flags = new boolean[alphabets.length];

        for(int i=0; i<n; i++) {
            String inputStr = br.readLine();
            HashSet<Character> hs = new HashSet<>();
            for(char c : inputStr.toCharArray()) {
                if(c != 'a' && c!= 'n' && c!= 't' && c!= 'i' && c!= 'c') hs.add(c);
            }
            arr_set.add(hs);
        }
        
        solution(0,0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}