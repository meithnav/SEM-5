import java.util.*;
import java.util.Arrays;
import java.lang.Integer; 

public class AStar{


    public static void calcAstar(int[][] gN , int[] hN , String[] city, int cur , int goal ,int N){

        int min = Integer.MAX_VALUE ;
        int minIndex =0;
        int[] visited = new int[N] ;
        visited[cur] = 1;

        System.out.print("\n\n ***** A* PATH *****") ;
        System.out.print("\n\n "+city[cur]+" ") ;

        while(cur != goal){

            for(int j=0; j<N; j++){
                
                if(gN[cur][j] >0){

                    if(visited[j] !=1 && min > gN[cur][j]+hN[j]){
                        min =gN[cur][j] +hN[j];
                        minIndex = j; 
                    }
                }
            }

            System.out.print(" -> "+city[minIndex]+" ") ;
            cur = minIndex ;
            min = Integer.MAX_VALUE ;
            visited[minIndex] = 1;

        }

    }
   
    public static void main(String args[] ){
        Scanner sc = new Scanner(System.in) ;

        System.out.print("Enter the number of Nodes : ") ;
        // int n = sc.nextInt() ;
        int N = 20 ;

        // int[] hN = new int[N] ;
        // int[] city = new int[N] ;
        int[][] gN = new int[N][N] ;
        int[] hN = {366,0,160,242,161,178,77,151,226,244,241,234,380,98,193,253,329,80,199,374 } ;
        String[] city = {"Arad" , "Bucharest" , "Craivoa" , "Dobreta" , "Eforie","Fagaras", "Guirgiu" ,"Hirsova", "Iasi",
                    "Lugoj" ,"Mehadia" , "Neamt" , "Oradea", "Pitesti","Rimnieu Vilcea" , "Sibiu", "Timisoara", "Urzieeni",
                    "Vaslui" , "Zerind"} ;

    
        System.out.print("\n\n HEURISTIC VALUES : ") ;
        for(int i =0 ; i<N ;i++ ){
            if(i== 14){
                System.out.print("\n "+city[i]+": "+hN[i]) ;

            }else{
                System.out.print("\n "+city[i]+" : \t "+hN[i]) ;

            }
            
        }

        // Distances
        System.out.print("\n\n G(n) for CITIES \n") ;

       for(int i =0 ; i<N ;i++ ){
            for(int j=0; j<N; j++){
                gN[i][j] = sc.nextInt() ;
            }

        }
        // for(int i =0 ; i<N ;i++ ){
        //     for(int j=0; j<N; j++){
        //         System.out.print(gN[i][j]+"\t") ;
        //     }
        //     System.out.print("\n") ;
        // }

        
        calcAstar(gN, hN,city, 0, 1 , N);
    
    }

}

/*

0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       140     118     0       0       75
0       0       0       0       0       211     90      0       0       0       0       0       0       101     0       0       0       85      0       0
0       0       0       120     0       0       0       0       0       0       0       0       0       138     146     0       0       0       0       0
0       0       120     0       0       0       0       0       0       0       75      0       0       0       0       0       0       0       0       0
0       0       0       0       0       0       0       86      0       0       0       0       0       0       0       0       0       0       0       0
0       211     0       0       0       0       0       0       0       0       0       0       0       0       0       99      0       0       0       0
0       90      0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0
0       0       0       0       86      0       0       0       0       0       0       0       0       0       0       0       0       98      0       0
0       0       0       0       0       0       0       0       0       0       0       87      0       0       0       0       0       0       92      0
0       0       0       0       0       0       0       0       0       0       70      0       0       0       0       0       111     0       0       0
0       0       0       75      0       0       0       0       0       70      0       0       0       0       0       0       0       0       0       0
0       0       0       0       0       0       0       0       87      0       0       0       0       0       0       0       0       0       0       0
0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       151     0       0       0       71
0       101     138     0       0       0       0       0       0       0       0       0       0       0       97      0       0       0       0       0
0       0       146     0       0       0       0       0       0       0       0       0       0       97      0       80      0       0       0       0
140     0       0       0       0       99      0       0       0       0       0       0       151     0       80      0       0       0       0       0
118     0       0       0       0       0       0       0       0       111     0       0       0       0       0       0       0       0       0       0
0       85      0       0       0       0       0       98      0       0       0       0       0       0       0       0       0       0       142     0
0       0       0       0       0       0       0       0       92      0       0       0       0       0       0       0       0       142     0       0
75      0       0       0       0       0       0       0       0       0       0       0       71      0       0       0       0       0       0       0


*/