/* Circular Linked List without using 'last' pointer */

#include <stdio.h>
#include <stdlib.h>

//#define NULL 0; 

struct node* first = NULL; 

struct node {
	int data;
	struct node* next;
};

void createFIFO() { //inserts new elements at the end
	char ans;
	struct node* pnode;
	struct node* current; 

	do {
		pnode = (struct node*)malloc(sizeof(struct node));

		if(pnode == NULL) {
			printf("\nMemory Overflow.Cannot Create node");
			exit(1);
		}

		printf("\nEnter the data :");
		scanf("%d",&pnode->data);

		current = first;
		while(current != NULL && current->next != first)
			current = current->next;

		if(first == NULL && current == NULL) {
			first = current = pnode;
			current->next = first;// or pnode->next = first;
		} else {
			current->next = pnode;
			current = pnode;
			current->next = first;// or pnode->next = first;
		}

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);// ask why can't we use %c when we are inputting only 1 character
	} while(ans == 'Y' || ans == 'y');
}

void createLIFO() {//inserts new elements at the start 
	char ans;
	struct node* pnode;
	struct node* current; 

	do {
		pnode = (struct node*)malloc(sizeof(struct node));
		if(pnode == NULL) {
			printf("\nMemory Overflow.Cannot Create node");
			exit(1);
		}

		printf("\nEnter the data :");
		scanf("%d",&pnode->data);

		current = first;
		while(current != NULL && current->next != first)
			current = current->next;
		if(first == NULL) {
			first = current = pnode;
			current->next = first;// or pnode->next = first;
		} else {
			pnode->next = first;
			first = pnode;
			current->next = first;
			/*******OR CAN BE DONE AS BELOW (ANY ONE WILL WORK)********/ 
			/*pnode->next = first;
			current->next = pnode;
			first = pnode;*/ 
		}

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);// ask why can't we use %c when we are inputting only 1 character
	} while(ans == 'Y' || ans == 'y');
}

void traverse() {
	struct node* current;

	if(first == NULL)
		printf("\nLinked List iz Empty");
	else {
		printf("\n");
		current = first;
		while(current->next != first) { 
			printf("%d\t",current->data);
			current = current->next;
		}

		printf("%d\n",current->data);
	}
}

void search() {
	int x;
	struct node* current;

	if(first == NULL) {
		printf("Linked List iz Empty");
		return;
	}

	current = first;
	printf("\nEnter the value to be searched");
	scanf("%d",&x);

	while(current->next != first) {
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
		current=current->next;

	if(current->data !=x)
		printf("\n Specified node not found");
	else
		printf("\n Specified node found");
}*/

void deleteNode() {
	int x;
	struct node* current;

	if(first == NULL) {
		printf("Linked List iz Empty");
		return;
	}	

	printf("\nEnter the value of the node to be deleted : ");
	scanf("%d",&x);

	current = first;
	if(current == first && current->next == first) {
		first = NULL;
		free(current);
		return;
	}

	while(current->next != first) {
		if(x == first->data) {// To delete first node
			while(current->next != first)
				current = current->next;
			current->next = first->next;
			free(first);
			first = current->next;
			return;
		} else if(x == current->data) {// To delete any middle node
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
	if(current->next == first && x == current->data) {// To delete last node
		struct node* follow; 

		follow = first;
		while(follow->next != current)
			follow = follow->next;
		follow->next = first;
		free(current);
		current = follow;
		return;
	} else
		printf("Specified Node not found");
}		

void deleteLinkedList() {
	struct node* current;
	struct node* follow;

	if(first == NULL) {
		printf("List Already Empty i.e. No Linked List Created");
		return;
	}

	current = first;
	follow = NULL;	
	while(current->next != first) {
		follow = current;
		current = current->next;
		free(follow);
	}

	free(current);
	first = current = NULL;
}

void insertAfter() {
	int x;
	struct node* current;
	struct node* pnode;

	if(first == NULL) {
		printf("Linked List iz empty");
		return;
	}

	pnode = (struct node*)malloc(sizeof(struct node));

	printf("\nEnter the data");
	scanf("%d",&pnode->data);

	printf("\nEnter the data of the node after which u want to insert this data");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->next != first)
		current = current->next;

	if(x == current->data) {
		pnode->next = current->next; 
		current->next = pnode;
		current = pnode;
	} else {
		current = first;
		while(current->data != x)
			current = current->next;
		pnode->next = current->next;
		current->next = pnode;
	}
}

void insertBefore() {
	int x;
	struct node* current;
	struct node* follow;
	struct node* pnode;

	if(first == NULL) {
		printf("Linked List iz empty");
		return;
	}

	pnode = (struct node*)malloc(sizeof(struct node));

	printf("\nEnter the data");
	scanf("%d",&pnode->data);

	printf("\nEnter the data of the node before which u want to insert this data");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->next != first)
		current = current->next;

	if(x == first->data) {
		pnode->next = first;
		first = pnode; 
		current->next = first;
		/*******OR CAN BE DONE AS BELOW (ANY ONE WILL WORK)********/ 
		/*pnode->next = first;
		current->next = pnode;
		first = pnode;*/ 
	} else {
		current = first;
		follow = NULL;

		while(current->data != x) {
			follow = current;
			current = current->next;
		}

		pnode->next = current;// or pnode->next = follow->next;
		follow->next = pnode;
	}
}

int main() {
	int ch;

	while(1) {
		printf("\n<------- Choose An Option ------->");
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
	return 0;
}

/**** Some other Functions  which can be added ****/
/*int isElementExisting(int x) {// Another Search function which returns '1' when element is found in the linked list
	struct node* current;

	if(first == NULL)
		return;
	else {
		current = first;

		while(current->next != first) {
			if(current->data == x)
				return 1; // Element found 
			current = current->next;
		}

		if(current->next == first && current->data == x) // to check the last element of the linked list
			return 1; // Element Found 
		else
			return 0; // Element not found
	}
}*/

/*int isListEmpty() {
	if(first == NULL)
		return 1;//indicates list iz empty
	else
		return 0;
}*/