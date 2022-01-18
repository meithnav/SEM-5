import java.util.*;
import java.util.Arrays ;
import java.lang.Integer ;
import java.lang.Math ;


public class hill{

    public static int MAX, MIN ;
    public static int[] hill = new int[10];
    public static int hill_pointer=0 ;
    

    public static int heuristic(int[] arr, int end){

        int total=0 ; 
        for(int i=0 ; i<end; i++){

            if(i>0){
                if(arr[i] != i){
                    total -= i;

                }else{
                    total += i;
                }

            }

        }  

        return total;
    }

    public static void displayArr(int[] arr, int len){
        System.out.print("[");
        for(int i =0; i<len;i++){
            System.out.print(" "+arr[i]+"");
        }
        System.out.print(" ]");
    }

    public static void displayPop(int[] arr, int len){
        for(int i =0; i<len;i++){
            System.out.print(" ["+arr[i]+"]");
        }

    }

    public static void swap(int elem){
        hill[hill_pointer] = elem ; 
    }

    public static void push(int elem){  
        hill_pointer++ ;
        hill[hill_pointer] = elem ; 
    }

    public static void pop(){
        hill_pointer-- ;
    }


    public static void calcHill(int[] arr, int num){

        int[] popped = new int[num] ;
        int pointer = 0;
        int step =0 ;
        int curHeuristic = heuristic(arr, arr.length) ;
        int end = arr.length-1 ;
            
        // System.out.print("\n\n CURRENT ARRAY : ") ;
        // displayArr(arr, end+1) ;
        // System.out.print("\n CURRENT HEURISTIC : "+curHeuristic) ;
           
     // POPPING
        while(curHeuristic < MAX && end>=0){
            int nextHeuristic = heuristic(arr, end);

          if(step %2 == 0){
            System.out.print("\n\n CURRENT ARRAY : ") ;
            displayArr(arr, end+1) ;
            displayPop(popped, pointer) ;      
            System.out.print(" --> "+curHeuristic) ;
            // System.out.print("\n POPPED ARRAY : ") ;
          }

            if(nextHeuristic > curHeuristic){
                popped[pointer] = arr[end];
                curHeuristic = nextHeuristic ;
                end-- ; pointer++ ;

            }else{
                // System.out.print("\n\n CURRENT ARRAY : ") ;
                // displayArr(arr, end+1) ;
                // displayPop(popped, pointer) ;      
                // System.out.print("\n CURRENT HEURISTIC : "+curHeuristic) ;
                // // System.out.print("\n POPPED ARRAY : ") ;
               
                break ; 
            }

            step++ ;

        }


        // COPY REST INTO HILL
        System.arraycopy(arr, 0, hill, 0, end+1);  
        hill_pointer= end;

        int[] visited_popped = new int[num];

        // END =0 
        if(end == 0){

           int temp = hill[hill_pointer];
            for(int i=0; i<=pointer;i++){
                if(hill[hill_pointer] > popped[i]){
                    swap(popped[i]);
                }
    
            }

            visited_popped[hill[hill_pointer]] = 1 ;
            popped[pointer] = temp ;
        }
        System.out.print("\n\n CURRENT ARRAY : ") ;
        displayArr(hill, hill_pointer+1);
        System.out.print(" --> "+curHeuristic) ;

        // PUSH
        while(curHeuristic < MAX) {
            // All empty-- Find next push
            int maxHeuristic= Integer.MIN_VALUE ;
            int pushVal= -1 ;
            int index= -1;
            hill_pointer++ ;

            for(int i=0; i<=pointer; i++){

                if(visited_popped[i] ==0){
                    swap(popped[i]) ;
                    
                    if( maxHeuristic < heuristic(hill, hill_pointer+1)){
                        index = i;
                        pushVal = popped[i];
                        maxHeuristic = heuristic(hill, hill_pointer+1);
                    }
                }
            }
            // pop() ;
            swap(pushVal) ;
            visited_popped[index] = 1;
            curHeuristic = maxHeuristic;
            step++ ;
            // System.out.print("\n Cur Array : ") ;
            // displayArr(hill, hill_pointer+1) ;
            // System.out.print("\n Cur Heuristic : "+maxHeuristic) ;
            // System.out.print("\n\n") ;

        }

        System.out.print("\n\n CURRENT ARRAY : ") ;
        displayArr(hill, hill_pointer+1) ;
        System.out.print(" --> "+curHeuristic) ;        


        System.out.print("\n GOAL STATE REACHED!!") ; 

    }

    
    
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in) ;
        System.out.print("\n ENTER THE NUMBER OF BLOCKS : ") ;
        int num = sc.nextInt();

        int[] arr = new int[num] ;
        System.out.print("\n ENTER BLOCKS ORDER : ") ;

        for(int i=0 ; i<num; i++){
            arr[i] = sc.nextInt() ;
        }

        // int num = 4;
        // int[] arr =  {1,2,3,0} ;
        // int[] arr1 =  {0,1,2,3} ;
        // int[] arr2 =  {0,1,3,2} ;
        // int[] arr3 =  {3,2,1,0} ;
        // int[] arr4 =  {1,0,2,3} ;

        MAX = num*(num-1)/2 ;       
        MIN = MAX*(-1);  

        // System.out.print("\n BLOCKS ORDER : ") ;
        // displayArr(arr,num);
        // System.out.print(" --> "+heuristic(arr, num) ) ;

        calcHill(arr,num) ;


        sc.close();
        
    }
    
}
