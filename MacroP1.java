import java.util.*;
import java.io.*;

public class MacroP1 {

	public static void main(String args[]) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader("macro_input.asm"));

		FileWriter mnt = new FileWriter("mnt.txt");
		FileWriter mdt = new FileWriter("mdt.txt");
		FileWriter kpdt = new FileWriter("kpdt.txt");
		FileWriter pnt = new FileWriter("pnt.txt");
		FileWriter ir = new FileWriter("ir.txt");

		LinkedHashMap<String,Integer> pntab = new LinkedHashMap<>();
		String line;
		String Macroname=null;
		int mdtp=0,kpdtp=0,pp=0,flag=0,paramNo=0,kp=0;

		while((line = br.readLine())!=null) {

			String parts[] = line.split("\\s+");

			if(parts[0].equalsIgnoreCase("MACRO")) {
				
				flag = 1;
				line = br.readLine();
				parts=line.split("\\s+");
				Macroname = parts[0];

				if(parts.length<=1) {
					mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+(kp==0?kpdtp:kpdtp+1)+"\n");
					continue;
				}

				for(int i=1;i<parts.length;i++) {                   //i from 1 not 0
					// parts[i].replaceAll("[&,]","");
					parts[i]=parts[i].replaceAll("[&,]","");

					if(parts[i].contains("=")) {
						++kp;
						String keywordParam[] = parts[i].split("=");
						pntab.put(keywordParam[0],paramNo++);

						if(keywordParam.length==2) {
							kpdt.write(keywordParam[0]+"\t"+keywordParam[1]+"\n");
						}
						else {
							kpdt.write(keywordParam[0]+"\t-"+"\n");
						}
					}
					else {
						pntab.put(parts[i],paramNo++);
						pp++;
					}

					mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+(kp==0?kpdtp:(kpdtp+1))+"\n");
					kpdtp=kpdtp+kp;
				}

			}
			else if (parts[0].equalsIgnoreCase("MEND")) {
				
				flag=kp=pp=0;
				mdt.write(line+"\n");
				mdtp++;
				paramNo=1;

				pnt.write(Macroname+":\t");
				Iterator<String> it = pntab.keySet().iterator();
				while(it.hasNext()) {
					pnt.write(it.next()+"\t");
					// System.out.println(it.nexts)
				} 
				pnt.write("\n");
				pntab.clear();
			}

			else if (flag==1) {
				
				for(int i=0;i<parts.length;i++) {

					if(parts[i].contains("&")) {

						// parts[i]=parts[i].replaceAll("[&,]","");
					parts[i]=parts[i].replaceAll("[&,]","");

						mdt.write("(P,"+pntab.get(parts[i])+")"+"\t");
					}
					else {
						mdt.write(parts[i]+"\t");
					}
				}
				mdt.write("\n");
				mdtp++;
			}

			else {
				ir.write(line+"\n");
			}

		}

			br.close();
			mdt.close();
			mnt.close();
			ir.close();
			pnt.close();
			kpdt.close();
			System.out.println("MAcro PAss1 Processing done. :)");
	}
}