import java.util.*;
import java.io.*;

//리팩토링(메인 메소드만 진행)
//리팩토링(solution() 메소드 진행)
//리팩토링(cnt와 types에 대해서, static 멤버 변수로 선언하지 않고, 대신 큰 객체 내부에 정의)
//리팩토링 마지막 단계(private Method를 코드의 아랫부분에 배치)

public class Main {

    static ArrayList<Folder> arr = new ArrayList<>(); //모든 폴더 객체를 저장하는 ArrayList;

    public String solution(StringTokenizer st) {
        String answer = "";

        //경로의 마지막 폴더를 찾기
        Folder curFolder = getLastFolder(st);

        //이제, curFolder 및 하위 폴더에 존재하는 파일의 총 종류 갯수 및 파일의 총 갯수 구하기
        FilesInfo filesInfo = new FilesInfo();
        count(curFolder, filesInfo);

        return getAnswer(filesInfo);
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
            String name = st.nextToken(); //폴더 이름
            
            Folder folder = setFolder(name);

            String undefinedName = st.nextToken(); //서브 폴더명 혹은 파일 명
            int c = Integer.parseInt(st.nextToken()); //폴더 또는 파일 판별 숫자
            
            setSubFoldersAndFiles(undefinedName, c, folder); //부모 폴더 하위의 폴더와 파일을 지정
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
    
    public void count(Folder curFolder, FilesInfo filesInfo) {
        filesInfo.cnt += curFolder.files.size();
        for(String f : curFolder.files) {
            filesInfo.types.add(f); //curFolder 내부의 파일 종류 추가
        }
        for(Folder folder : curFolder.subFolders) {
            count(folder, filesInfo); //curFolder 하위 폴더에 있는 파일들을 모두 고려
        }
    }
    
    private Folder getFirstFolder(String firstName) {
        for(Folder folder : arr) {
            if(folder.name.equals(firstName)) {
                return folder;
            }
        }
        return null; //형식상 맞춰둠.
    }
    
    private Folder getLastFolder(StringTokenizer st) {
        String firstName = st.nextToken();
        Folder curFolder = getFirstFolder(firstName);
        while(st.hasMoreTokens()) {
            String curFolderName = st.nextToken();
            for(Folder sub : curFolder.subFolders) {
                if(sub.name.equals(curFolderName)) {
                    curFolder = sub;
                }
            }
        }
        return curFolder;
    }
    
    private String getAnswer(FilesInfo filesInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(filesInfo.types.size());
        sb.append(" ");
        sb.append(filesInfo.cnt);
        return sb.toString();
    }
    
    private static Folder setFolder(String name) {
         //기존에 이미 저장된 folder라면 해당 folder 객체를 사용
         for(Folder f : arr) {
             if(f.name.equals(name)) {
                 return f;
             }
         }
         //arr에 저장되어 있지 않은 폴더라면, 새로 folder 객체를 생성해서 arr에 저장
         Folder folder = new Folder(name);
         arr.add(folder);
         return folder;
    }
    
    private static void setSubFoldersAndFiles(String undefinedName, int c, Folder folder) {
        if(c==0) {
            folder.files.add(undefinedName); //파일 추가
        } else {
            Folder subFolder = setFolder(undefinedName);
            folder.subFolders.add(subFolder);
        }        
    }


    private static class Folder {

        String name;
        ArrayList<Folder> subFolders = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }
    }
    
    private static class FilesInfo {
        int cnt = 0; //파일 총 갯수
        HashSet<String> types = new HashSet<>(); //파일 종류를 모아둔 집합.
        
        FilesInfo() {}
    }
}
