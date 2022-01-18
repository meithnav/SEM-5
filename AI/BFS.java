import java.util.*;
import java.lang.Integer; 
import java.util.Arrays ; 

public class BFS{

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in) ;

        System.out.print("\n Enter the number of nodes : ") ;
        int n = sc.nextInt() ;

        int[] visited= new int[n] ;
        int[][] matrix = new int[n][n] ;



        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<n ; j++){

                matrix[i][j]  = sc.nextInt() ;

            }
        }

        

    }


}