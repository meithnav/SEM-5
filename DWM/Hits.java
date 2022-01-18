
import java.util.*;
import java.util.Arrays;
import java.lang.Integer;
import java.lang.Math;


public class Hits{

    public static int MAX_NODES = 10;
    public static int[][] incoming = new int[MAX_NODES][MAX_NODES] ;
    public static int[][] outgoing = new int[MAX_NODES][MAX_NODES] ;
    public static float[][] overAllHubScore = new float[MAX_NODES][MAX_NODES] ;
    public static float[][] overAllAuthScore = new float[MAX_NODES][MAX_NODES] ;
    public static float[] AuthScore = new float[MAX_NODES] ;
    public static float[] HubScore = new float[MAX_NODES] ;
    public static float[] New_AuthScore = new float[MAX_NODES] ;
    public static float[] New_HubScore = new float[MAX_NODES] ;


    
    
    public static void displayOutput(int nodes){

        // PRINT RESULTS
       System.out.print("\n\n ******* HITS RANK ******\n") ;
       System.out.print("\n NODE\t\tAUTH SCORE\t\t\tHUB SCORE") ;
       System.out.print("\n \tITER 2\tITER 4\tITER 6\t\tITER 2\tITER 4\tITER 6") ;


       for(int i =0; i<nodes;i++){
            System.out.printf("\n %s \t%.3f \t%.3f\t%.3f \t\t%.3f \t%.3f \t%.3f" , 
                (char)(i+65) ,overAllAuthScore[1][i],overAllAuthScore[3][i], overAllAuthScore[5][i] , 
                overAllHubScore[1][i], overAllHubScore[3][i], overAllHubScore[5][i]) ;

       }


    }
    
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
        int MAX_ITER = 6;
        float total_auth=0.0f, total_hub=0.0f ;
       
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

                overAllHubScore[i][n] =  HubScore[n];
                overAllAuthScore[i][n] =  AuthScore[n];
            }

            // RE-INITIALISE 
            total_auth=0;
            total_hub=0;

            // System.out.print("\nITER "+i+" : "+Arrays.toString(AuthScore)) ;
        }

        displayOutput(nodes);
      

    }
    public static void initialise(int nodes){

        for(int j=0; j<nodes; j++){
            AuthScore[j] =1.0f;
            HubScore[j] =1.0f;

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