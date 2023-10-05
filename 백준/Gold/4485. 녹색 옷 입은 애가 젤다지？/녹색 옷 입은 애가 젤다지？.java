import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 박태호
 * 
 *         <pre>
 * 	풀이
 * 
 * 	N = 125 
 * 	빽트레킹으로 모두 돌아보기에는 NxN 은 너무 크기 때문에 cost로 풀어야한다고 생각한다.
 * 	n-1,n-1에서 시작해
 * 	4방향으로 점점 퍼지는 식으로 가면서
 *  값을 더해나가며
 *  값은 최소값으로 갱신한다.
 *  
 *  1. cost의 초기값은 전부 아주 큰 수로 채워줘야한다.
 *  2. min(map[i][j]+map[ni][nj], cost[ni][nj]) : 점화식??
 *  3.
 * 
 *         </pre>
 *
 */
public class Main {
	static int map[][], cost[][], N;
	// 상하좌우.
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {

			N = Integer.parseInt(bf.readLine());
			if (N == 0) {
				System.out.println(sb);
				return;
			}
			map = new int[N][N];
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(cost[i], 987654321);
			}

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(0,0);
			sb.append("Problem "+testCase+": "+cost[N-1][N-1]+"\n");
			testCase++;
		}
		
		
	}

	private static void bfs(int i, int j) {
		Queue<int[]> que = new LinkedList<int[]>();
		cost[i][j] = map[i][j];
		que.offer(new int[] {i,j});
		
		int cur[],ci,cj,ni,nj;
		while(!que.isEmpty()) {
			cur = que.poll();
			ci = cur[0];
			cj = cur[1];
			for (int k = 0; k < 4; k++) {
				ni = ci+dx[k];
				nj = cj+dy[k];
				if(isIn(ni,nj)) {	
					int tmp = (cost[ci][cj]+map[ni][nj]);
					if(cost[ni][nj]>tmp) {
						cost[ni][nj] =tmp;
						que.offer(new int[] {ni,nj});
					}
				}
				
			}
//			System.out.println(que.size());
//			showcost();
		}
		
		
		
	}

	private static boolean isIn(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}

//	private static void showcost() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(cost[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}

}
