
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */
public class minCost {
    public static void main(String args[])throws IOException {
        int i,tc,n,C;
        
        
     Scanner input=new Scanner(System.in);   
      System.out.println("Enter the no of test case");
          tc=input.nextInt();
     System.out.println("Enter the no station");
          n=input.nextInt();     
       int c1[]=new int[n];
       int c2[]=new int[n];
       int Cost[]=new int[n];
          Cost[0]=0;
  for(i=2;i<=n;i++){
      System.out.println("Enter the cost of path 1 and     path 2");
       c1[i]=input.nextInt();
       c2[i]=input.nextInt();
       System.out.println(c1[i]+"  "+c2[i]);
  }     
  for(i=2;i<=n;i++){
       if(c1[i]<c2[i])
     Cost[i]=Cost[i-1]+c1[i];
    else
           Cost[i]=Cost[i-1]+c2[i];
  }
    C=Cost[n];
    System.out.println("Least Cost"+C);
      }
    
    }

