package sposlab;

import java.util.*;
//import java.

public class lru {
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of frames");
		int frames = sc.nextInt();
		System.out.println("Enter the length of reference string");
		int ref_len = sc.nextInt();
		System.out.println("Enter the reference string elements one by one");
		int[] ref_str = new int[ref_len];
		ArrayList<Integer> stack = new ArrayList<Integer>();
		for(int j=0;j<ref_len;j++) {
			ref_str[j] = sc.nextInt();
//			stack.add(ref_str[j]);
		}
		
		int[] buffer = new int[frames];
		for(int j=0;j<frames;j++) {
			buffer[j] = 0;
		}

		
		int hit=0, fault=0,flag=0,pointer=0,temp=0,min=9999;
		int mem_layout[][] = new int[frames][ref_len];
		Boolean isFull = false;
		
		for(int i=0;i<ref_len;i++) {
			flag=-1;
			if(stack.contains(ref_str[i])) {
				stack.remove(stack.indexOf(ref_str[i]));
			}
			stack.add(ref_str[i]);
			
			for(int j=0;j<frames;j++) {
				if(ref_str[i]==buffer[j]) {
					flag = j;
					hit++;
					break;
				}
			}
			
			if(flag==-1) {
				
				if(isFull) {
					min = ref_len;
					for(int j=0; j<frames;j++) {
						if(stack.contains(buffer[j])) {
							temp = stack.indexOf(buffer[j]);
							if(temp < min) {
								pointer = j;
								min = temp;
							}
						}
					}
				}
				
				fault++;
				buffer[pointer++]=ref_str[i];
				if(pointer==frames) {
					pointer = 0;
					isFull = true;
				}
			}
			
			for(int k=0;k<frames;k++) {
				mem_layout[k][i] = buffer[k];
			}
			
		}
		
		for(int i=0;i<frames;i++) {
			System.out.println();
			for(int j=0; j<ref_len;j++)
				System.out.print(mem_layout[i][j]+"  ");
		}
		
		System.out.println("Hits: "+hit);
		System.out.println("Faults: "+fault);
		sc.close();
		
	}

}
