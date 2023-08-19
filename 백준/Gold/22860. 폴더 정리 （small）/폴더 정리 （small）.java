import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Folder> arr = new ArrayList<>(); //모든 폴더 객체를 저장하는 ArrayList;
    static int cnt = 0; //파일 총 갯수. 전역으로 선언하여 dfs를 활용
    static HashSet<String> types = new HashSet<>(); //파일 종류를 모아둔 집합. 전역으로 선언하여 dfs를 활용

    public void count(Folder curFolder) {
        cnt += curFolder.files.size();
        for(String f : curFolder.files) {
            types.add(f); //curFolder 내부의 파일 종류 추가
        }
        for(Folder folder : curFolder.subFolders) {
            count(folder); //curFolder 하위 폴더에 있는 파일들을 모두 고려
        }
    }

    public String solution(StringTokenizer st) {
        String answer = "";

        //경로의 맨 첫번째 폴더 (main 폴더 찾기)
        Folder curFolder = null;
        String firstName = st.nextToken(); //main
        for(Folder folder : arr) {
            if(folder.name.equals(firstName)) {
                curFolder = folder;
                break;
            }
        }
        
        //경로의 이후 2번째 폴더부터 찾기
        while(st.hasMoreTokens()) {
            String curFolderName = st.nextToken();
            for(Folder sub : curFolder.subFolders) {
                if(sub.name.equals(curFolderName)) {
                    curFolder = sub;
                    break;
                }
            }
        }

        //이제, curFolder 및 하위 폴더에 존재하는 파일의 총 종류 갯수 및 파일의 총 갯수 구하기
        count(curFolder);

        answer = types.size() + " " + cnt;
        cnt = 0;
        types.clear();
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 폴더 총 갯수
        int m = Integer.parseInt(st.nextToken()); // 파일 총 갯수

        for(int i=2; i<=n+m+1; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            Folder folder = null;
            //기존에 이미 저장된 folder라면 해당 folder 객체를 사용
            for(Folder f : arr) {
                if(f.name.equals(name)) {
                    folder = f;
                    break;
                }
            }
            //arr에 저장되어 있지 않은 폴더라면, 새로 folder 객체를 생성해서 arr에 저장
            if(folder == null) {
                folder = new Folder(name);
                arr.add(folder);
            }
            String undefinedName = st.nextToken(); //서브 폴더명 혹은 파일 명
            int c = Integer.parseInt(st.nextToken()); //폴더 또는 파일 판별 숫자
            if(c==0) {
                folder.files.add(undefinedName); //파일 추가
            } else {
                Folder subFolder = null;
                //이미 기존의 총 폴더 목록에 있는 경우
                for(Folder f : arr) {
                    if(f.name.equals(undefinedName)) {
                        subFolder = f;
                        folder.subFolders.add(subFolder);
                        break;
                    }
                }
                //기존의 총 폴더 목록에 없는 경우, 새로 폴더를 만들어 "서브 폴더"로 지정.
                if(subFolder == null) {
                    subFolder = new Folder(undefinedName);
                    arr.add(subFolder);
                    folder.subFolders.add(subFolder);
                }
            }
        }

        int q = Integer.parseInt(br.readLine());
        for(int k=1; k<=q; k++) {
            st = new StringTokenizer(br.readLine(), "/");
            bw.write(main.solution(st));
            if(k!=q) bw.write("\n");
        }
        bw.flush();
        bw.close();
    }


    private static class Folder {

        String name;
        ArrayList<Folder> subFolders = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }
    }
}


