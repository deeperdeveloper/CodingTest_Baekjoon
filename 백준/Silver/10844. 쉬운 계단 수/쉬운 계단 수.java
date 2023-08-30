import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    static int n;
    static int[] nums; //계단수 중, index에 해당하는 숫자가 맨 마지막에 나타나는 숫자의 갯수
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[10];
        
        setInitValues();
        solution();
        
        System.out.print(getAnswer());
    }
    
    private static void setInitValues() {
        for(int i=1; i<=9; i++) {
            nums[i] = 1;
        }
    }
    
    private static void solution() {
        if(n==1) return;
        int k=2;
        while(k<=n) {
            int[] tmp = setNextNums();
            for(int i=0; i<=9; i++) {
                nums[i] = tmp[i];
            }
            k++;
        }
    }
    
    private static int[] setNextNums() {
        int[] tmp = new int[10];
        tmp[0] = nums[1];
        tmp[9] = nums[8];
        for(int i=1; i<=8; i++) {
            tmp[i] = (nums[i-1] + nums[i+1]) % 1000000000;
        }
        return tmp;
    }
    
    private static int getAnswer() {
        int answer = 0;
        for(int i=0; i<=9; i++) {
            answer += nums[i];
            if(answer >= 1000000000) answer = answer % 1000000000;
        }
        return answer;
    }
}