import java.util.Scanner;

public class bankers {

   

    public static void display_matrix(int matrix[][],int proc_n, int res_n){
        for(int i=0;i<proc_n;i++){
            for(int j=0;j<res_n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
   
   
    public static boolean check(int max[][],int need[][],int alloc[][],int curr_avail[],int proc_n,int res_n){
        boolean safe=true;
        boolean marked[] = new boolean[proc_n];
        int safe_pos=0;
        int[] safe_seq = new int[proc_n];
        while(safe_pos<proc_n && safe){
            for(int i=0;i<proc_n;i++){
                int c=0;
                for(int j=0;j<res_n;j++){
                    if(need[i][j]<curr_avail[j]){
                        c++;
                    }
                }
                if((c==res_n) && (marked[i]==false)){
                    for(int j=0;j<res_n;j++){
                        curr_avail[j]+=alloc[i][j];
                    }
                    marked[i]=true;
                    safe_seq[safe_pos]=i;
                    safe_pos++;
                    break;
                }
                if((c<res_n) && (i==proc_n-1)){
                    safe=false;
                }
            }
        }
       
        if(safe){
            for(int i=0;i<proc_n;i++){
                System.out.print(safe_seq[i]+" ");
            }
        }
       
       
        return safe;
       
    }
   
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int res_n;
        System.out.println("Enter total number of resources: ");
        Scanner sc = new  Scanner (System.in);
        res_n=sc.nextInt();
        int[] res = new int[res_n];
        int[] curr_avail = new int[res_n];
        System.out.println("Enter number of instances for each resouce: ");
        for(int i=0;i<res_n;i++){
            System.out.println("Instances for resource: "+(i+1));
            res[i]=sc.nextInt();
            curr_avail[i]=res[i];
        }
        int proc_n;
        System.out.println("Enter total number of processes: ");
        proc_n=sc.nextInt();
       
        int[][] max = new int[proc_n][res_n];
        int[][] alloc = new int[proc_n][res_n];
        int[][] need = new int[proc_n][res_n];
       
       
        for(int i=0;i<proc_n;i++){
            for(int j=0;j<res_n;j++){
                System.out.println("Enter max requirement for process:"+(i+1)+" "+(j+1));
                max[i][j]=sc.nextInt();
            }
        }
       
        for(int i=0;i<proc_n;i++){
            for(int j=0;j<res_n;j++){
                System.out.println("Enter allocation for process:"+(i+1)+" "+(j+1));
                alloc[i][j]=sc.nextInt();
                curr_avail[j]-=alloc[i][j];
            }
        }
       
        System.out.println("Calculating Need matrix: ");
       
        for(int i=0;i<proc_n;i++){
            for(int j=0;j<res_n;j++){
                need[i][j]=max[i][j]-alloc[i][j];
            }
        }
       
        System.out.println("Max matrix: ");
        display_matrix(max, proc_n, res_n);
       
        System.out.println("Alloc matrix: ");
        display_matrix(alloc, proc_n, res_n);
       
        System.out.println("Need matrix: ");
        display_matrix(need, proc_n, res_n);
       
   
        if(check(max,need,alloc,curr_avail,proc_n,res_n)){
            System.out.println("The system is in safe state");
            int pno;
            System.out.println("Enter process number needing more resource: ");
            pno=sc.nextInt();
            int[] req = new int[res_n];
            System.out.println("Enter resource needed");
            for(int i=0;i<res_n;i++){
                req[i]=sc.nextInt();
            }
            int need_cnt=0,curr_cnt=0;
            for(int i=0;i<res_n;i++){
                if(req[i] < need[pno][i])
                    need_cnt++;
                if(req[i] < curr_avail[i])
                    curr_cnt++;
            }
            if(need_cnt==res_n && curr_cnt==res_n){
                for(int i=0;i<res_n;i++){
                    alloc[pno][i]+=req[i];
                    curr_avail[i]-=req[i];
                    need[pno][i]-=req[i];
                }
                if(check(max, need, alloc, curr_avail, proc_n, res_n)){
                    System.out.println("The system is in safe state");
                }
            }
            else{
                System.out.println("The request cannot be granted");
            }
        }
        else
            System.out.println("The system is in unsafe state");
        
        sc.close();
    }

}