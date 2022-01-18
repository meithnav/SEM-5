
import java.util.*;
import java.util.Arrays;
import java.lang.Integer;
import java.lang.Math;


public class PageRank{

    public static int MAX_NODES = 5;
    public static int[][] incoming = new int[MAX_NODES][MAX_NODES] ;
    public static int[][] outgoing = new int[MAX_NODES][MAX_NODES] ;
    public static float[] AuthScore = new float[MAX_NODES] ;
    public static float[] HubScore = new float[MAX_NODES] ;
    public static float[] New_AuthScore = new float[MAX_NODES] ;
    public static float[] New_HubScore = new float[MAX_NODES] ;


    
    public static float calcAuthScore(int curNode , int nodes){
        float val=0.0f; 
        
        for(int i=0; i<nodes;i++){

            if(incoming[curNode][i]==1){
                val += HubScore[i] ;
            }

        }

        return val;

    }

    public static float calcHubScore(int curNode , int nodes){
        float val=0.0f; 
        
        for(int i=0; i<nodes;i++){

            if(outgoing[curNode][i]==1){
                val += AuthScore[i] ;
            }

        }

        return val;

    }

    public static void calcHitsRank(int nodes){
        int MAX_ITER = 10;
        int total_auth=0, total_hub=0;
       
        for(int i=0; i<MAX_ITER; i++){

            // ALL NODES
            for(int n=0; n<nodes; n++){
                New_AuthScore[n] = calcAuthScore(n, nodes);
                total_auth += New_AuthScore[n];
            }
            for(int n=0; n<nodes; n++){
                New_HubScore[n] = calcHubScore(n, nodes);
                total_hub =+ New_HubScore[n];
            }

            // NORMALISE
            for(int n=0; n<nodes; n++){
                HubScore[n] = (float)(New_HubScore[n])/(total_hub) ;
                AuthScore[n] = (float)(New_AuthScore[n])/(total_auth) ;
            }

            // RE-INITIALISE 
            total_auth=0;
            total_hub=0;

            // System.out.print("\nITER "+i+" : "+Arrays.toString(PR)) ;
        }



        // PRINT RESULTS
       System.out.print("\n\n ******* HITS RANK ******\n") ;
       System.out.print("\n NODE\tAUTH SCORE\tHUB SCORE") ;

       for(int i =0; i<nodes;i++){
            System.out.print("\n "+(char)(i+65)+"\t"+ AuthScore[i]+"\t"+ HubScore[i]) ;

       }

    }
    public static void initialise(int nodes){

        for(int j=0; j<nodes; j++){
            AuthScore[i] =1.0;
            HubScore[i] =1.0;

        }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in) ;

        System.out.print("\n ENTER NUMBER OF NODES :");
        int nodes = sc.nextInt();

        // int nq=0; 
        System.out.print("\n ENTER INFLOW MATRIX :\n");
        for(int i=0; i<nodes;i++){
            for(int j=0; j<nodes; j++){
                incoming[i][j] = sc.nextInt();

                // if(outgoing[i][j] ==1){
                //     nq++; 
                // }
            }

            // AuthScore[i]= nq;
            // nq=0;
        }
        
        System.out.print("\n ENTER OUTFLOW MATRIX :\n");
        for(int i=0; i<nodes;i++){
            for(int j=0; j<nodes; j++){
                outgoing[i][j] = sc.nextInt();

            }
        }
       
        initialise(nodes) ;
        calcHitsRank(nodes);

        sc.close();
    }
}

/*
8
0 0 1 0 0 0 1 1
0 0 0 1 1 0 0 0
0 1 0 1 1 1 1 0 
1 0 0 0 1 0 0 0 
0 1 0 0 0 0 0 0 
0 0 0 0 1 0 0 0 
0 0 0 0 0 0 0 0 
0 0 0 0 0 1 0 0 

0 0 0 1 0 0 0 0 
0 0 1 0 1 0 0 0
1 0 0 0 0 0 0 0 
0 1 1 0 0 0 0 0 
0 1 1 1 0 1 0 0
0 0 1 0 0 0 0 1
1 0 1 0 0 0 0 0
1 0 0 0 0 0 0 0  


*/ 