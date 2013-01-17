import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */
public class Printer {
//    int brk[];
//    int newm[];;
    Writer output=null;
   public static void main(String args[])throws FileNotFoundException, IOException{       
      String str;  
      int L=78,i=0,sumwl=0,x=0;
      int n=1000;
      int M[]=new int[n];
      int E[]=new int[1000];
      int brk[]=new int[1000];
      int newm[]=new int[1000];
      String part1[][][]=new String[n][L][L];
     // String part2[][][]=new String[n][n][n];
      String w[]=new String[n];
      Scanner in=new Scanner(new File("file.txt"));
      
       // boolean hasNext = in.hasNext();
      i=0;
        while(in.hasNext()){
            
           w[i]=in.next();            
           System.out.println("hi"+w[i]);
        i++;
        }
        for( i=0;i<n;i++){
             for(int j=1;j<2;j++){
                sumwl+=w[j].length();
              System.out.println(" hi"+sumwl);
                if(sumwl>L){
                   x=sumwl-w[j].length();
                   sumwl=w[j].length();
                   E[i]+=(L-x)*(L-x);
                            }
                   }
           E[i]+=(L-sumwl)*(L-sumwl);
        }
   for(i=0;i<n;i++){
       int min=999999999;
         int e1=0, e2=0,k1,k2,l,m;
        for(int j=0;j<i;j++){
              M[j]=E[j-i+1]+M[i-1];
                if(M[j]<min){
                 min=M[j];
                 k1=j-i+1;
                 brk[e1]=k1;
                 k2=i-1;
                 newm[e2]=k2;
                 e1++;
                 e2++;
                   }      
                                        
             }
        for(l=0;l<n;l++){
             for(m=newm[l]+1;m<=brk[l]+newm[l];m++){
               part1[i][l][m]=w[m];
               //part2[i][l][m]=w[m]; 
             }
        
     } 
        
     
   }
   for(int l=0;l<n;l++){
             for(int m=newm[l]+1;m<=brk[l]+newm[l];m++){
          System.out.println(part1[n-1][l][m]+" ")     ;
               
             }
        
     } 
              
             
          
           
      
    


      
      
//     public void printm(int n,int brk[],int newm[],int E[],int En,String w[]) throws IOException{
//         int i1,i2;
//         
//                     for(int e=1;e<n;e++){
//           String text=w[e]+" ";
//           File file = new File("write.txt");
//           output = new BufferedWriter(new FileWriter(file));
//           output.write(text);
//                  
//        
//                        }
//            
//        }
//    
   in.close();
   }

}
