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
 * <pre>
 * 	풀이
 * 	각 노드의 진입차수를 누적하여 기록한다.
 * 	내 진입과 진출 차이 == 1 이면 자신이 몇번째인지 알 수 있다.
 * 	
 * </pre>
 *
 */
public class Solution {
	static int N,M,adj[][],up,down;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		int ans;
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(bf.readLine());
			M = Integer.parseInt(bf.readLine());
			adj = new int[N+1][N+1];
			StringTokenizer st;
			int a,b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				// 유향 a->b
				adj[a][b]=1;
			}
			for (int num = 1; num <= N; num++) {
				up=0;
				down=0;
				bfs(num, new boolean[N+1]);
				Rbfs(num, new boolean[N+1]);
				if(up+down == N-1) {
					ans++;
				}
			}
			sb.append("#"+tc+" "+ans+"\n");
//			show();
		}
		System.out.println(sb);
		
		
	}
	
	
	
	
	
	private static void bfs(int num, boolean[] v) {
		// 큐생성
		Queue<Integer> que = new LinkedList<Integer>();
		// 삽입
		que.offer(num);
		// 방문처리
		v[num] = true;
		
		
		//반복문
		int cur;
		while(!que.isEmpty()) {
			// 큐에서 꺼냄
			cur = que.poll();
			// 방문처리가 안되어있으면. 
			for (int i = 1; i <= N; i++) {
				if(adj[cur][i]==1&& !v[i]) {
					v[i] = true;
					que.offer(i);
					up++;
				}
			}
		}
	}
	
	private static void Rbfs(int num, boolean[] v) {
		// 큐생성
		Queue<Integer> que = new LinkedList<Integer>();
		// 삽입
		que.offer(num);
		// 방문처리
		v[num] = true;
		
		
		//반복문
		int cur;
		while(!que.isEmpty()) {
			// 큐에서 꺼냄
			cur = que.poll();
			// 방문처리가 안되어있으면. 
			for (int i = 1; i <= N; i++) {
				if(adj[i][cur]==1&& !v[i]) {
					v[i] = true;
					que.offer(i);
					down++;
				}
			}
		}
	}

}
