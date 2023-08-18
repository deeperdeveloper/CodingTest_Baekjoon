import java.util.*;
import java.io.*;

//리팩토링 반드시 병행하기

public class Main {
    
    static Node[] arr;
    
    // 1. 유사 중위 순회의 끝을 찾기
    // 2. 부모 노드(1번 노드)로부터 규착에 따라 시행
    public String solution() {
        //1. 유사 중위 순회의 끝은 아래와 같이 찾는다
        //    1-1. 루트 노드(1번 노드)에서 오른쪽 노드가 존재한다면, 계속 오른쪽 노드로 진행한다
        //    1-2. 1-1번을 진행하던 도중, 오른쪽 노드가 존재하지 않는 노드가 최초로 등장 시, 해당 노드가 원하는 노드이다.
        
        Node endNode = arr[1]; //root 노드부터 시작
        while(endNode.rightNode != null) {
            endNode = endNode.rightNode;
        }
        
        //2.부모 노드(1번 노드) 로부터 규칙에 따라 시행
        //    2-1. curNode와 endNode(유사 중위 순회 끝)과 일치하며, curNode의 왼쪽 자식 노드가  while문 탈출
        Node curNode = arr[1];
        int answer = 0;
        while(curNode != endNode || (!curNode.isLeftVisited && curNode.leftNode != null)) {
            if(curNode.leftNode != null) {
                if(!curNode.isLeftVisited) {
                    curNode.isLeftVisited = true;
                    curNode = curNode.leftNode;
                    answer++;
                    continue;
                } else {
                    //leftNode를 이미 방문했으며, rightNode가 null이 아닌 경우
                    if(curNode.rightNode != null) {
                        //rightNode를 아직 방문한 적이 없는 경우
                        if(!curNode.isRightVisited) {
                            curNode.isRightVisited = true;
                            curNode = curNode.rightNode;
                            answer++;
                            continue;
                        } else { //rightNode를 방문한 적이 있는 경우
                            curNode = curNode.parentNode;
                            answer++;
                            continue;
                        }
                    } else { //leftNode를 이미 방문했으며, rightNode가 null 인경우
                        curNode = curNode.parentNode;
                        answer++;
                        continue;
                    }
                }
            } else {
                //leftNode가 null이며, rightNode가 null이 아닌 경우
                if(curNode.rightNode != null) {
                    //rightNode를 아직 방문한 적이 없는 경우
                    if(!curNode.isRightVisited) {
                        curNode.isRightVisited = true;
                        curNode = curNode.rightNode;
                        answer++;
                        continue;
                    } else { //rightNode를 방문한 적이 있는 경우
                        curNode = curNode.parentNode;
                        answer++;
                        continue;
                    }
                } else { //leftNode도 null이며, rightNode가 null 인경우
                    curNode = curNode.parentNode;
                    answer++;
                    continue;
                }
            }
        }
        
        return String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new Node[n+1];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            //1.첫번째 번호에 해당하는 node가 존재하지 않는다면, 해당 node를 만든 후 아래의 과정 시행
            //  1-1.  arr의 해당 번호의 index에 해당하는 자리에 node를 할당
            //
            //2.첫번째 번호에 해당하는 node가 존재한다면, 이 과정은 생략
            int curNum = Integer.parseInt(st.nextToken());
            Node node = null;
            if(arr[curNum] == null){
                node = new Node(curNum);
                arr[curNum] = node;
            } else {
                node = arr[curNum];
            }
            
            //node의 leftNode 배정
            //1. leftNode가 존재한다면 
            //    2. leftNum에 해당하는 Node가 이미 존재한다면, 해당 leftNode의 주소 배정.
            //        2-(1). 이 때, leftNode의 parentNode 역시 node로 배정해야 함.
            //    3. leftNum에 해당하는 Node가 존재하지 않는다면, leftNode를 새로 만든 후, 해당 leftNode의 주소 배정
            //        3-(1). 이 때, leftNode의 parentNode 역시 node로 배정해야 함.
            //        3-(2). arr의 leftNum의 index에 해당하는 자리에 leftNode를 할당
            
            int leftNum = Integer.parseInt(st.nextToken());
            if(leftNum != -1) {
                if(arr[leftNum] != null) {
                    node.leftNode = arr[leftNum];
                } else {
                    Node leftNode = new Node(leftNum);
                    node.leftNode = leftNode;
                    arr[leftNum] = leftNode;
                }
                arr[leftNum].parentNode = node;
            }
            
            //node의 rightNode 배정
            //    leftNode 배정방식과 동일
            
            int rightNum = Integer.parseInt(st.nextToken());
            if(rightNum != -1) {
                if(arr[rightNum] != null) {
                    node.rightNode = arr[rightNum];
                } else {
                    Node rightNode = new Node(rightNum);
                    node.rightNode = rightNode;
                    arr[rightNum] = rightNode;
                }
                arr[rightNum].parentNode = node;
            }
        }
        
        bw.write(main.solution());
        bw.flush();
        bw.close();
    }
}


class Node {
    
    int curNum;
    Node parentNode;
    Node leftNode;
    Node rightNode;
    boolean isLeftVisited = false;
    boolean isRightVisited = false;
    
    Node(int curNum) {
        this.curNum = curNum;
    }
}