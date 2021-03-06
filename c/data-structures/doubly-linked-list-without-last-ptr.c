#include <stdio.h>
#include <stdlib.h>

/* Doubly Linked List Implementation without a last pointer. */
struct node {
	int data;
	struct node* next;
	struct node* previous;
};

struct node* first = NULL;
struct node* last = NULL;

void createFIFO() {
	char ans;
	struct node* current;
	struct node* new_node;

	do {
		new_node = (struct node*)malloc(sizeof(struct node));

		if(new_node == NULL) {
			printf("Memory Overflow! Cannot create Node");
			exit(1);
		}

		printf("\nEnter the data");
		scanf("%d",&new_node->data);

		if(first == NULL) {
			first = new_node;
			new_node->previous = new_node->next = NULL;
		} else {
			current = first;
			while(current->next != NULL)
				current = current->next;
			current->next = new_node;
			new_node->previous = current;
			new_node->next = NULL; 
		}

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);
	} while(ans == 'Y' || ans == 'y');
}

void createLIFO() {
	char ans;
	struct node* current;
	struct node* new_node;

	do {
		new_node = (struct node*)malloc(sizeof(struct node));

		if(new_node == NULL) {
			printf("Memory Overflow! Cannot create Node");
			exit(1);
		}

		printf("\nEnter the data");
		scanf("%d",&new_node->data);

		if(first == NULL) {
			first = new_node;
			new_node->previous = new_node->next = NULL;
		} else {
			new_node->next = first;
			first->previous = new_node;
			first = new_node;
			new_node->previous = NULL;
		}

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);
	} while(ans == 'Y' || ans == 'y');
}

void traverse() {
	struct node* current;

	if(first == NULL) {
		printf("\nLinked List iz Empty!");
		return;
	}

	current = first;
	while(current != NULL) { 
		printf("%d\t",current->data);
		current = current->next;
	}
}

void destroyList() {
	struct node* current;

	if(first == NULL) {
		printf("Linked List iz Already Empty");
		return;
	}

	current = first;
	while(current != NULL) {
		first = first->next;
		free(current);
		current = first;
	}
}	

void search() {
	struct node* current;
	int x;

	if(first == NULL) {
		printf("Linked List iz empty !");
		return;
	}

	printf("\nEnter the data to be Searched :");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;

	if(current == NULL)
		printf("Specified Data Not Found in the Linked List");
	else
		printf("Specified Data Found in the Linked List");
}

void insertAfter() {
	int x;
	struct node* current;
	struct node* new_node;
	
	if(first == NULL) {
		printf("Linked List iz empty !");
		return;
	}

	new_node = (struct node*)malloc(sizeof(struct node));

	if(new_node == NULL) {
		printf("\n Cannot Create Node ! Memory overflow");
		exit(1);
	}

	printf("\nEnter the data :");
	scanf("%d",&new_node->data);

	printf("\nEnter the data node after which you want to insert:");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;

	if(current == NULL)
		printf("%d not in the Linked List",x);
	else if(current->next == NULL) {
		// to insert after last node
		current->next = new_node;
		new_node->previous = current;
	 	new_node->next = NULL;
	} else {
		new_node->next = current->next;
		new_node->previous = current;
		current->next = new_node;
		new_node->next->previous = new_node;
	}
}

void insertBefore() {
	int x;
	struct node* current;
	struct node* new_node;
	
	if(first == NULL) {
		printf("Linked List iz empty !");
		return;
	}

	new_node = (struct node*)malloc(sizeof(struct node));

	if(new_node == NULL) {
		printf("\n Cannot Create Node ! Memory overflow");
		exit(1);
	}

	printf("\nEnter the data :");
	scanf("%d",&new_node->data);

	printf("\nEnter the data node before which you want to insert:");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;

	if(current == NULL)
		printf("%d not in the Linked List",x);
	else if(current == first) {
		new_node->next = first;
		first->previous = new_node;
		first = new_node;
		new_node->previous = NULL;
	} else {
		new_node->previous = current->previous;
		current->previous->next = new_node;
		new_node->next = current;
		current->previous = new_node;
	}
}

void deleteNode() {
	int x;
	struct node* current;
	
	if(first == NULL) {
		printf("\nLinked List iz already Empty");
		exit(1);
	}

	printf("\nEnter the Data Node to be deleted");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;
	
	if(current == NULL)
		printf("Data Not found in the Linked List");
	else if(current == first && current->next == NULL)
		first = last = NULL;
	else if(current == first) {
		first = first->next;
		first->previous = NULL;
	}
	else if(current->next == NULL) {
		current = current->previous;
		current->next = NULL;
	} else {
		current->previous->next = current->next;
		current->next->previous = current->previous;
	}

	free(current);
	current = NULL;// To Avoid dangling pointer
}

int main() {
	int ch;
	while(1) {
		printf("\n\t\t\t\t------MENU--------");
		printf("\n1.Create LIFO\n2.Create FIFO");
		printf("\n3.Insert Before \n4.Insert After");
		printf("\n5.Search\n6.Delete Node \n7.Traverse \n8.Destroy List \n9.Exit");
		printf("\nEnter your Choice");
		scanf("%d", &ch);

		switch(ch) {
			case 1:
				createLIFO();
				break;

			case 2 :
				createFIFO();
				break;

			case 3:
				insertBefore();
				break;

			case 4:
				insertAfter();
				break;

			case 5:
				search();
				break;

			case 6:
				deleteNode();
				break;

			case 7:
				traverse();
				break;

			case 8:
				destroyList();
				break;

			case 9:
				exit(1); 
		}
	}
}



