import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());

        for(int i=1; i<=t; i++) {
            int f = Integer.parseInt(br.readLine());
            HashMap<String, Person> map = new HashMap<>();
            for(int j=1; j<=f; j++) {
                st = new StringTokenizer(br.readLine());
                String prv = st.nextToken();
                String aft = st.nextToken();

                int result = union(prv, aft, map);
                bw.write(String.valueOf(result));
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int union(String prv, String aft, HashMap<String, Person> map) {
        if(!map.containsKey(prv)) map.put(prv, new Person(prv, 1));
        if(!map.containsKey(aft)) map.put(aft, new Person(aft, 1));
        String fa = find(prv,map);
        String fb = find(aft,map);
        if(!fa.equals(fb)) {
            map.get(fb).name = fa;
            map.get(fa).cnt += map.get(fb).cnt++;
        }
        int result = map.get(fa).cnt;
        return result;
    }

    private static String find(String name, HashMap<String, Person> map) {
        if(name.equals(map.get(name).name)) return name;
        return map.get(name).name = find(map.get(name).name, map);
    }

    private static class Person {
        String name;
        int cnt;

        Person(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }
}