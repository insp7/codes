#include<stdio.h>
#include<stdlib.h>

#define STACKSIZE 100 // Assuming the max stack size to be 100

struct Stack {
	int top;
	char item[STACKSIZE];
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
 * To pop an element from the stack
 * @param  ps defines the stack pointer
 * @return    the popped character if stack is not empty, else -1
 */
char pop(struct Stack* ps) {
	int temp;

	if(isEmpty(ps)) {
		printf("\nStack underflow cannot pop");
		return -1;
	} else {
		return (ps->item[ps->top--]);
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
 * To print all the elements from the stack
 * @param ps defines the stack pointer
 */
void show(struct Stack* ps) {
	int i;

	printf("\nElement of stack from top:\n");
	for(i=ps->top; i>=0; i--)
		printf("%c\n", ps->item[i]);
}

int main() {
	int ch, i, flag; // by default set flag = 1 which means "unsuccessful expression" 
	char x[STACKSIZE];
	struct Stack s;

	while(1) {
		printf("\n\t\t\t--------MENU-------");
		printf("\n1. Enter Expression \n2. Pop Element fromStack \n3. Show Elements \n4. Exit");
		printf("\nEnter your choice: ");
		scanf("%d", &ch);
		
		switch(ch) {
			case 1:
				// Main code
				s.top = -1; // initially stack is set to empty!
				flag = 0; // initially set flag = 0 to indicate successful Expression
				
				printf("\nEnter the expression:");
				scanf("%s",x);
				
				for(i = 0; x[i] != '\0'; i++) {
					
					// Check for each character
					if(x[i] == '{'|| x[i] == '(' || x[i] == '[') {
						flag++; // indicating unsuccessful expression
						push(&s, x[i]); // push { or ( or [
					} else if(x[i] == '}' && s.item[s.top] == '{' || x[i] == ')' && s.item[s.top] == '(' || x[i] == ']' && s.item[s.top] == '[') {
						pop(&s);
						flag--; //flag = 0 indicates successful Expression
					} else if(x[i] == '}' && (i == 0 || s.top == -1)) { // if the first character entered is itself a closing '}'
						printf("\nError : '{' not found");
						flag++;
					} else if(x[i] == ']' && (i == 0 || s.top == -1)) { // if the first character entered is itself a closing ']'
						printf("\nError : '[' not found");
						flag++;
					} else if(x[i] == ')' && (i == 0 || s.top == -1)) { // if the first character entered is itself a closing ')'
						printf("\nError : '(' not found");
						flag++;
					} else { 

						// if proper match not found, print error
						if(s.item[s.top] == '(' && x[i] != s.item[s.top] && (x[i] == '}' || x[i] == ']')) {
							flag++; // indicating unsuccessful expression
							printf("\nError : Expected %c ; Found %c ", (s.item[s.top] + 1), x[i]);
						} else if(s.top != -1 && (x[i] == ')' || x[i] == ']' || x[i] == '}')) {
							flag++;// indicating unsuccessful expression
							printf("\nError : Expected %c ; found %c ", (s.item[s.top] + 2), x[i]);
						}
					}
				}

				if(flag == 0)
			 		printf("\nSuccessful Expression");
				else if(flag != 0) // Also works if brackets opened are not ever closed 
					printf("\nInvalid Expression");
				
				if(s.top == -1) // to check if all the elements are popped out of the stack
					flag = 0; // if yes , then indicate successful expression 
				break;

			case 2:
				pop(&s);
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

/* 
	********* Ascii Characters **********
	'(' - 40
	')' - 41
	'[' - 91
	']' - 93
	'{' - 123
	'}' - 125
*/