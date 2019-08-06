#include<stdio.h>
#include<stdlib.h>

#define STACKSIZE 50

// Maintaining two stacks in one array. 
// This type of implementation is known as double stack. 
// In this implementation both stacks grow in opposite directions. 
// One from lower index to higher index and second from higher to lower index.

struct D_Stack {
	int items[STACKSIZE];
	int top1, top2;
};

void push1(struct D_Stack *ps, int x) {
	if(ps->top1 == ps->top2 - 1) {
		printf("\nStack Overflow cannot PUSH");
		return;
	} else
		ps->items[++ps->top1] = x;
}

void push2(struct D_Stack *ps, int x) {
	if(ps->top1 == ps->top2 -1) {
		printf("\nStack2 Overflow cannot PUSH");
		return;
	} else
		ps->items[--ps->top2] = x;
}

int empty(struct D_Stack *ps) {
	if(ps->top1 == -1 && ps->top2 == STACKSIZE)
		return 1;
	else
		return 0;
}

int pop1(struct D_Stack *ps) {
	if(ps->top1 == -1) {
		printf("\nStack Underflow cannot delete");
		return -1;
	} else
		return(ps->items[ps->top1--]);
}

int pop2(struct D_Stack *ps) {
	if(ps->top2 == STACKSIZE) {
		printf("\nStack2 Underflow cannot delete");
		return -1;
	} else
		return(ps->items[ps->top2++]);
}

void show1(struct D_Stack *ps) {
	int i;
	printf("\nElement of Stack from TOP:\n");
	for(i = ps->top1; i >= 0; i--)
		printf("%d \n", ps->items[i]);
}

void show2(struct D_Stack *ps) {
	int i;
	printf("\nElement of Stack 2 from TOP:\n");
	for(i = ps->top2; i < STACKSIZE; i++)
		printf("%d \n", ps->items[i]);
}

int main() {
	int x, ch;
	struct D_Stack s;
	s.top1 = -1;
	s.top2 = STACKSIZE;

	while(1) {
		printf("\n\t\t\t------MENU------");
		printf("\n1. Push 1 \n2. Pop 1 \n3. Push 2 \n4. Pop 2");
		printf("\n5. Empty \n6. Show 1 \n7. Show 2 \n8. Exit");
		scanf("%d", &ch);
		
		switch(ch) {
			case 1:
				printf("\nEnter the element to be pushed: ");
				scanf("%d", &x);
				
				push1(&s, x);
				show1(&s);
				break;

			case 2:
				x = pop1(&s);
				printf("\nElement Popped is %d\n", x);
				
				show1(&s);
				break;

			case 3:
				printf("\nEnter the element to be pushed: ");
				scanf("%d", &x);
				
				push2(&s, x);
				show2(&s);
				break;

			case 4:
				x = pop2(&s);
				printf("\nElement Popped is %d\n", x);
				
				show2(&s);
				break;

			case 5:
				x = empty(&s);
				if(x == 0)
					printf("\nStack is not empty");
				else
					printf("\nStack is empty");
				break;

			case 6:
				show1(&s);
				break;

			case 7:
				show2(&s);
				break;

			case 8:
				exit(1);
		}
	}

	return 0;
}