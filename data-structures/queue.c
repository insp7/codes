#include <stdio.h>
#include <stdlib.h>

/*Simple Queue Implementation using Linked List*/

struct node {
	int data;
	struct node* next; 
};

struct node* front = NULL;
struct node* back = NULL;

void create() {
	char ans;
	struct node* pnode; 

	do {
		pnode = (struct node*)malloc(sizeof(struct node));

		if(pnode == NULL) {
			printf("\nMemory Overflow.Cannot Create node");
			exit(1);
		}

		printf("\nEnter the data :");
		scanf("%d", &pnode->data);

		if(front == NULL)
			front = back = pnode;
		else {
			back->next = pnode;
			back = pnode;
		}

		pnode->next = NULL;
		printf("\nDo you want to continue?(Y/N)");
		scanf("%s", &ans);
	} while(ans == 'Y' || ans == 'y');
}

void insert() {
	int x;
	struct node* pnode;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode == NULL) {
		printf("Memory Overflow ! Cannot Create node");
		exit(1);
	}

	printf("\nEnter the data");
	scanf("%d", &pnode->data);

	back->next = pnode;
	back = pnode;
	pnode->next = NULL;
}

void delete() {
	struct node* current;

	if(front == NULL)
		printf("Cannot Delete");

	current = front;
	front = front->next;
	free(current);
	current = NULL; 
}

void display() {
	struct node* current;

	if(front == NULL) {
		printf("Queue is Empty!");
		return;
	}

	current = front;
	while(current != NULL && current != back) {
		printf("%d\t", current->data);
		current = current->next;
	}
	printf("%d",current->data);
}

void main() {
	int ch;

	while(1) {
		printf("\n\t\t\t-------MENU-------");
		printf("\n1. Create Queue \n2. Insert Element \n3. Delete Element \n4. Display \n5. Exit");
		printf("\nEnter your choice:\n");
		scanf("%d", &ch);

		switch(ch){
			case 1:
				create();
				break;

			case 2:
				insert();
				break;

			case 3:
				delete();
				break;

			case 4:
				display();
				break;
				
			case 5:
				exit(1);
		}
	}
}