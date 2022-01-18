#include <conio.h>
#include<stdio.h>
#include<ctype.h>
#include <stdbool.h>

#define MAX 20

int a[MAX][MAX], visited[MAX], dfs_list[MAX], k = 0, queue[MAX], front = 0, rear = -1  ,goal[MAX], goal_pending ;

int MAX_DEPTH ;

bool isGoal(int s , int len){

    for(int i = 0 ; i<len ; i++){
        if(s == goal[i]){
            return true;
        }
    }

    return false ;
    
}


void dfs(int s , int n,  int goal_num , int depth ,int limit) {

        visited[s] = 1; // checked its entry 
        int d= depth ; 

       // if(s == isGoal(s ,goal_num)){
        //     printf(" [%c] \t",(char)(s+65));
        //     goal_pending-- ;
        // }else{
        //     printf(" %c \t", (char)(s+65));



         if(isGoal(s ,goal_num)){
            printf(" [%d] ", s);
            goal_pending-- ;
        }else{
            printf(" %d ", s);
        }

        
        for (int i = 0; i < n; i++) {

            if(depth<=limit){
                if (a[s][i] == 1 && visited[i] == 0) {
                //passing the current node as parent to traverse in depth. 
                dfs(i, n, goal_num , ++d , limit); 

                }
            }
            
        }

       
}

bool isGoalPending(){

    if(goal_pending >0){
        return true ;
    }else{
        return false;
    }
}


void DFID(int s, int n, int num_goal){

    
    printf("\n Level %d :  %d" , 0 , s) ; 
    printf("\n %d goal(s) found ", (num_goal- goal_pending)) ;  
    printf("\n %d goal(s) pending ", goal_pending) ; 

    for(int i=0; i<=MAX_DEPTH ; i++){

    // Clearing 
        for (int j = 0; j < MAX ; j++) {
        visited[j] = 0;
        queue[j] = 0;

        }
    // Restoring total goal before next loop
        goal_pending = num_goal ;
        int depth=0 ;
        
        if(isGoalPending()){
            // Next Level
            printf("\n\n Level %d : " , i+1) ;  
            dfs(s, n , num_goal , depth, i) ;
            printf("\n %d goal(s) found ", (num_goal- goal_pending)) ;  
            printf("\n %d goal(s) pending ", goal_pending) ;  

        }else{

            printf("\n All goals reached!! ") ;  
            break ;
        }

    }


}


void main() {

  int n, s,g, key = -1;

  printf("\n Enter the number of Vertices  : ");
  scanf("%d", &n);

  printf("\n Enter the Matrix  of Vertices  : \n");
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      scanf("%d", & a[i][j]);

    }

  }

//   printf("\n The Adjacency Matrix  : \n\n");

//   for (int i = 0; i < n; i++) {
//     for (int j = 0; j < n; j++) {
//       printf("%d \t", a[i][j]);
//     }

//     printf("\n");

//   }

  
    // Clearing 
    for (int j = 0; j < MAX ; j++) {
      visited[j] = 0;
      queue[j] = 0;
      goal[j] = 0 ; 

    }
    
    printf("\n Enter the MAX DEPTH : ") ;
    scanf("%d", &MAX_DEPTH) ;
    // MAX_DEPTH = 4 ;
    printf("\n Enter the Start Node : ") ;
    scanf("%d", &s) ;


    printf("\n\n Enter Goal Vertex(s) :");
    printf("\n To exit -1 ");

        int k =0 ;
        printf("\n\n Enter Goal %d :", k+1);
        scanf("%d", &goal[k]);   
        while(goal[k] != -1){
        printf(" Enter Goal %d :", k+2);
          scanf("%d", &goal[++k]);    
        }

        int num_goal = k ; 
        goal_pending = k ;


    // printf("\n Enter the Goal Node : ") ;
    // scanf("%d", &g) ;
    // goal_pending = 1;
    // int num_goal=1 ;

    printf("\n ***** DFID***** \n");
    DFID(s, n, num_goal) ;


}

/*

1 0 1 1 0
0 0 1 1 0
1 1 1 0 0
1 0 0 0 1
1 1 0 0 0

9
0 1 0 0 0 0 0 1 0
1 0 1 0 0 0 0 1 0 
0 1 0 1 0 1 0 0 1 
0 0 1 0 1 1 0 0 0 
0 0 0 1 0 1 0 0 0 
0 0 1 1 1 0 1 0 0 
0 0 0 0 0 1 0 1 1 
1 1 0 0 0 0 1 0 1 
0 0 1 0 0 0 1 1 0 


15
0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0





gcc -o DFID DFID.c

*/ 