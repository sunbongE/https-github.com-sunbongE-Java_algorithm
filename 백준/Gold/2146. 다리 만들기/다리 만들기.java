import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static int map[][], N, minLen = 987654321, areaV[][];
    static boolean v[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(bf.readLine());
        // 입력
        map = new int[N][N];
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 지도에 섬에 고유번호 먹이기
        int id = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    bfs(i, j, id);
                    id++;
                }
            }
        }

        // go라는 bfs으로 출발시키기.
//        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
//            	for (int i = 0; i < N; i++) {
//            		for (int j = 0; j < N; j++) {
                // 바다가 아니면. 호출.
                if (map[i][j] != 0) {
                    // 다리놓으면서 사용할 방문배열.
                    // 0이 아닌곳에서 전부 출발 시키고
                    areaV = new int[N][N];

                    v[i][j] = true;
                    // 새로운 방문처리할 배열 선언하고 가서 사용.
                    go(i, j);
                }
            }
        }
        System.out.println(minLen);

    }

    private static void go(int i, int j) {
        // 가지치기는 현재 최소값보다 이미 값이 크다면 종료
        // 도착 조건은 내 섬이 아니고 바다가 아닌곳에 방문하면 도착한것이고 최소값 비교 갱신.
        int nowId = map[i][j];
        
//        System.out.println(i+" "+j+" now: "+nowId);
        Queue<int[]> que = new LinkedList<int[]>();
        que.offer(new int[] { i, j });

        // 다리 길이 기록.
        areaV[i][j] = 0;
        
        int cur[], ci, cj, ni, nj;
        while(!que.isEmpty()) {
            cur = que.poll();
            ci = cur[0];
            cj = cur[1];
            // 가지치기 : 현재 다리 길이가 최소값보다 크면 종료.
            if(minLen<=areaV[ci][cj]-1) {
                return;
            }
            // 섬도착이면 최소값 갱신. 바다가 아니고 현재 섬이 아니면.
            if(map[ci][cj]!=0&&map[ci][cj]!=nowId) {
                minLen = Math.min(minLen, areaV[ci][cj]-1);
            }
            
            for (int k = 0; k < 4; k++) {
                ni = ci + dx[k];
                nj = cj + dy[k];
                // 내 땅이 아니고 미방문이면 큐에 포함.
                if(isIn(ni,nj)&&map[ni][nj]!=nowId&&areaV[ni][nj]==0) {
                    que.offer(new int[] {ni,nj});
                    areaV[ni][nj] = areaV[ci][cj]+1;
                }
            }
//            System.out.println("===================");
//            show2();
        }
    }

  

//    private static void show2() {
//    	for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(areaV[i][j]);
//            }
//            System.out.println();
//        }		
//	}
    // 땅에 고유한 ID설정.
	private static void bfs(int i, int j, int id) {
        Queue<int[]> que = new LinkedList<int[]>();
        que.offer(new int[] { i, j });
        v[i][j] = true;
        map[i][j] = id;

        int cur[], ci, cj, ni, nj;
        while (!que.isEmpty()) {
            cur = que.poll();
            ci = cur[0];
            cj = cur[1];
            for (int k = 0; k < 4; k++) {
                ni = ci + dx[k];
                nj = cj + dy[k];
                if (isIn(ni, nj) && !v[ni][nj] && map[ni][nj] != 0) {
                    que.offer(new int[] { ni, nj });
                    map[ni][nj] = id;
                    v[ni][nj] = true;
                }
            }

        }
    }

    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < N && 0 <= nj && nj < N;
    }
    
    
//    private static void show() {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//    }
}
