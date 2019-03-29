#include<stdio.h>
#include<stdlib.h>

/* Simple Linked List Implementation with 'last' pointer.*/
struct node {
	int data;
	struct node* next;
};

struct node* first = NULL;
struct node* last = NULL; // pointer to last node in the linked list

void createLIFO() {
	char ans;
	struct node* new_node;	
	
	do {	
		new_node = (struct node*)malloc(sizeof(struct node));

		if(new_node == NULL) {
			printf("\nMemory Overflow ! cannot create node");
			exit(1);	
		}

		printf("\nEnter the data:");
		scanf("%d",&new_node->data);

		if(first == NULL)
			first = last = new_node;
		else {
			new_node->next = first;
			first = new_node;	
		}

		last->next = NULL;

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);
	} while(ans == 'Y' || ans == 'y');
}

void createFIFO() {
	char ans;
	struct node* new_node;	
	
	do {	
		new_node = (struct node*)malloc(sizeof(struct node));

		if(new_node == NULL) {
			printf("\nMemory Overflow ! cannot create node");
			exit(1);	
		}

		printf("\nEnter the data:");
		scanf("%d",&new_node->data);

		if(first == NULL)
			first = last = new_node;
		else {
			last->next = new_node;
			last = new_node;	
		}

		last->next = NULL;

		printf("\nDo you want to continue?(Y/N)");
		scanf("%s",&ans);
	} while(ans == 'Y' || ans == 'y');
}

void insertAfter() {
	int x;
	struct node* current;
	struct node* new_node;

	if(first == NULL) {
		printf("List iz Empty");
		return;	
	}

	new_node = (struct node*)malloc(sizeof(struct node));
	if(new_node == NULL) {
		printf("\nMemory overflow ! Cannot create node");
		exit(1);	
	}

	printf("Enter the data");
	scanf("%d",&new_node->data);

	printf("\nEnter the data after which you want to insert");
	scanf("%d",&x);	

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;

	if(current == NULL)
		printf("\nNode not found in the linked List");
	else {
		if(current == last) {
			last->next = new_node;
			new_node->next = NULL;
			last = new_node;
		} else {
			new_node->next = current->next;	
			current->next = new_node;	
		}	
	}
}

void insertBefore() {
	int x;
	struct node* current;
	struct node* follow;
	struct node* new_node;
	
	if(first == NULL) {
		printf("List iz Empty");
		return;	
	}
	
	new_node = (struct node*)malloc(sizeof(struct node));

	if(new_node == NULL) {
		printf("\nMemory overflow ! Cannot create node");
		exit(1);	
	}

	printf("\nEnter the data");
	scanf("%d",&new_node->data);

	printf("\nEnter the data before which you want to insert");
	scanf("%d",&x);	

	current = first;
	follow = NULL;
	while(current != NULL && current->data != x) {
		follow = current;		
		current = current->next;
	}

	if(current == NULL)
		printf("\nNode not found in the linked List");
	else {
		if(current == first) {
			new_node->next = first;
			first = new_node;
		} else {
			follow->next = new_node->next;
			follow->next = new_node;	
		}	
	}
}

void deleteNode() {
	int x;
	struct node* current;
	struct node* follow;

	if(first == NULL) {
		printf("\nList iz Empty");
		return;	
	}
	
	printf("\nEnter the node data to be deleted :");
	scanf("%d",&x);

	current = first;
	follow = NULL;
	while(current != NULL && current->data !=x) {
		follow = current;
		current = current->next;
	}

	if(current == NULL)
		printf("\nNode not found in this LinkedList");
	else {
		if(x == first->data)
			first = first->next;
		else if(x == last->data) {
			last = follow;
			last->next = NULL;
		}

		free(current);
	}
}

void destroyList() { 
	struct node* current;

	if(first == NULL){
		printf("\nList is Empty");
		return;
	}

	current = first;
	while(current != NULL) {
		first = first->next;
		free(current);
		current = first;
	}
}

void traverse() {
	struct node* current;

	if(first == NULL) {
		printf("\nList is Empty");
		return;	
	}

	current = first;
	while(current != last) {
		printf("%d\t",current->data);
		current = current->next;
	}

	printf("%d\n",current->data);
}

void search() {
	int x;
	struct node* current;
	
	if(first == NULL) {
		printf("\nList is Empty");
		return;
	}

	printf("\nEnter the data to be searched:");
	scanf("%d",&x);

	current = first;
	while(current != NULL && current->data != x)
		current = current->next;

	if(current == NULL)
		printf("Data not found in this Linked List");
	else
		printf("Data Found in this Linked List");
}

void reverseLinkedList() { 
	int temp;
	struct node* f1 = first; // will start from first
	struct node* l1 = last; // will start from last
	struct node* current;
	
	if(first == NULL) {
		printf("List iz empty");
		return;
	}

	while(f1 != l1) {
		/** Code to swap **/
		temp = f1->data;
		f1->data = l1->data;
		l1->data = temp;

		/* Increment and decrement the pointer respectively */
		f1 = f1->next;
		current = f1;
		while(current->next != l1)
			current = current->next;
		l1 = current;
	}
	printf("The reversed linked list iz :\n");
	traverse();
}

void sortBubble() {
	struct node* current;
	int i, j, temp, count = 0, flag = 1;

	if(first == NULL) {
		printf("List iz Empty !");
		return;
	}

	for(current = first; current != NULL; current = current->next, count++); // to get the number of nodes

	/*current = first;
	while(current != NULL){
		current = current->next;
		count++;
	}*/

	for(i = 0; i < count - 1 && flag == 1; i++) {
		flag = 0;
		current = first;

		for(j=0;j<count-i-1;j++) {

			if(current->next != NULL && current->data > (current->next)->data) {
				temp = current->data;
				current->data = (current->next)->data;
				(current->next)->data = temp;
				flag = 1; 
			}

			current = current->next;
		}
	}

	printf("\nElements in sorted order : ");
	traverse();
}

main() {
	int ch;

	while(1) {
		printf("\n\t\t\t\t------MENU--------");
		printf("\n1.Create LIFO\n2.Create FIFO");
		printf("\n3.Insert Before \n4.Insert After");
		printf("\n5.Search\n6.Delete Node \n7.Traverse \n8.Destroy List \n9.Reverse Linked list \n10.Sort using bubble-sort \n11.Exit");
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
				reverseLinkedList();
				break;

			case 10: 
				sortBubble();
				break;

			case 11:
				exit(1); 
		}
	}
}