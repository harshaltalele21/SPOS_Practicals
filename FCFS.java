import java.util.Scanner;

public class FCFS 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int i,j,temp;
        float avgta=0,avgwt=0;
        System.out.println("Enter number of process : ");
        int n;
        n=sc.nextInt();
        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] wt = new int[n];
        int[] ta = new int[n];
    
        for(i=0;i<n;i++)
        {
            pid[i]=i+1;
            System.out.println("Process : "+pid[i]);
            System.out.println("Arrival time : ");
            at[i]=sc.nextInt();
            System.out.println("Burst time : ");
            bt[i]=sc.nextInt();
        }
        
        //Sorting process
        for(i=0;i<n-1;i++)
        {
            for(j=0;j<n-i-1;j++)
            {
                if(at[j]>at[j+1])
                {
                    temp=at[j];
                    at[j]=at[j+1];
                    at[j+1]=temp;
                    
                    temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;
                    
                    temp=pid[j];
                    pid[j]=pid[j+1];
                    pid[j+1]=temp;
                }
            }
        }
        for(i=0;i<n;i++)
        {
            if(i==0)
            {
                ct[i]=at[i]+bt[i];
            }
            else
            {
                if(at[i]>ct[i-1])
                {
                    ct[i]=at[i]+bt[i];
                }
                else
                {
                    ct[i]=ct[i-1]+bt[i];
                }
            }
            ta[i]=ct[i]-at[i];
            wt[i]=ta[i]-bt[i];
            
            avgwt+=wt[i];
            avgta+=ta[i];
        }
        
        avgwt/=n;
        avgta/=n;
        System.out.println("Pid\tAT\tBT\tCT\tWT\tTA");
        for(i=0;i<n;i++)
        {
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+wt[i]+"\t"
                    +ta[i]);
        }
        System.out.println("Average waiting time = "+avgwt);
        System.out.println("Average turn around time = "+avgta);
    }
}