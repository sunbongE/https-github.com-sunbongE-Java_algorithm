
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int map[][];
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		int cnt=0;
		char[] inp ;
		for (int i = 0; i < 9; i++) {
			inp = bf.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = Character.getNumericValue(inp[j]);
				if(map[i][j]==0) cnt++;
			}
		}
		
		dfs(cnt);
		
	}
	private static void dfs(int cnt) {
		if(cnt==0) {
			show();
//			System.out.println(10);
			flag=true;
			return;
		}
		loop:
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j]==0) {
					// 1~9까지
					for (int num = 1; num <= 9; num++) {
						// 들어갈 수 있는 수인지 검증 
						if(valid(i,j,num)) {
						map[i][j]=num; 	// 들어갈 수 있는 수를 넣은 것
						dfs(cnt-1);
						if(flag) break loop;
						map[i][j]=0;	// 다녀와서 되돌리기
						};
					}
					return;
				}
			}
		}
	}
	private static void show() {
//		System.out.println("====================================================");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	private static boolean valid(int i, int j, int num) {
		// 수평에 같은 수가 있는지확인
		for (int x = i; x < i+1; x++) {
			for (int y = 0; y < 9; y++) {
				if(!(x==i&&y==j)&&map[x][y]==num)return false;
			}
		}
		// 수직으로 같은 수가 있는지 확인
			for (int x = j; x < j+1; x++) {
				for (int y = 0; y < 9; y++) {
				if(!(x==i&&y==j)&&map[y][x]==num)return false;
			}
		}
		
		// 3x3에 같은 수가 있는지 확인. 출발점 : (i/3)*3 , 끝점 : 출발점 +3
		int xStart,xEnd,yStart,yEnd;
		xStart = (i/3)*3;
		xEnd = xStart+3;
		
		yStart = (j/3)*3;
		yEnd = yStart+3;
		
		for (int x = xStart; x < xEnd; x++) {
			for (int y = yStart; y <yEnd ; y++) {
				if(!(x==i&&y==j)&&map[x][y]==num) {
					return false;
				}
			}
		}
		
		return true;
	}

}
