import java.util.*;

class Main {
    
    public int solution(int n) {
        int answer = 0;
        if(n==1 || n==3) return -1;
        while(n>=5) {
            n -= 5;
            answer++;
        }
        if (n % 2 != 0) {
            n += 5;
            answer -= 1;
        }
        answer += (n/2);
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int n = sc.nextInt();
        System.out.print(main.solution(n));
    }
}