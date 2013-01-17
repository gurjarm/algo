
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */
public class sort {
    public static void main(String args[]){
        int i,j,t,n=7;
       int A[]={2,6,4,0,7,9,1};
        
         for(i=1;i<=n;i++){
           for(j=i;j<n;j++){
                if(A[j]<A[j-1])
                { t=A[j];
                  A[j]=A[j-1];
                  A[j-1]=t;
                }
               
               
               
                      }
           }
         System.out.println("Sorted 1array");
         for(i=0;i<n;i++){
            System.out.print(" "+A[i]);
         
         }
         Arrays.sort(A);
          System.out.println("Sorted 2array");
         for(i=0;i<n;i++){
            System.out.print(" "+A[i]);
         
         }
         
    
    }
}
