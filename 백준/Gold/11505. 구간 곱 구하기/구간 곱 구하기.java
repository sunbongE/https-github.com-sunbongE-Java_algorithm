import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 1_000_000_007;
	static int N,M,K, arr[];
	static long tree[];
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		// 트리의 크기 구하기.
		int k = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[(int)Math.pow(2, k+1)];
		
		for (int i = 1; i <= N; i++) {	// 1~N까지 0은 미사용.
			arr[i] = Integer.parseInt(bf.readLine());
		}
		init(1,1,N);
		
		for (int i = 0; i < (M+K); i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) {				// update
				arr[b]=c;			// 원본배열 변경
				update(1,1,N,b,c);	// 변경
			}else {				// pMul
				sb.append(pMul(1,1,N,b,c)%MOD+"\n");
			}
			
		}
		System.out.println(sb.toString());
	}
	
	
	private static long pMul(int node, int start, int end, int left, int right) {
		
		// 범위 외.
		if(left>end||start>right) {
			return 1;
		}
		
		// 범위내 완전히 포함하면 더 탐색하지않고 리턴한다.
		if(left<=start&&end<=right) {
			return tree[node];
		};
		
		int mid = (start+end)/2;
		return (pMul(node*2,start,mid,left,right)*pMul(node*2+1,mid+1,end,left,right))%MOD;
		
	}


	private static long update(int node, int start, int end, int idx, int val) {
		
		// 범위를 벗어남
//		if(idx>end||idx<start) {
//			return tree[node];
//		}
		if(end < idx || idx< start) return tree[node];
		// 두 포인터가 같음
		if(start==end) {
			return tree[node] = val;
		}
		// 나머지는 자식을 탐색하며 찾아옴
		int mid = (start+end)/2;
		return tree[node] = (update(node*2,start,mid,idx,val)*update(node*2+1,mid+1,end,idx,val))%MOD;
	}


	// segmentTree setting
	private static long init(int node, int start, int end) {
		// start==end가 같으면 리프노드 할당후 리턴
		if(start==end) {
			return tree[node]=arr[start];
		}

		//그 외, 자식노드로 이동하면서 현재 노드 갱신.
		int mid = (start+end)/2;
		return tree[node]=(init(node*2,start,mid)*init(node*2+1,mid+1,end))%MOD;
	}
}
