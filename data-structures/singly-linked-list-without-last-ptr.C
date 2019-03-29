#include<stdio.h>
#include<stdlib.h>

//#define null 0

/* Simple Linked List implementation without using 'last' pointe */

/*Assuming the data portion of the node consists of single
integer*/
struct node {
	int data;
	struct node* next;
};



/*first is a global variable and need not be passed
to any function in the program. Moreover any changes made to 
variable 'first' by any function will be reflected in the 
entire program*/
struct node* first = NULL;


/*The below function will create the linked list
right from the beginning. It will create the linked list as a 
LIFO data structure.*/
void createLIFO() {
	char ch;
	struct node* pnode;// acts as a pointer object for structure "node"

	do {
		pnode = (struct node*)malloc(sizeof(struct node));

		if(pnode == NULL) {
			printf("\nMemory Overflow cannot create node");
			exit(1);
		} else {
			printf("\nEnter the data to be inserted:\n");
			scanf("%d", &pnode->data);
			pnode->next = first;
			first = pnode;
			printf("\nDo you want to create another node? (y | n): ");
			scanf("%s", &ch);
		}
	} while (ch == 'Y' || ch == 'y');
}

/*The below function will create the linked list
right from the beginning. It will create the linked list as a 
FIFO data structure.*/
void createFIFO() {
	char ch;
	struct node *pnode, *current, *follow;

	do {
		pnode = (struct node *)malloc(sizeof(struct node));

		if(pnode==NULL) {
			printf("\nMemory overfloaw, cannot create node");
			exit(1);
		} else {
			printf("\nEnter the data to be inserted:\n");
			scanf("%d", &pnode->data);

			pnode->next = NULL;
			if(first == NULL)
				first = pnode;
			else {
				follow = NULL;
				current = first;

				while(current!=NULL) {
					follow = current;
					current = current->next;
				}
				follow->next = pnode;
			}
			printf("\nDo you want to create another node? (y | n): ");
			scanf("%s", &ch);
		}
	} while (ch == 'Y' || ch == 'y');
}

/*The below function will insert a new node after a specified 
node in an already existing linked list*/
void insertafter() {
	int x;
	struct node *pnode, *current;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode == NULL) {
		printf("\nMemory Overflow cannot create node");
		exit(1);
	} else {
		printf("\nEnter the data to be inserted:\n");
		scanf("%d", &pnode->data);

		printf("\nEnter the data value after which the new node has to be inserted: ");
		scanf("%d", &x);

		/*Searching the required Node */
		current = first;
		while(current !=NULL && current->data != x)
			current = current->next;

		if(current == NULL)
			printf("\nSpecified node not found");
		else {
			pnode->next = current->next;
			current->next = pnode;
		}
	}
}

void insertbefore() {
	int x;
	struct node *pnode, *current, *follow;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode == NULL) {
		printf("\nMemory Overflow cannot create node");
		exit(1);
	}
	else {
		printf("\nEnter the data to be inserted:\n");
		scanf("%d", &pnode->data);

		printf("\nEnter the data value before which the new node has to be inserted: ");
		scanf("%d", &x);

		// searching the required node
		current = first;
		follow = NULL;
		while(current->data != x && current != NULL) {
			follow = current;
			current = current->next;
		}

		if(current == NULL)
			printf("\nSpecified node not found");
		else if(current == first) {
			pnode->next = first;
			first = pnode;
		} else {
			pnode->next = current;
			follow->next = pnode;
		}
	}
}

void deletenode() {
	int x;
	struct node *pnode, *current, *follow;

	printf("\nEnter the data value of the node has to be deleted: ");
	scanf("%d", &x);

	current = first;
	follow = NULL;
	while(current->data != x && current != NULL) {
		follow = current;
		current = current->next;
	}

	if(current == NULL)
		printf("\nSpecified Node not found");
	else {
		if(current == first)
			first = first->next;
		else
			follow->next = current->next;
		free(current);
	}
}
void search() {
	int x;
	struct node *current;

	printf("\nEnter the data value of the node to be Searched: ");
	scanf("%d", &x);

	current = first;
	while(current->data != x && current !=NULL)
			current = current->next;

	if(current == NULL)
		printf("\nSpecified node not found");
	else
		printf("\nSpecified node found");
}
void traverse() {
	struct node *current;

	current = first;
	if(first==NULL)
		printf("\nList is empty");
	else {
		while(current != NULL) {
			printf("%d\t", current->data);
			current = current->next;
		}
	}
}
void destroylist() {
	struct node  *current;

	while(first != NULL) {
		current = first;
		first = first -> next;
		free(current);
	}
}
int main() {
	int ch;

	while(1) {
		printf("\n\t\t\t\t------MENU--------");
		printf("\n1.Create \n2.Insert After Node \n");
		printf("3.Insert Before node \n4. Delete Node \n");
		printf("5.Search \n6.Traverse \n7.Destroy \n8.Exit");
		printf("\nEnter your Choice");
		scanf("%d", &ch);

		switch(ch) {
			case 1:
				createLIFO();
				break;

			case 2 :
				insertafter();
				break;

			case 3:
				insertbefore();
				break;

			case 4:
				deletenode();
				break;

			case 5:
				search();
				break;

			case 6:
				traverse();
				break;

			case 7:
				destroylist();
				break;
				
			case 8:
				exit(1);
		}
	}
}