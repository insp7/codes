/* Simple implementation of Stack using array */
#include<stdio.h>
#include<stdlib.h>

#define STACKSIZE 5 // Assuming 5 is the max-size of the stack.

struct Stack {
	int top;
	int item[STACKSIZE];
};

int isFull(struct Stack* ps);
int isEmpty(struct Stack* ps);
void push(struct Stack* ps, int x);
char pop(struct Stack* ps);
void show(struct Stack* ps);

/**
 * To check if the stack is full.
 * @param  ps defines the stack pointer
 * @return    1 if the stack is full, else 0
 */
int isFull(struct Stack* ps) {
    if(ps->top == STACKSIZE-1)
        return 1;
    else
        return 0;
}

/**
 * To push an element into the stack
 * @param ps defines the stack pointer
 * @param x  defines the element to be pushed on stack
 */
void push(struct Stack* ps, int x) {
	if(isFull(ps)) {
		printf("\nStack overflow cannot push");
		return;
	} else {
		ps->item[++(ps->top)] = x;
	}
}

/**
 * To check if stack is empty
 * @param  ps defines the stack pointer
 * @return    1 if the stack is empty, else 0
 */
int isEmpty(struct Stack* ps) {
	if(ps->top == -1)
		return 1;
	else
		return 0;
}

/**
 * To pop an element from the stack
 * @param  ps defines the stack pointer
 * @return    the popped character if stack is not empty, else -1
 */
int pop(struct Stack* ps) {
	int temp;

	if(isEmpty(ps)) {
		printf("\nStack underflow cannot pop");
		return -1;
	} else {
		return(ps->item[ps->top--]);
	}
}

/**
 * To print all the elements from the stack
 * @param ps defines the stack pointer
 */
void show(struct Stack* ps) {
	int i;

	printf("\nElement of stack from top:\n");
	for(i = ps->top; i >= 0; i--)
		printf("%d ", ps->item[i]);
}

int main() {
	int x, ch;
	struct Stack s;

	s.top = -1; //initially stack is set to empty!
	while(1) {
		printf("\n\t\t\t--------MENU-------");
		printf("\n1. Push \n2. Pop \n3. Show \n4. Exit");
		printf("\nEnter your choice: ");
		scanf("%d", &ch);

		switch(ch) {
			case 1:
				printf("\nEnter the element to be pushed :");
				scanf("%d", &x);

				push(&s, x);
				show(&s);
				break;
			case 2:
				x = pop(&s);
				printf("\nElement popped is %d", x);

				show(&s);
				break;
			case 3:
				show(&s);
				break;
			case 4:
				exit(1);
		}
	}
	return 0;
}
