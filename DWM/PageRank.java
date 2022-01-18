
import java.util.*;
import java.util.Arrays;
import java.lang.Integer;
import java.lang.Math;


public class PageRank{

    public static int MAX_NODES = 5;
    public static int[][] incoming = new int[MAX_NODES][MAX_NODES] ;
    public static int[][] outgoing = new int[MAX_NODES][MAX_NODES] ;
    public static float[] PR = new float[MAX_NODES] ;

    
    public static float calcPR(int curNode , int nodes, int[] Nq){
        float val=0.0f; 
        
        for(int i=0; i<nodes;i++){

            if(incoming[curNode][i]==1){
                val += (float)(PR[i])/(Nq[i]) ;
            }

        }

        return val;

    }

    public static void calcPageRank(int nodes, int[] Nq ){
        float d = 0.85f;
        int MAX_ITER = 10;

        for(int i=0; i<MAX_ITER; i++){

            // ALL NODES
            for(int n=0; n<nodes; n++){
                PR[n] = (1-d) + d*(calcPR(n, nodes, Nq));
            }


            // System.out.print("\nITER "+i+" : "+Arrays.toString(PR)) ;
        }

       System.out.print("\n\n ******* PAGE RANK ******\n") ;
       for(int i =0; i<nodes;i++){
            System.out.print("\n"+(char)(i+65)+" -> "+ PR[i]) ;

       }

    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in) ;

        System.out.print("\n ENTER NUMBER OF NODES :");
        int nodes = sc.nextInt();

        // System.out.print("\n ENTER Dampening (d) :");
        // float d = sc.nextInt();

        System.out.print("\n ENTER INFLOW MATRIX :\n");
        for(int i=0; i<nodes;i++){
            for(int j=0; j<nodes; j++){
                incoming[i][j] = sc.nextInt();
              
            }
        }
        int nq=0; 
        int[] Nq= new int[nodes];
        System.out.print("\n ENTER OUTFLOW MATRIX :\n");
        for(int i=0; i<nodes;i++){
            for(int j=0; j<nodes; j++){
                outgoing[i][j] = sc.nextInt();

                if(outgoing[i][j] ==1){
                    nq++; 
                }
            }

            Nq[i]= nq;
            nq=0;
        }

        calcPageRank(nodes ,Nq);

        sc.close();
    }
}

/*
3
0 0 1
1 0 0 
1 1 0 

0 1 1 
0 0 1
1 0 0

****

4

0 0 1 0 
1 0 0 0 
1 1 0 1 
0 0 0 0 

0 1 1 0
0 0 1 0
1 0 0 0 
0 0 1 0

*/ 