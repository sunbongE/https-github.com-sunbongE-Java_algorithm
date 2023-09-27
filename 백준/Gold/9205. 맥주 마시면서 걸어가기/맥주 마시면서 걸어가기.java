import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 박태호
 * 
 *         <pre>
 *  
 *  
 *  풀이
 *  시작부터 갈수있는 거리를 1000으로 설정한다.
 *  패스티벌 위치가 내가 갈 수 있는 거리보다 작거나 같은 경우에 happy리턴한다. 
 *  
 *  다음 편의점 위치가 1000미터 이내에 있으면 위치 내 위치 이동시키고 맥주병을 채우는데 최대 : 20병
 *  
 *  1. 갈수있는 거리 계산
 *  2. 다음위치와 내 위치까지 거리차이가 갈수있는거리안에있으면 가고 아니면 SAD리턴
 *  3. 다음 위치가 패스티벌위치이고 갈 수 있으면 끝낸다.
 * 
 *         </pre>
 */

public class Main {
	static class sang {
		int x;
		int y;

		public sang(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int T, n, curDist;
	static boolean v[]; // 패스티벌 방문체크
	static int[][] stores;
	static int[] endPoint;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		int x,y;
		for (int tc = 0; tc < T; tc++) {
			curDist = 1000;
			n = Integer.parseInt(bf.readLine());
			
			st = new StringTokenizer(bf.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			sang cur= new sang(x,y);// 현재 친구들 위치..
			v=new boolean[n];
			stores = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				stores[i][0] = Integer.parseInt(st.nextToken());
				stores[i][1] = Integer.parseInt(st.nextToken());
//				System.out.println(Arrays.toString(stores[i]));
			}
			// 패스티벌 위치 기록.
			endPoint = new int[2];
			st = new StringTokenizer(bf.readLine());
			endPoint[0] = Integer.parseInt(st.nextToken());
			endPoint[1] = Integer.parseInt(st.nextToken());
//			System.out.println(cur.x+" "+cur.y);
			v = new boolean[n];
			bfs(cur.x, cur.y);
		}
		System.out.println(sb);

	}
	private static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {x,y});
		
		int curXY[],cx,cy;
		while(!que.isEmpty()) {
			curXY = que.poll();
			cx = curXY[0];
			cy = curXY[1];
//			System.out.println(cx+" "+cy);
			// 현재 위치에서 패스티벌까지 맥주마시면서 갈수있으면 
			if(1000>=(Math.abs(cx-endPoint[0])+Math.abs(cy-endPoint[1]))){
				sb.append("happy\n");
				return;
			}
			// 편의점 위치를 순회하면서 갈 수 있으면 가고 다음 편의점까지 못가는 상황이면 바로 끝내기.
			for (int i = 0; i < n; i++) {
				if(!v[i]&&1000>=(Math.abs(cx-stores[i][0])+Math.abs(cy-stores[i][1]))) {
//					System.out.println(stores[i][0]+" "+stores[i][1]);
					v[i] = true;
					que.offer(new int[] {stores[i][0],stores[i][1]});
				}
			}
			
			
		}
		sb.append("sad\n");
		
	}

}
