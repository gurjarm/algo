/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */

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
public class NewClass2 {
    public static void main(String args[]){
    
    
    
     int n=100,p=1,k1=0;
     int k2=0;
     int X[]=new int[n];
     int Y[]=new int[n];
     int Xcor[]=new int[n];
     int Ycor[]=new int[n];
     int point[]=new int[n];
     int P[]=new int[n];
      P[0]=0;
     double min;
     double C=100000;
     double E[][]=new double[n][n];
     int printX[][]=new int[n][n];
     int printY[][]=new int[n][n];
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
             p=0;
             //min=99999999;
             M[j]=9999999;
             int e=0;
             for(i=1;i<=j;i++){
                    
                   // min=E[i][j]+C+M[i-1];
                  if(M[j]>E[i][j]+C+M[i-1]){
                      M[j]=E[i][j]+C+M[i-1];  
                      //p++;
                      k1=i;
                     point[e] = k1;
                      e++;
                             }
                                  
                       P[j]=P[k1]+1;
                                             
                  
                            }

             for(k2=1;k2<n;k2++){
               printX[j][k2]=X[point[k2]];
               printY[j][k2]=Y[point[k2]]; 
             }
            printX[j][k1]=X[3];
               printY[j][k1]=Y[3] ;
                   }
                           
         System.out.println("Total no of partitions"+P[99]);
               for(int k3=1;k3<=P[99];k3++){
               System.out.println("partion will start from  ("+printX[99][k3]+","+printY[99][k3]+")"+" end to  ("+printX[99][k3+1]+","+printY[99][k3]+")");
               }

                        
      }
  
     
     


}     

