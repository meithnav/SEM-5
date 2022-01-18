
import java.util.*;
import java.util.Arrays ;
import java.lang.Math ;
import java.lang.Integer ;


public class BestFit {


    public static int findBlock(int curr_process , int[] curr_mem , int[] busy_mem,  int num_p , int num_b){

        int index = -1;
        int lowest = Integer.MAX_VALUE ;  


        for(int i =0 ; i<num_b; i++){
        //Empty
           if(busy_mem[i] ==0 && (curr_mem[i] - curr_process)>=0 && lowest > (curr_mem[i] - curr_process)){
                lowest = curr_mem[i] - curr_process ;   
                index = i ;
           }
       }  
    
       return index ; 
    }
    
    public static void bestFit(int[] process , int[] memory , int num_p , int num_b){

        int[] busy_mem = new int[num_b] ;
        int[] internal_fragment = new int[num_b] ;
        int[] exe_process = new int[num_b] ;
        // int[] 

        System.out.print("\n Memory Block\tJob\tJob Size\tStatus\tInternal Frag") ;
        for(int i =0 ; i<num_p ; i++){

            int index = findBlock(process[i] , memory ,  busy_mem , num_p , num_b) ;

            if(index ==-1){
                // No Space
            }else{
                busy_mem[index] = 1 ;
                exe_process[index] = i;
                internal_fragment[index] = memory[index] - process[i] ;
                // int internal_frag = memory[index] - process[i] ;
                // System.out.print("\n"+ (index+1)+"\tP"+(i)+"\t"+process[i]+"\tBusy\t"+internal_fragment[index]) ;

            }
            
        }//closes for

        // Display

        int total=0 , total_frag=0 ;
        for(int i=0 ; i<num_b ; i++){

         if(busy_mem[i] == 1){
            System.out.print("\n "+memory[i]+"\t\tP"+(exe_process[i] +1)+"\t"+(process[exe_process[i]])+"\t\tBusy\t"+internal_fragment[i]) ;
            total += process[exe_process[i]];
            total_frag += internal_fragment[i]; 
         }else{
            System.out.print("\n "+memory[i]+"\t\tNone"+"\t-\t"+"\tFree") ;

         }

        }

        System.out.print("\n Total Used : \t\t"+total+ "\t\t\t"+total_frag) ;



    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in) ;

        System.out.print("\n Enter the number of Memory Blocks : ") ;
        int num_block = sc.nextInt() ;

        int[] memory = new int[num_block] ;

        System.out.print("\n\n Enter the Sizes of Memory Blocks : \n") ;
        for(int i=0 ; i<num_block; i++){
            System.out.print(" Size of Memory Block B"+(i+1)+" : ") ;
            memory[i] = sc.nextInt() ;
        }

        System.out.print("\n\n Enter the number of Processes : ") ;
        int num_process = sc.nextInt() ;
        int[] process = new int[num_process] ;

        System.out.print("\n\n Enter the Sizes of Processes : \n") ;
        for(int i=0 ; i<num_process; i++){
            System.out.print(" Size Requested by P"+(i+1)+" : ") ;
            process[i] = sc.nextInt() ;
        }

        // int[] memory = {30, 50, 200,700, 980} ; 
        // int[] process = {20 , 200 , 500, 50} ;
       

        bestFit(process , memory, num_process, num_block) ;
        // bestFit(process , memory ,4, 5) ;


        
    }
    
}
