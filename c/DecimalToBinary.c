#include <stdio.h>
#include <stdlib.h>

#define STACKSIZE 100

// Program to print binary equivalent of a given decimal number using stack.

struct Stack {
	int top;
	int item[STACKSIZE];
};

int isFull(struct Stack *ps) {
	if(ps->top == STACKSIZE-1)
		return 1;
	else 
		return 0;
}

void push(struct Stack *ps, int x) {
	if(isFull(ps)) {
		printf("\nStack Overflow cannot Push");
		return;
	} else {
		ps->item[++(ps->top)] = x;
	}
}

int isEmpty(struct Stack *ps) {
	if(ps->top == -1)
		return(1);
	else
		return(0);
}

void show(struct Stack *ps) {
	int i;

	for(i = ps->top; i >= 0; i--)
		printf("%d", ps->item[i]);
}

int main() {
	int no, r;
	struct Stack s;

	s.top = -1; // default case

	printf("Enter the number :");
	scanf("%d", &no);

	while(no != 0) {
		r = no % 2;
		push(&s, r);
		no = no / 2;
	}

	printf("The Binary form : ");
	show(&s);
	return 0;
}