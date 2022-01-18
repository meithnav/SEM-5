#include <conio.h>
#include<stdio.h>
#include<ctype.h>
#include <stdbool.h>

#define MAX 20

int a[MAX][MAX], visited[MAX], dfs_list[MAX], k = 0, queue[MAX], front = 0, rear = -1  ,goal[MAX], goal_pending ;



bool isGoal(int s , int len){

    for(int i = 0 ; i<len ; i++){
        if(s == goal[i]){
            return true;
        }
    }

    return false ;
    
}


void dfs(int s, int n , int goal_num) {

        visited[s] = 1; // checked its entry 
        //dfs_list[k++] =s ; 

        if(isGoal(s ,goal_num)){
            printf(" [%d] \t", s);
            goal_pending-- ;
        }else{
            printf(" %d \t", s);
        }

        
        for (int i = 0; i < n; i++) {


            if (a[s][i] == 1 && visited[i] == 0) {
            //passing the current node as parent to traverse in depth. 
            dfs(i, n, goal_num); 

            }
         }

       
}

void bfs(int s, int n , int goal_num){
  
        for (int i = 0; i < n; i++){

                if (a[s][i] == 1 && visited[i] == 0){
                    //adding all the unvisited childs
                    queue[++rear] = i;
                    visited[i] = 1; //make it visited 

                //  if(i == goal){
                    if(isGoal(i, goal_num)){
                        printf(" [%d] " , i) ; 
                        goal_pending-- ; 
                    }else{
                        printf(" %d " , i) ; 

                    } 
                }

        }

        if (front <= rear) {
                bfs(queue[++front], n , goal_num);
         }
    
    }
    

  


void main() {

  int n, s, key = -1;

  printf("\n Enter the number of Vertices  : ");
  scanf("%d", & n);

  printf("\n Enter the Matrix  of Vertices  : \n");
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      scanf("%d", & a[i][j]);

    }

  }

  printf("\n The Adjacency Matrix  : \n\n");

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      printf("%d \t", a[i][j]);
    }

    printf("\n");

  }

  do {


    // Clearing 
    for (int j = 0; j < MAX ; j++) {
      visited[j] = 0;
      queue[j] = 0;
      goal[j] = 0 ; 

    }


    front = 0;
    rear = -1;

    printf("\n \n ***** MAIN MENU ***** \n");
    printf("\n  1)BFS ");
    printf("\n  2)DFS");
    printf("\n -1)Exit ");
    printf("\n \n Enter your Choice : ");
    scanf("%d", &key);


    switch (key){

        case 1:{

        printf("\n ***** BFS *****");
        printf("\n\n Enter Goal Vertex(s) :");
        printf("\n To exit -1 ");

        int k =0 ;
        printf("\n\n Enter Goal %d :", k+1);
        scanf("%d", &goal[k]);   
        while(goal[k] != -1){
        printf(" Enter Goal %d :", k+2);
          scanf("%d", &goal[++k]);    
        }

        int num_goals = k ; 
        goal_pending = k ;

        //Select your Start vertex.  
        printf("\n Select your Start Vertex : ");
        scanf("%d", &s);
        queue[++rear] = s;
        visited[queue[front]] = 1; //make it visited  

        printf("\n *****BFS TRAVERSAL*****\n\n");

        if(isGoal(s, num_goals)){
            printf(" [%d] " , s) ; 
        }else{
            printf(" %d " , s) ;
        }       
        
        bfs(s, n ,num_goals);
        if(goal_pending>0){
            printf("\n No. of Goal Nodes still pending : %d" , goal_pending) ;
        }else{
            printf("\n All Goal Nodes reached!!") ;
        }
        printf("\n\n*********");

        printf("\n");
        break;

        }

        case 2:{

        printf("\n ***** DFS *****");

        printf("\n\n Enter Goal Vertex(s) :");
        printf("\n To exit -1 ");

        int k =0 ;
        printf("\n\n Enter Goal %d :", k+1);
        scanf("%d", &goal[k]);   
        while(goal[k] != -1){
        printf(" Enter Goal %d :", k+2);
          scanf("%d", &goal[++k]);    
        }

        int num_goals = k ; 
        goal_pending = k ;

        //Select your Start vertex.  
        printf("\n \n Select your Start Vertex : ");
        scanf("%d", &s);

        printf("\n *****DFS TRAVERSAL*****\n\n");
        dfs(s, n ,num_goals);

        if(goal_pending>0){
            printf("\n No. of Goal Nodes still pending : %d" , goal_pending) ;
        }else{
            printf("\n All Goal Nodes reached!!") ;
        }
        printf("\n\n*********\n");

        break;

        }

        case -1:{
            printf("\n \n***** END ***** \n");
            break;
        }
        

        default:{
            printf("\n INVALID KEY!! ");
            break;
        }
        

    }

  }while (key != -1);

}

/*

1 0 1 1 0
0 0 1 1 0
1 1 1 0 0
1 0 0 0 1
1 1 0 0 0

0 1 0 0 0 0 0 1 0
1 0 1 0 0 0 0 1 0 
0 1 0 1 0 1 0 0 1 
0 0 1 0 1 1 0 0 0 
0 0 0 1 0 1 0 0 0 
0 0 1 1 1 0 1 0 0 
0 0 0 0 0 1 0 1 1 
1 1 0 0 0 0 1 0 1 
0 0 1 0 0 0 1 1 0 



gcc -o search search.c

*/ 