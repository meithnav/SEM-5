import java.util.*;
import java.util.Arrays;

public class SeqMem {

    public static  int MAX_PAGES = 64 ;
    public static int MAX_BLOCKS=64;
    public static int MAX_WORDS=32;
    public static int[][][] mm = new int[MAX_PAGES][4][2] ;
    public static int[][] L2 = new int[MAX_BLOCKS][2] ;
    public static int[] L1 = new int[MAX_WORDS];
    public static int processor =-1;
    public static int TL1 = 20 ;
    public static int TL2 = 60 ;
    public static int TMM = 120 ;
    public static int in_index_L1=-1 ;
    public static int in_index_L2=-1;

    
    public static int[] searchMM(int elem){

        int p = (elem/(mm[0].length*mm[0][0].length)%MAX_PAGES) ;
        int b = (elem/2)%4;
        int w = elem%2 ;

        // System.out.print(" "+Arrays.toString((mm[p][b]))+" ") ;

        System.out.print(" W"+elem+" Found in Main Memory --> PAGE "+p);
        System.out.print("\n TOTAL TIME TAKEN : "+ (TL1+TL2+TMM)+" ns\n");

        return mm[p][b] ;

        
    }
    
    public static int searchL2(int elem){

        if(in_index_L2 == -1 ){
            // Empty
            int[] b = searchMM(elem);
            L2[++in_index_L2] = b;

            for(int i=0 ; i<b.length; i++){
                if(b[i] == elem){
                    return b[i];
                }
            }

        }else{

            for(int i = 0; i<=in_index_L2 ; i++){
                for(int k = 0; k<L2[0].length ; k++){

                    if(L2[i][k] == elem){
                        System.out.print(" W"+elem+" Found in L2 --> BLOCK "+i);
                        System.out.print("\n TOTAL TIME TAKEN : "+ (TL1+TL2)+" ns\n");

                        return elem;
                    }
                }
            }

            int[] b = searchMM(elem); 
            in_index_L2++; 
            L2[in_index_L2%MAX_BLOCKS] = b ;

            for(int i=0 ; i<b.length; i++){
                if(b[i] == elem){
                    return b[i];
                }
            }

        }

        return 0 ;
    }


    public static int searchL1(int elem){

       if(in_index_L1 == -1 ){
        // Empty
            int w = searchL2(elem);
            in_index_L1++;
            L1[in_index_L1] = w;
            return w;

        }else{

            for(int i = 0; i<=in_index_L1 ; i++){

                if(L1[i] == elem){
                    System.out.print(" W"+elem+" Found in L1 WORD --> "+i);
                    System.out.print("\n TOTAL TIME TAKEN : "+ (TL1)+" ns\n");

                    // break;
                    return elem;
                }
            }

            int w = searchL2(elem); 
            in_index_L1++; 
            L1[in_index_L1%MAX_WORDS] = w;
            return w ;

       }

       
    }

    public static void SeqMemSearch(int elem){

        if(processor != elem){
            int w = searchL1(elem);
            processor= w ;

        }else{
            System.out.print("W"+elem+" Found in Processor.");
            System.out.print("\n TIME TAKEN : 0ns\n");


        }
    }

    

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in) ;

        // Data in MM
        int data = 0 ;
        for(int i=0; i< MAX_PAGES ; i++){
            for(int j=0; j< mm[0].length ; j++){
                for(int k=0; k< mm[0][0].length ; k++){

                    mm[i][j][k] = data ;
                    data++ ;
                }
            }

        }

        int elem=0 ;
        while(elem != -1){
            System.out.print("\n Enter the word to Search : ") ;
             elem = sc.nextInt() ;
             if(elem == -1){
                 break;
             }else{
                SeqMemSearch(elem) ;
            }

        }
        
    }
    
}
