import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static int g,p; //게이트 갯수 g, 비행기 댓수 p
    static int[] emptyGates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        emptyGates = new int[g+1];
        fillEmptyGates();

        int result = 0;
        boolean flag = false;
        for(int i=1; i<=p; i++) {
            int hopingGate = Integer.parseInt(br.readLine());
            int root = find(hopingGate);
            if(root == 0) break;
            result++;
        }

        System.out.println(result);
    }
    
    private static int find(int hopingGate) {
        if(hopingGate == emptyGates[hopingGate]) {
            emptyGates[hopingGate]--;
            return hopingGate;
        }
        
        return emptyGates[hopingGate] = find(emptyGates[hopingGate]);
    }

    private static void fillEmptyGates() {
        for(int i=1; i<=g; i++) {
            emptyGates[i] = i;
        }
    }
}