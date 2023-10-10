import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		String p = br.readLine();
        
		KMP(t,p);
	}
	static void KMP(String parent, String pattern){
		int[] table = makeTable(pattern);
		
		int n1 = parent.length(), n2 = pattern.length();
		int idx=0;
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n1; i++) {
			while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(idx)) {
				if(idx == n2-1) {
					sb.append((i-idx+1)+" ");
					cnt++;
					idx = table[idx];
				}else {
					idx +=1;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
		
	}
	
	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		
		int idx = 0;
		for(int i=1; i<n; i++) {
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
}