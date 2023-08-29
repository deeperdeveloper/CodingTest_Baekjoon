import java.util.Scanner;

public class Main {

    static int n;
    static int[] nums;

    public static void solution(){
        if(n<=3) return;
        int k=3;
        while(k<=n){
            nums[k] = nums[k-1] + 1;
            if(k%3 == 0) {
                nums[k] = Math.min(nums[k-1], nums[k/3])+1;
            }
            if(k%2 == 0) {
                if(k%3 != 0) {
                    nums[k] = Math.min(nums[k-1], nums[k/2])+1; //1뺴는 것과 2나누는 것 중 최소
                } else {
                    nums[k] = Math.min(nums[k], nums[k/2]+1); // 최소((1빼는 것과 3나누는 것 중 최소), 2나누는 것)
                }
            }
            k++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        nums = new int[1000001];

        setInitValues();
        solution();

        System.out.print(nums[n]);
    }

    private static void setInitValues() {
        nums[1] = 0;
        nums[2] = 1;  // 2 -> 1
        nums[3] = 1;  // 3 -> 1
    }
}