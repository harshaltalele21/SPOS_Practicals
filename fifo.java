package sposlab;
import java.util.*;

public class fifo {
	//public static int i;
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of frames");
		int frames = sc.nextInt();
		System.out.println("Enter the length of reference string");
		int ref_len = sc.nextInt();
		System.out.println("Enter the reference string elements one by one");
		int[] ref_str = new int[ref_len];
		
		for(int j=0;j<ref_len;j++) {
			ref_str[j] = sc.nextInt();
		}
		
		int[] buffer = new int[frames];
		for(int j=0;j<frames;j++) {
			buffer[j] = -1;
		}
	
		
		int hit=0, fault=0,flag=0,pointer=0;
		int mem_layout[][] = new int[frames][ref_len];
		
		for(int i=0;i<ref_len;i++) {
			flag=-1;
			for(int j=0;j<frames;j++) {
				
				if(ref_str[i]==buffer[j]) {
					flag = j;
					hit++;
					break;
				}
			}
			if(flag==-1) {
				fault++;
				buffer[pointer++]=ref_str[i];
				if(pointer==frames) {
					pointer = 0;
				}
			}
			
			for(int k=0;k<frames;k++) {
				mem_layout[k][i] = buffer[k];
			}
		}
		
//		System.out.println(mem_layout);
		for(int i=0;i<frames;i++) {
			System.out.println();
			for(int j=0; j<ref_len;j++)
				System.out.print(mem_layout[i][j]+"  ");
		}
		sc.close();
	}
}
