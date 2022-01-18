import java.util.*;
import java.lang.Integer;
import java.util.Arrays;
import java.util.Random;




public class Wumpus{

    public static int BOX = 4;
    public static boolean userStatus = true;
    public static int[][] map = new int[BOX][BOX] ;
    public static int[][] learning = new int[BOX][BOX] ;

    public static int[] userBox = new int[2];  // [X,Y]
    public static int score = 0;


    public static void moveUser(){

        Random random = new Random();

        int[] prevPos = new int[2];
        int[] nextPos = new int[2];
        System.arraycopy(userBox, 0, prevPos, 0, prevPos.length); 
        System.arraycopy(userBox, 0, nextPos, 0, nextPos.length); 

        int moveIndex  = random.nextInt(2);
        nextPos[moveIndex]+=1;

        if(userBox[moveIndex]<BOX -1 && learning[nextPos[0]][nextPos[1]] != -1){
            userBox[moveIndex]+=1;

            if(map[userBox[0]][userBox[1]] == 2 || map[userBox[0]][userBox[1]] == 1){
                userStatus=false;
                learning[userBox[0]][userBox[1]] = -1;

            }else{
                map[prevPos[0]][prevPos[1]] = 0;
                map[userBox[0]][userBox[1]] = -1;
                //SENSE for BREEZE OR STRENCH

            }
            

        }else{
            moveUser() ;
        }

        System.out.print("\n "+ Arrays.toString(userBox));


    }
    
    public static void displayMap(){

        System.out.print("\n\n WUMPUS MAP : \n\n"); 
        for(int i=0; i<BOX;i++){
            for(int j=0; j<BOX;j++){

                if(map[i][j] == -1){
                    System.out.print(" U "); 

                }else if(map[i][j] == 2){
                    System.out.print(" W "); 

                }else if(map[i][j] == 3){
                    System.out.print(" G "); 
                    
                }else if(map[i][j] == 1){
                    System.out.print(" P "); 
                    
                }else{
                  System.out.print(" "+map[i][j]+" "); 
                }

            }

            System.out.print("\n"); 
        }

    }
    
    public static void initialiseGame(){
        System.out.print("\n ***** WUMPUS GAME****");
        System.out.print("\n Starting Game.....") ;

        // SET SAFE = 0 , PIT = 1, WUMPUS=2 & GOLD = 3 , user = -1

        map[1][2] = 3; //GOLD
        map[2][0] = 1; //PIT
        map[2][2] = 1; //PIT
        map[3][3] = 1; //PIT
        map[0][2] = 2; //WUMPUS

        // START
        userBox[0] = 0; //X
        userBox[1] = 0; //Y
        map[0][0] = -1;

        displayMap() ;

    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
    
        initialiseGame();

        int gameCount=1;
        while(map[userBox[0]][userBox[1]] != 3){
            
            System.out.print("\n ATTEMPT "+gameCount+"\n");
            while(userStatus && map[userBox[0]][userBox[1]] != 3){

                moveUser();
                displayMap();
            }
    
            gameCount++ ;
            userBox[0] = 0;
            userBox[1]=0;
            userStatus = true;

        }
       


       


    
        sc.close();
        
    }
    

}
