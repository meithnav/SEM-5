
#include <stdio.h>

int tag[2][4];
int mru[4] = {1,1,1,1};


void mruUpdate(int index){    
int i;    
// find index in mru    
for (i = 0; i < 4; i++)        
    if (mru[i] == index)            
    break;    
    // move earlier refs one later    
    while (i > 0) {        
        mru[i] = mru[i-1];        
        i--;    
        
    }    
    mru[0] = index;
    
}

int main(){
    int addr;
    int i, j; 
    int hits, accesses;
    FILE *fp;

    fp = fopen("trace.txt", "r");
    hits = 0;
    accesses = 0;
    while (fscanf(fp, "%x", &addr) > 0) {
        /********* YOUR CODE HERE ************/
        
        printf("%3d: 0x%08x ", accesses, addr); 
        for( i=0; i<2; i++){ 
            accesses += 1; 
            for(j=0; j<4; j++){   
                if (tag[i][j] ==addr){                 
                    hits += 1;                
                    printf("Hit%d ", i);                
                    mruUpdate(i);                
                    break; 
                    
                }                        
                
            } if(tag[i][j]==addr){                  
                printf("Hit%d ", i);                
                
            }else {                               
                printf("Miss");               
                tag[i][j] = addr; 
                //hits +=1;                      
                }        
            
        } for(i = 0; i < 2; i++){ 
            for(j=0; j<4; j++){ 
                printf("0x%08x ", tag[i][j]); 
                
            }
            
        }        
        for (i = 0; i < 2; i++)            
        printf("%d ", mru[i]); 

        printf("\n");     
        
    }   
    printf("Hits = %d, Accesses = %d, Hit ratio = %f\n", hits, accesses, ((float)hits)/accesses);    
    close(fp);
    
}
   