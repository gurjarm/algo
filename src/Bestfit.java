
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjar
 */
public class Bestfit {
    int n=100;
    Random rand=new Random();
    int X[]=new int[n];
    int Y[]=new int[n];
    double Error[][]=new double[n][n];
    double a[][]=new double [n][n];
    double b[][]=new double [n][n];
    double SigmaX[]=new double[n];
    double SigmaY[]=new double[n];
    double SigmaXY[]=new double[n];
    double SigmaXsq[]=new double[n];
    
    public static void main(String args[]){
    }
    public void SegLS(){
        int i,j,k;
         for(i=0;i<n;i++){
           X[i]=rand.nextInt(100);
         
            }
         for(i=0;i<n;i++){
           Y[i]=rand.nextInt(100);
         
            }
         for(i=0;i<n;i++){
           SigmaX[i]=0;
           SigmaY[i]=0;
           SigmaXY[i]=0;
           SigmaXsq[i]=0;
            }
         for(i=0;i<n;i++){
           SigmaX[i]=SigmaX[i]+X[i];
           SigmaY[i]=SigmaY[i]+Y[i];
           SigmaXY[i]=SigmaXY[i]+X[i]*Y[i];
           SigmaXsq[i]=SigmaXsq[i]+X[i]*X[i];;
           }
         for(j=0;j<n;j++){
             for(i=0;i<=j;i++){
                 for(k=i;k<=j;k++){
                a[i][j]=(((n*SigmaXY[k])-(SigmaX[k])*(SigmaY[k]))/((n*SigmaXsq[k])-(SigmaX[k]*SigmaX[k])));
                        }
                 
                 }
             
            }  
         }
    }

