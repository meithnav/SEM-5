#include <stdio.h>

int tag[16];
int mru[16] = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};

void mruUpdate(int index)
{
    int i;
    // find index in mru
    for (i = 0; i < 16; i++)
        if (mru[i] == index)
            break;
    // move earlier refs one later
    while (i > 0) {
        mru[i] = mru[i-1];
        i--;
    }
    mru[0] = index;
}

int main()
{
    int addr;
    int i, j, t;
    int hits, accesses;
    FILE *fp;
    
    fp = fopen("trace.txt", "r");
    hits = 0;
    accesses = 0;
    while (fscanf(fp, "%x", &addr) > 0) {
        /* simulate fully associative cache with 8 words */
        accesses += 1;
        printf("%4d: 0x%08x ", accesses, addr);
        for (i = 0; i < 16; i++) {
            if (tag[i] == addr) {
                hits += 1;
                printf("Hit%d ", i);
                mruUpdate(i);
                break;
            }
        }
        if (i == 16) {
            /* allocate entry */
            printf("Miss ");
            i = mru[15];
            tag[i] = addr;
            mruUpdate(i);
        }
        for (i = 0; i < 16; i++)
            printf("0x%08x ", tag[i]);
        for (i = 0; i < 16; i++)
            printf("%d ", mru[i]);
        printf("\n\n");
    }
    printf("Hits = %d, Accesses = %d, Hit ratio = %f\n", hits, accesses, ((float)hits)/accesses);
    close(fp);
}