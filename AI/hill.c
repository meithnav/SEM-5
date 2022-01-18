#include <stdio.h>
#include <conio.h>

int boxes, input[10], stack[10], freeBox[10];
int top = -1;
int freeBoxCounter=0;

void push(int item) {
    if (top == 9) {
        printf("Stack overflow...");
    } else {
        stack[++top] = item;
    }
}

int pop() {
    int k;
    if (top == -1) {
        return (0);
    } else {
        k = stack[top--];
        return (k);
    }
}

int getHeuristic() {
    int heuristic = 0;

    for(int i=0; i<top+1; i++) {
        if(stack[i] == i+1){
            for(int j=0; j<i; j++) heuristic++;
        } else {
            for(int j=0; j<i; j++) heuristic--;
        }
    }
    return heuristic;
}

void printStack() {
    printf("Stack is: [ ");
    for(int i=0; i<=top; i++){
        printf("%d ", stack[i]);
    }
    printf("]  ---> %d\n", getHeuristic());
}

void hillClimbing(int maxHeuristic) {
    int popped1, popped2, min, heuristic1, heuristic2, index;
    int initialHeuristic = getHeuristic();

    printStack();
    while(getHeuristic() < 0 && freeBoxCounter < boxes+1){
        popped1 = pop();
        heuristic1 = getHeuristic();
        popped2 = pop();
        heuristic2 = getHeuristic();
        if(heuristic1 > heuristic2){
            push(popped2);
            freeBox[freeBoxCounter++] = popped1;
        } else {
            freeBox[freeBoxCounter++] = popped1;
            freeBox[freeBoxCounter++] = popped2;
        }
        printStack();
    }

    while(getHeuristic() < maxHeuristic){
        min = 99999;
        for(int i=0; i<boxes; i++){
            if(min > freeBox[i]){
                min = freeBox[i];
                index = i;
            }
        }
        push(min);
        freeBox[index]=99999;
        min = 99999;
        for(int i=0; i<boxes; i++){
            if(min > freeBox[i]){
                min = freeBox[i];
                index = i;
            }
        }
        push(min);
        freeBox[index]=99999;
        printStack();
    }
}

void main() {
    int box, level;

    printf("\nEnter the Number of Boxes: ");
    scanf("%d", &boxes);
     printf("\nEnter the Order of the Boxes: ");
    for(int i=0; i<boxes; i++){
        scanf("%d", &box);
        input[i] = box;
        push(box);
        freeBox[i] = 99999;
    }

    int maxHeuristic=0;
    for(int i=0; i<boxes; i++){
        maxHeuristic += i;
    }

    hillClimbing(maxHeuristic);
}