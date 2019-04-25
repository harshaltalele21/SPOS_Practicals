import java.util.Scanner;

public class round_robin {

    public static void main(String args[]) {
        
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter number of processes: ");
        n = sc.nextInt();
        int q,flag=0;
        float avgt=0,avgw=0;
        System.out.println("Enter quantum time: ");
        q = sc.nextInt();
        int[] pid = new int[n];
        int[] bt = new int[n];
        int[] ta = new int[n];
        int[] wt = new int[n];
        int[] rem = new int[n];
        
        for (int i=0;i<n;i++) {
        
            System.out.println("Enter burst time for process: "+(i+1));
            bt[i]=sc.nextInt();
            rem[i]=bt[i];
        }
        
        do{
            flag=0;
            for(int i=0; i<n; i++){
                if(rem[i]>=q){
                    System.out.println("P"+(i+1));
                    for(int j=0;j<n;j++){
                        if(j==i){
                            rem[i]-=q;
                        }
                        else if(rem[j]>0){
                            wt[j]+=q;
                        }
                    }
                }
                else if(rem[i]>0){
                    System.out.println("P"+(i+1));
                    int rem1 = rem[i];
                    for(int j=0;j<n;j++){
                        if(j==i){
                            rem[i]=0;
                        }
                        else if(rem[j]>0){
                            wt[j]+=rem1;
                        }
                    }
                }
            }
            for(int i=0;i<n;i++){
                if(rem[i]>0){
                    flag=1;
                    break;
                }
            }
        }while(flag==1);
        
        
        System.out.println("Pid\tBT\tWT\tTA");
        for(int i=0;i<n;i++)
        {
            ta[i]=bt[i]+wt[i];
            avgt+=ta[i];
            avgw+=wt[i];
            System.out.println((i+1)+"\t"+bt[i]+"\t"+wt[i]+"\t"
                    +ta[i]);
        }
        
        System.out.println("Average waiting time: "+ (avgw/n));
        System.out.println("Average turn around time: "+ (avgt/n));
    }
}