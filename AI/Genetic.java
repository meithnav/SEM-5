import java.util.*;
import java.lang.Integer;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Genetic{

    public static int MAX =1024 , BITS=10, string=4; 
    public static int[][] GeneString= new int[string][BITS+1] ;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public static String displayGene(int[] arr){

        String str ="" ; 

        for(int i=BITS; i>0 ; i--){
           str += ""+arr[i]+"" ;
        }

        return str;
    }

         
    public static int toDecimal(int[] arr){

        int num = 0;
        for(int i =arr.length -1; i>0; i--){
            num = num*2 + arr[i];
        }

        return num ;
    }

     
    public static int[] getGenes(){
          
        int m = (int)(Math.floor((Math.random())*MAX)); 
        int[] arr = new int[BITS+1] ; 
        arr[0] = m;
        int count=1;

        // BINARY [LSB -- MSB]
        while(m>0){

            arr[count] = m%2 ;
            count++ ;
            m /=2; 
        }

        return arr;
    }


    public static double[][] calcObjective(){

        
        double avg_f=0 , total_f=0 ,  Max_f=Integer.MIN_VALUE ;
        double[] arr_fX = new double[string]; 
        double[][] result = new double[string+3][4]; // Strings,Sum,Avg,Max

        for(int i=0; i<string ; i++){
            int numOnes =0  ; 
            for(int j=1; j<BITS+1 ; j++){
            
                if(GeneString[i][j] == 1){
                    numOnes++ ;
                }
            }

            double fX = (double)numOnes/10 ;
            arr_fX[i] =fX ;
            total_f += fX; 

            if(Max_f < fX){
                Max_f = fX ;
            }

        }

        avg_f = total_f/string;
        double min_expected = Integer.MAX_VALUE ; 


        // RESULTS
        for(int i=0; i<string ; i++){

            // F(x)
            result[i][0] = arr_fX[i];
            // Fi / tot_f
            result[i][1] = (double)arr_fX[i]/total_f ;
            // Fi / avg_f
            result[i][2] = (double)arr_fX[i]/avg_f ;
            // ACTUAL COUNT
            result[i][3] = Math.round(result[i][2]) ;

            if(min_expected > result[i][2]){
                min_expected= result[i][2] ;
            }
 
        }

        // ACTUAL COUNT
        //  for(int i=0; i<string ; i++){
        //     result[i][3] = Math.round(result[i][2]/min_expected) ;
        //  }

        // SUM  
          result[string][0] =total_f;
          result[string][1] = (double)(total_f/total_f) ;
          result[string][2] = (double)(total_f/avg_f) ;

        //   AVG
        result[string+1][0] =avg_f;
        result[string+1][1] = avg_f/total_f ;
        result[string+1][2] = avg_f/avg_f ;
        //  MAX
        result[string+2][0] =Max_f;
        result[string+2][1] = Max_f/total_f ;
        result[string+2][2] = Max_f/avg_f ;

        

        return result;
    }

    public static int[] displayInitialise(double[][] result ,int iter){

        double min_Actual=Integer.MAX_VALUE ;
        double max_Actual=Integer.MIN_VALUE ;
        int min_index= -1,  max_index= -1;
        int[] index = new int[2] ;

        System.out.print("\n\n String No\tPopulation(P"+iter+")\tX\tf(X)\tFi/SUM(f)\tfi/f\tActual Count" ) ;

        for(int i=0; i<string; i++){
            System.out.print("\n    "+(i+1)+"\t\t"+displayGene(GeneString[i])+"\t"+ Math.round(GeneString[i][0])+"\t"+
            df.format( result[i][0])+"\t"+df.format(result[i][1]) +"\t\t"+df.format(result[i][2])+"\t"+
            Math.round(result[i][3])  );

            if(min_Actual> result[i][3]){
                min_index = i;
                min_Actual= result[i][3] ;
            }
            if(max_Actual<= result[i][3]){
                max_index = i;
                max_Actual= result[i][3] ;
            }

        }
        index[0] = min_index;
        index[1] = max_index ;


        // SUM
        System.out.print("\n\n  \t\tSUM\t\tSUM(f)\t"+
        df.format( result[string][0])+"\t"+df.format(result[string][1]) +"\t"+df.format(result[string][2]) );
        // AVG
        System.out.print("\n  \t\tAVG\t\tf\t"+
        df.format( result[string+1][0])+"\t"+df.format(result[string+1][1]) +"\t"+df.format(result[string+1][2]) );
        // MAX
        System.out.print("\n  \t\tMAX\t \t"+
        df.format( result[string+2][0])+"\t"+df.format(result[string+2][1]) +"\t"+df.format(result[string+2][2]) );
        
        return index;
    }

    public static void replaceWeak(int[] index ){

        // GeneString[index[]][]

        System.arraycopy(GeneString[index[1]], 0, GeneString[index[0]], 0, GeneString[0].length);

        // for(int i=0; i<string; i++){
        //     System.out.print("\n    "+(i+1)+"\t\t"+displayGene(GeneString[i])+"\t"+ Math.round(GeneString[i][0])+"\t");
        // }
        // System.out.print("\n");

        // return GeneString ;
    }

    public static int[] replaceMate(int cur , int mate , int crossSite){
        int length = 5 ;
        int[] arr = new int[BITS+1] ;

        
        for(int i=crossSite+1; i<BITS+1; i++){
            arr[i] = GeneString[cur][i] ;
        }

        for(int i=1; i<=crossSite; i++){
            arr[i] = GeneString[mate][i] ;
        }

        return arr ;
    }

    public static void displayCrossOver(int iter){

        System.out.print("\n\n\n CROSS OVER : ");
        System.out.print("\n String No\tPopulation(P"+iter+")\tMate\tCrossover Site\tNew Pop(P"+(iter+1)+")\tNEW X" ) ;
        int[][] newGeneString= new int[string][BITS+1] ;
        

        for(int i=0; i<string; i++){
            int mate = (i%2==0)? i+1: i-1 ;
            int crossSite= 5;
            // int[] arr = new int[BITS+1] ;
            newGeneString[i] = replaceMate(i , mate, crossSite) ;
            newGeneString[i][0]= toDecimal(newGeneString[i] ) ;

            System.out.print("\n    "+(i+1)+"\t\t"+displayGene(GeneString[i])+"\t"+(mate+1)+"\t5\t\t"+
            displayGene(newGeneString[i])+"\t"+newGeneString[i][0] ); 
            
            
        }

        GeneString= newGeneString;


    }

    public static void mutation(){

        System.out.print("\n\n String No\tPopulation(P2)\tP2 X\t  Mutated Population\tMUTATED X" ) ;
        int[][] gen2 = new int[string][BITS+1] ; 

        for(int i=0; i<string;i++){
            int index;
            System.arraycopy(GeneString[i], 0, gen2[i], 0, BITS+1);

            do{
                index =(int)(Math.floor((Math.random()*BITS))) +1;
                if(GeneString[i][index] == 0){
                    GeneString[i][index] =1; //mutate
                    break;
                }else{
                   
                }
            }while(GeneString[i][index] != 0) ;
            GeneString[i][0] = toDecimal(GeneString[i]);

            System.out.print("\n  "+(i+1)+"\t\t"+displayGene(gen2[i])+"\t"+ gen2[i][0]+"\t  "+displayGene(GeneString[i])+"\t\t"+GeneString[i][0] ) ;
        }

    }
  
    public static void GeneticAlgo(){

        System.out.print("\n\n\n *****GENERATION 1*****");
        for(int i=0; i<string; i++){
            GeneString[i] = getGenes() ;
            // System.out.print("\n "+GeneString[i][0]+" : "+displayGene(GeneString[i] ));
        }

        double[][] result = calcObjective() ;
        int[] index = displayInitialise(result, 0);
        // REPLACE WEAK
        replaceWeak(index);
        displayCrossOver(0);

        System.out.print("\n\n\n *****GENERATION 2*****");
        double[][] result2 = calcObjective() ;
        index = displayInitialise(result2, 1);
        // REPLACE WEAK
        replaceWeak(index);
        displayCrossOver(1);


        // MUTATION
        System.out.print("\n\n\n *****FINAL GENERATION POST MUTATION*****");
        mutation() ;

    }
    
public static void main(String args[]){

    Scanner sc = new Scanner(System.in) ;

    System.out.print("\n Enter the number of Strings : "+string);
    // int string= sc.nextInt() ;

    GeneticAlgo();

    sc.close() ;
}

}