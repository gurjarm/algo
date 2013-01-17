
import java.util.Arrays;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */
public class BestFitl {
    public static void main(String args[]){
    
    
    
     int n=100,p=1;
     int X[]=new int[n];
     int Y[]=new int[n];
     double min;
     double C=1000000;
     double E[][]=new double[n][n];
     double M[]=new double[n];         
     double SigmaX;
     double SigmaY;
     double SigmaXY;
     double SigmaXsq;
     Random rand=new Random();
          
     int i,j,k,t;
     double a,b;
         for(i=0;i<n;i++){
           X[i]=rand.nextInt(200)-100;
           Y[i]=rand.nextInt(200)-100;
            }
         Arrays.sort(X);
                 
         for(j=0;j<n;j++){
            for(i=0;i<=j;i++){ 
                  SigmaX=0;
                     SigmaY=0;
                     SigmaXY=0;
                     SigmaXsq=0;
                     E[i][j]=0;
                for(k=i;k<=j;k++){
                    SigmaX+=X[k];
                    SigmaY+=Y[k];
                    SigmaXY+=(X[k]*Y[k]);
                    SigmaXsq+=(X[k]*X[k]);
                              }
                     a=((((j-i+1)*SigmaXY)-(SigmaX*SigmaY))/(((j-i+1)*SigmaXsq)-((SigmaX)*(SigmaX)))); 
                     b=(SigmaY-(a*SigmaX)/(j-i+1)); 
                for(k=i;k<=j;k++){
                       E[i][j]+=((Y[k]-a*X[k]-b)*(Y[k]-a*X[k]-b));
                
                                 }     
                            }
                       }
         M[0]=0;
         for(j=1;j<n;j++){
             
             //min=99999999;
             M[j]=9999999;
             for(i=1;i<=j;i++){
                    
                   // min=E[i][j]+C+M[i-1];
                  if(M[j]>E[i-1][j-1]+C+M[i-1]){
                      M[j]=E[i-1][j-1]+C+M[i-1];            
                  }
                   
                
                   
                            }
            
                   }

         for(j=2;j<n-1;j++){
             if(M[j+1]<M[j])
                 p++ ;
         
         }
         System.out.println("Total partitions"+p);
         
      }
  
     
     


}     

