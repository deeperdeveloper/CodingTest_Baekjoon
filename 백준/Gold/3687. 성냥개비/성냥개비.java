import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {

    static int t; //테스트 케이스 갯수
    static long[] answers; //answers[i] => i개의 성냥개비를 활용하여 만들 수 있는 수 중, 최소의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        answers = new long[100+1];
        setInitAnswers();

        t = Integer.parseInt(br.readLine());
        for(int i=1; i<=t; i++) {
            int n = Integer.parseInt(br.readLine());
            String min = getMinValue(n);
            String max = getMaxValue(n);

            bw.write(min);
            bw.write(" ");
            bw.write(max);
            if(i!=t) bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static String getMinValue(int n) {
        if(n<=8 || answers[n] > 0) return String.valueOf(answers[n]);
        int k=9;

        //answers[k] ~ answers[n]까지 완성시킬 것이다.
        while(k<=n) {
            if(answers[k] > 0) {
                k++;
                continue;
            }
            long result = 0L;
            answers[k] = Long.MAX_VALUE;
            int digits = getDigits(k); //최솟값은 무조건 digits 자리이다. i가 8~14 => digits = 2, i가 15~21 => digits = 3
            for(int p=2; p<=7; p++) {
                long x = answers[k-p] - getPowerOfTen(digits);
                if(x >= 0) continue; //digits자리를 만들어야 하는데, answers[k-p]가 digits 자리이면, 최솟값이 아님은 명백하다.
                String tmp = String.valueOf(answers[k-p]);
                if(p!=6) {
                    for(int j=0; j<=digits-1; j++) {
                        String prv = tmp.substring(0,j);
                        String aft = tmp.substring(j,digits-1);
                        StringBuilder sb = new StringBuilder();
                        sb.append(prv);
                        sb.append(String.valueOf(answers[p]));
                        sb.append(aft);
                        result = Long.parseLong(sb.toString());
                        answers[k] = Math.min(result, answers[k]);
                    }
                } else {
                    for(int j=1; j<=digits-1; j++) {
                        String prv = tmp.substring(0,j);
                        String aft = tmp.substring(j,digits-1);
                        StringBuilder sb = new StringBuilder();
                        sb.append(prv);
                        sb.append(String.valueOf(0));
                        sb.append(aft);
                        result = Long.parseLong(sb.toString());
                        answers[k] = Math.min(result,answers[k]);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("6");
                    sb.append(tmp);
                    result = Long.parseLong(sb.toString());
                    answers[k] = Math.min(result,answers[k]);
                }
            }
            k++;
        }

        return String.valueOf(answers[n]);
    }

    private static long getPowerOfTen(int digits) {
        long result = 1L;
        for(int i=1; i<=digits-1; i++) {
            result = result * 10;
        }
        return result;
    }

    private static String getMaxValue(int n) {
        StringBuilder sb = new StringBuilder();
        if(n%2 == 0) {
            for(int i=1; i<=n/2; i++) {
                sb.append("1");
            }
        } else {
            sb.append("7");
            for(int i=1; i<n/2; i++) {
                sb.append("1");
            }
        }
        return sb.toString();
    }

    private static int getDigits(int k) {
        return (k-1)/7 + 1;
    }

    private static void setInitAnswers() {
        answers[2] = 1L;
        answers[3] = 7L;
        answers[4] = 4L;
        answers[5] = 2L;
        answers[6] = 6L; //0도 가능함을 반드시 인지하기
        answers[7] = 8L;
        answers[8] = 10L;
    }
}