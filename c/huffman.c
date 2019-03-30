#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define SIZE 1000

struct priorityQ { // Simple priorityQ implementation using doubly linked list.
	char data[SIZE];
	char huffCode[SIZE];
	int priority; // Priority = frequency of individual character.
	struct priorityQ* next;
	struct priorityQ* previous;
	struct priorityQ* left;
	struct priorityQ* right;
};
 
void countFrequency(char inputString[]);
void displayArray(int arr[], int n);
int isLeaf(struct priorityQ* root);
void generateHuffmanCode(struct priorityQ* root, int arr[], int idx);
void traverseInorder(struct priorityQ* root);
int countNodes();
void createHuffmanTree();
void traverse();
void sortPriority();
void insert(char data, int priority);
struct priorityQ* deQueue();

struct priorityQ* front = NULL;
struct priorityQ* rear = NULL;
int flag = 0; // when flag is set to 1, then queue is empty
int arr[SIZE], idx = 0;

// Function to count frequency of characters
void countFrequency(char inputString[]){
	int i,j,k,frequency = 0;

	for(i=0;inputString[i]!='\0';i++){
		if(inputString[i] == '~')
			continue;

		for(j=i;inputString[j]!='\0';j++){
			if(inputString[j] == inputString[i]){
				frequency++;

				if(j!=i){
					inputString[j]='~';
				}
			}
		}

		// Insert into the priorityQ
		insert(inputString[i], frequency);

		frequency = 0;// initialize frequency again for next characters
		inputString[i]='~';// ~ is overwriting the visited characters in the input string
	}
}

void displayArray(int arr[], int n){
    int i;
    for (i=0;i<n;++i)
        printf("%d", arr[i]);
    printf("\n");
}

int isLeaf(struct priorityQ* root){
	return !(root->left) && !(root->right);
}

// This function code is taken from internet
void generateHuffmanCode(struct priorityQ* root, int arr[], int idx){
    // Assign 0 to left edge and recur
    if (root->left){
        arr[idx] = 0;
        generateHuffmanCode(root->left, arr, idx + 1);
    }
 
    // Assign 1 to right edge and recur
    if (root->right) {
        arr[idx] = 1;
        generateHuffmanCode(root->right, arr, idx + 1);
    }
 
    // If this is a leaf node, then it contains one of the input
    // characters, print the character and its code from arr[]
    if (isLeaf(root)) {
        printf("%s: ", root->data);
        displayArray(arr, idx);
    }
}

void traverseInorder(struct priorityQ* root) {
	if(root != NULL){
		traverseInorder(root->left);
		printf("%s\n",root->data);
		traverseInorder(root->right);
	}
}

int countNodes() {
	int count = 0;
	struct priorityQ* current = front;
	while(current != NULL){
		count++;
		current = current->next;
	}
	return count;
}

void createHuffmanTree(){
	struct priorityQ* tempNode1;
	struct priorityQ* tempNode2;
	struct priorityQ* current;
	struct priorityQ* newNode;
	char tempString[SIZE];

	if(front == NULL){
		printf("Queue is empty!");
		return;
	}
	// Code to pop two lowest frequency nodes
	while(countNodes() > 1){
		newNode = (struct priorityQ*)malloc(sizeof(struct priorityQ));

		tempNode1 = deQueue(); // 1st element popped
		tempNode2 = deQueue(); // 2nd element popped

		// Add their priorites and data, copy them in the created newNode
		newNode->priority = tempNode1->priority + tempNode2->priority;
		strcpy(newNode->data, tempNode1->data);
		strcat(newNode->data, tempNode2->data);

		//Copy newNode's data to tempString for searching
		strcpy(tempString, newNode->data);

		// Add the newNode to the queue again
		if(front == NULL){
			front = newNode;
			newNode->next = newNode->previous = NULL;
		}else{
			newNode->previous = NULL;
			newNode->next = front;
			front->previous = newNode;
			front = newNode;
			/* 	printf("\nbefore sorting :\n");
				traverse();*/
			sortPriority();//Sort Priority again, since newNode is added to Queue	
		}

		/* 	printf("\nTraversing now:\n");
			traverse();//To show how the Queue is right now */

		//Search the currently added element
		current = front;
		while(current != NULL && strcmp(current->data,tempString) != 0) 
			current = current->next;
		if(current == NULL) exit(1);

		// set left and right pointers for the currently added element
		current->left = tempNode1;
		current->right = tempNode2;
	}

	/*  printf("Traversing inorder : \n");
		traverseInorder(front); */
}

void traverse() {
	struct priorityQ* current;
	if(front == NULL){
		printf("Queue is empty\n");
		return;
	}
	current = front;
	while(current != NULL){
		printf("Data = %s \t Priority = %d\n", current->data, current->priority);
		current = current->next;
	}
}

void sortPriority() { // Simple bubble sort
	struct priorityQ* current;
	struct priorityQ* follow;
	int i,j,temp,count,localFlag = 1;
	char tempChar[SIZE];

	count = countNodes(); // get number of nodes

	if(front == NULL){
		printf("\nQueue iz Empty !");
		return;
	}

	for(i=0;i<count-1 && localFlag==1;i++) {
		localFlag = 0;
		current = front;

		for(j=0;j<count-i-1;j++){
			if(current->next != NULL && current->priority > (current->next)->priority){				
				// SWAP FREQUENCIES
				temp = current->priority;
				current->priority = (current->next)->priority;
				(current->next)->priority = temp;

				// SWAP CHARACTERS
				strcpy(tempChar, current->data); //tempChar = current->data;
				strcpy(current->data, (current->next)->data);//current->data = (current->next)->data;
				strcpy((current->next)->data, tempChar);//(current->next)->data = tempChar;

				// To set the swapped element's children 
				current->left = (current->next)->left;
				current->right = (current->next)->right;

				localFlag = 1; 
			}
			current = current->next;
		}
	}
}

void insert(char data, int priority){
	struct priorityQ* new_node = (struct priorityQ*)malloc(sizeof(struct priorityQ));

	fflush(stdin); // probably useless

	new_node->data[0] = data;
	new_node->priority = priority;

	if(front == NULL){//Queue is Empty
		front = rear = new_node;
		new_node->next = new_node->previous = NULL;
	}else{
		rear->next = new_node;
		new_node->previous = rear;
		new_node->next = NULL;
		rear = new_node;
	}

	// setting each node's left and right pointer to NULL, 
	// because each node with single character will become leaf node.
	new_node->left = new_node->right = NULL;
	sortPriority();
}

struct priorityQ* deQueue(){
	struct priorityQ* current;

	if(flag == 1){ 
		printf("The queue is empty !");
		return NULL;
	}

	current = front;

	if(front->next == NULL){// if there is only one node in the queue
		flag = 1;
		front = rear = NULL;
	}else{// more than one node in the queue
		front = front->next;
		current->next = NULL;
		front->previous = NULL;
		sortPriority();// Only included in else, coz when we have only 1 element in Queue, the queue is already prioritized(sorted).
	} 
	return current;
}

int main(){
	int i;
	char inputString[SIZE] = {'\0'}, tempInputString[SIZE] = {'\0'};

	printf("Enter the data:\n");
	scanf("%s",&inputString);
	
	// Print the inputString & create a copy of inputString i.e. tempInputString
	printf("\nYour inputString = %s\n", inputString);
	strcpy(tempInputString, inputString);

	// Count Frequency of characters		
	countFrequency(tempInputString);

	// Function to construct huffman tree
	createHuffmanTree();

	// Function to print huffcodes
	generateHuffmanCode(front, arr, idx);

	return 1;
}

/*  Assuming word "DATASTRUCTURES". 
	Keeping it static right now, hence passing alphabets and their known frequencies as their priorities */
	// insert('E', 1);
	// insert('D', 1);
	// insert('C', 1);
	// insert('T', 3);
	// insert('R', 2);
	// insert('U', 2);
	// insert('A', 2);
	// insert('S', 2);

/** points to remember :  (source - stackoverflow)**/
/*
You can't (usefully) compare strings using != or ==, you need to use strcmp:
Ex. while (strcmp(check,input) != 0)
The reason for this is because != and == will only compare the base addresses of those strings. 
Not the contents of the strings themselves.

Complete Link = https://stackoverflow.com/questions/8004237/how-do-i-properly-compare-strings
 */

// A different way to print huff codes, but yet pending
/* void generateHuffCode(){
	int j,i;
	char srcString[SIZE];
	char search;
	struct priorityQ* current = front;

	for(i=0;i<nodeCount;i++){
		huffCode[1][i] = '~';
	}

	printf("current->data = %s",current->data);

	for(j=0;j<nodeCount;j++){
		current = front;
		search = huffCode[0][j];

		while(current != NULL){
			strcpy(srcString, current->data);

			if(isSubstring(search, (current->left)->data, strlen(srcString))){
				huffCode[1][j] = '0';//strcat(huffCode[1][j], '0');
				current = current->left;
			}else{
				huffCode[1][j] = '1';
				current = current->right;
			}
			printf("In while loop");
		}
		printf("\nhuffCode of %s : ",huffCode[0][j]);
		for(i=0;huffCode[1][i] != '~';i++){
			printf("%s",huffCode[1][i]);
		}
		huffCode[1][i] = '$';
	}
} 
To Do this in an efficient way...
*/
