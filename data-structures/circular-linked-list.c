/* Circular Linked List with using 'last' pointer */

#include <stdio.h>
#include <stdlib.h>

// #define NULL 0;
 
struct node {
	int data;
	struct node* next;
};

struct node* first = NULL;
struct node* last = NULL;

void createFIFO() { //inserts new elements at the end
	char ans;
	struct node* pnode; 

	do {
		pnode = (struct node*)malloc(sizeof(struct node));

		if(pnode == NULL) {
			printf("\nMemory Overflow.Cannot Create node");
			exit(1);
		}

		printf("\nEnter the data :");
		scanf("%d",&pnode->data);

		if(first == NULL)
			first = last = pnode;
		else {
			last->next = pnode;
			last = pnode;
		}

		last->next = first;// or pnode->next = first;

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);// ask why can't we use %c when we are inputting only 1 character
	} while(ans == 'Y' || ans == 'y');
}

void createLIFO() {//inserts new elements at the start 
	char ans;
	struct node* pnode; 

	do {
		pnode = (struct node*)malloc(sizeof(struct node));

		if(pnode == NULL){
			printf("\nMemory Overflow.Cannot Create node");
			exit(1);
		}

		printf("\nEnter the data :");
		scanf("%d",&pnode->data);

		if(first == NULL && last == NULL)
			first = last = pnode;
		else {
			pnode->next = first;
			first = pnode;
			/*******OR CAN BE DONE AS BELOW (ANY ONE WILL WORK)********/ 
			/*pnode->next = first;
			last->next = pnode;
			first = pnode;*/ 
		}

		last->next = first; // or pnode->next = first;

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans); // google later why can't we use %c when we are inputting only 1 character
	} while(ans == 'Y' || ans == 'y');
}

void traverse() {
	struct node* current;

	if(first == NULL)
		printf("\nLinkedList iz Empty");
	else {
		current = first;

		while(current != last) { //or while(current != last)
			printf("%d\t",current->data);
			current = current->next;
		}

		printf("%d\n",current->data);
	}
}

void search() {
	int x;
	struct node* current;

	current = first;
	printf("\nEnter the value to be searched");
	scanf("%d",&x);

	while(current != last) {
		if(current->data == x) {
			printf("\nElement Found in the LinkedList");
			return;
		}

		current = current->next;
	}

	if(current->data == x)
		printf("\nElement Found in LinkedList");
	else
		printf("\nElement does not exist in LinkedList");
}

/* Alternate Way : 
void search() {
	int x;
	struct node *current;

	printf("\n Enter the data to be searched");
	scanf("%d",&x);

	while(current != last && current->data != x)
		current = current->next;

	if(current->data != x)
		printf("\n Specified node not found");
	else
		printf("\n Specified node found");
}*/

void deleteNode() {
	int x;
	struct node* current;	

	printf("\nEnter the value of the node to be deleted : ");
	scanf("%d",&x);

	current = first;
	if(current == first && current == last) {
		first = last = NULL;
		free(current);
		return;
	}

	while(current != last) {
	 	if(x == first->data){ // if u want to delete first node
			last->next = first->next;
			first = first->next;
			// OR 
			/*first = first->next;
			last->next = first;*/
			free(current);
			return;
		} else if(x == last->data) { // if u want to delete last node
			struct node* follow;

			follow = first;
			while(follow->next != last)
				follow = follow->next;

			follow->next = first;
			free(last);
			last = follow;
			return;
		} else if(x == current->data) {
			struct node* follow;

			follow = first;
			while(follow->next != current)
				follow = follow->next;

			follow->next = current->next;
			free(current);
			return;
		}

		current = current->next;
	}

	if(current == last)
		printf("Specified Node not found");
}		

void deleteLinkedList() {
	struct node* current;
	struct node* follow;

	current = first;
	follow = NULL;	
	while(current != last) {
		follow = current;
		current = current->next;
		free(follow);
	}

	free(current);
	first = last = NULL;
}
/***** Alternate way *****/
/*void destroy() {
	struct node *current;

	if(first == NULL)
		printf("\n List is empty");
	else {
		while(first != NULL) {
			current = first;
			first = first->next;
			last->next = first;
			free(current);
		}
	}
}*/

void insertAfter() {
	int x;
	struct node* current;
	struct node* pnode;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode == NULL) {
		printf("Memory Overflow ! Cannot Create node");
		exit(1);
	}

	printf("\nEnter the data");
	scanf("%d",&pnode->data);

	printf("\nEnter the data of the node after which u want to insert this data");
	scanf("%d",&x);

	if(x == last->data) {
		pnode->next = last->next; // or pnode->next = last->next;
		last->next = pnode;
		last = pnode;
		// OR
		/*last->next = pnode;
		last = pnode;
		last->next = first;*/
	} else {
		current = first;
		while(current->data != x && current != last)
			current = current->next;

		if(current->data != x) {
			printf("Specified node not found");
			return;
		}

		pnode->next = current->next;
		current->next = pnode;
	}
}

void insertBefore() {
	int x;
	struct node* current;
	struct node* follow;
	struct node* pnode;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode == NULL) {
		printf("\nMemory Overflow ! Cannot create node");
		exit(1);
	}

	printf("\nEnter the data");
	scanf("%d",&pnode->data);

	printf("\nEnter the data of the node before which u want to insert this data");
	scanf("%d",&x);

	if(x == first->data) {
		pnode->next = first;
		first = pnode; 
		last->next = first;
		/*******OR CAN BE DONE AS BELOW (ANY ONE WILL WORK)********/ 
		/*pnode->next = first;
		last->next = pnode;
		first = pnode;*/ 
	} else {
		current = first;
		follow = NULL;
		while(current->data != x && current != last) {
			follow = current;
			current = current->next;
		}

		if(current->data != x) {
			printf("Specified node not found");
			return;
		}

		pnode->next = current;// or pnode->next = follow->next;
		follow->next = pnode;
	}	
}

int main() {
	int ch;

	while(1) {
		printf("\n********Choose An Option********");
		printf("\n1.createLIFO\n2.createFIFO\n3.insertAfter\n4.insertBefore\n");
		printf("5.deleteNode\n6.traverse\n7.search\n8.deleteLinkedList\n9.exit\n");
		scanf("%d",&ch);

		switch(ch) {
			case 1 :createLIFO();
					break;

			case 2 :createFIFO();
					break;

			case 3 :insertAfter();
					break;

			case 4 :insertBefore();
					break;

			case 5 :deleteNode();
					break;

			case 6 :traverse();
					break;

			case 7 :search();
					break;

			case 8 :deleteLinkedList();
					break;

			case 9 :exit(1);
					break;
		}
	}		
	return 0; // unnecessary statement, remove later
}
