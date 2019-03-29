/* Binary Search Tree - Basic Opeartions (Variable root declared globally) */

#include <stdio.h>
#include <stdlib.h>

#define STACKSIZE 100

/* Assume that the data portion of each node consists of an integer. */
struct node {
	int data ;
	struct node* left; 
	struct node* right ;
};

struct Stack {
	int top;
	struct node* item[STACKSIZE];
};

int isFull(struct Stack* ps) {
    if(ps->top == STACKSIZE-1)
        return 1;
    else
        return 0;
}

void push(struct Stack* ps, struct node x) {
	if(isFull(ps)) {
		printf("\nStack overflow cannot push");
		return;
	} else {
		(ps->top)++;
		ps->item[ps->top] = x;
		//ps->item[++(ps->top)] = x;
	}
}

int isEmpty(struct Stack* ps) {
	if(ps->top == -1)
		return 1;
	else
		return 0;
}

struct node* pop(struct Stack* ps) {
	int temp;

	if(isEmpty(ps)) {
		printf("\nStack underflow cannot pop");
		return NULL;
	} else {
		return(ps->item[ps->top--]);
	}
}

void show(struct Stack* ps) {
	int i;

	printf("\nElement of stack from top:\n");
	for(i = ps->top; i >= 0; i--)
		printf("%d ", ps->item[i]);
}

/* root is a global variable and need not be passed to any function. 
Any changes made to variable root by any of the functions in the program 
will be permanent and will be reflected in the entire program */

struct node *root = NULL ;
int ncount, lcount; // ncount = nodeCount, lcount = leafNodeCount

/* This function will insert a new node as a leaf node in the BST */
void  insert(int  x) {
	struct node *pnode, *current, *follow;

	pnode = (struct node*)malloc(sizeof(struct node));

	if(pnode==NULL)
		printf("\nMemory overflow. Unable to create.") ;
	else {
		pnode->data=x;
	 
		/* A new node is always inserted as leaf node */
		pnode->left = pnode->right = NULL ;
		if(root == NULL) /* Tree is empty */
			root = pnode;
		else {
			/* searching the place of insertion*/
			current = root;
			follow = NULL;
			while(current != NULL) {
				follow = current;
				if(pnode->data < current->data)
					current = current->left;
				else
					current = current->right;
			}

			/* Actual insertion */
			if(pnode->data < follow->data)
				follow->left = pnode;
			else
				follow->right = pnode;
		}
	}	
}

/* This function is responsible for creating a BST 
with multiple nodes */
void  create() {
	int  x, i, n;

	printf("Enter the number of nodes required:\n");
	scanf("%d",&n);

	printf("Enter the data value of each node:\n");
	for(i = 1; i <= n; i++) {
		scanf("%d",&x);
		insert(x) ;
	}
}

/* Binary Tree Traversal methods */
void  preorder(struct  node  *root) {
	if(root!=NULL) {
		/* Visit the root */
		printf("%d ", root->data);
		/* Traverse the left subtree in preorder */
		preorder(root->left);
		/* Traverse the right subtree in preorder */
		preorder(root->right);
	}
}

void  inorder(struct  node  *root) {
	if(root!=NULL) {
		inorder(root->left);
		printf("%d ", root->data);
		inorder(root->right);
	}
}
void  postorder(struct  node  *root) {
	if(root!=NULL) {
		postorder(root->left);
		postorder(root->right);
		printf("%d ", root->data);
	}
}

/*
This function will delete a node with value k from BST.
The following are the four cases of deleting a node from BST
1)
The node to be deleted has no children i.e. 
it is a leaf node.
This is the easiest case in which all we need 
to do is set the deleted node's parent to NULL.
2)
The node to be deleted has only a right subtree.
In this case we attach this right subtree to the 
parent of the deleted node.
3)
The node to be deleted has only a left subtree.
In this case we attach this left subtree to the 
parent of the deleted node.
4)
The node to be deleted has both subtrees.
This case is the most difficult one to deal with.
In this case the node's position is occupied by
"Inorder successor of that node".
*/

void  deletenode(int  k) {
	struct node *current, *follow, *t, *f;

	/* Searching the node to be deleted */
	current = root;
	follow = NULL;
	while(current != NULL && current->data != k) {
		follow = current;

		if(k < current->data)      
			current = current->left;
		else
			current = current->right;
	}

	if(current == NULL)
		printf("Required node not found. \n") ;
	else {
		/* Actual Deletion */	
		/*Case 1: 
		Deleting a leaf node 
		or a node with empty left subtree */
		if(current->left == NULL)
			if(current != root)
				if(follow->left == current)
					follow->left = current->right;
				else
					follow->right = current->right;
			else
				root = current->right;

		/*Case 2: 
		Deleting node with empty right subtree */
		else if(current->right == NULL)
			if(current != root)
				if(follow->left == current)
					follow->left = current->left;
				else
					follow->right = current->left;
			else
				root = current->left;

		/*Case 3: 
		Deleting a node with non-empty left and right subtrees */
		else {
			/* finding the inorder successor of current, that is, 
			finding the smallest node in right sub tree of node to be deleted */
			t = current->right; 
			f = current;
			while(t->left != NULL) { 
				f = t; 
				t = t->left;
			}
			/* At this stage t is the inorder successor of current 
			and f is the father of t */
			/* put t's data in place of current */
			current->data = t->data ;
			
			/*Set the inorder successor's right subtree to
			the position of in-order successor*/
			if(f != current)
				f->left = t->right;
			else
				f->right = t->right;
	
			current = t; 
			/* Useful for free(current) */
		}
		free(current);
	}
}

/* This fn will search a node with value k in BST */
void  search(int  k) {
	struct node *current;

	/* Searching the required node */
	current = root;
	while(current != NULL && current->data == k) {
		
		if(k < current->data)
			current=current->left;
		else
			current=current->right;
	}
	if(current == NULL)
		printf("Required node not found. \n");
	else
		printf("Node found at address %X. \n",current);
}

/* This function will find level of a node with value k */
void  levelnode(int  k) {
	int  level = 0;
	struct  node  *current;

	/* Finding level of the node */
	current = root;
	while(current != NULL && current->data == k) {
    
		level++ ;
		if(k < current->data)
			current = current->left;
		else
			current = current->right;
	}
	
	if(current == NULL)
		printf("Required node not found. \n");
	else
		printf("Required node found at level %d. \n",level);
}

/* This function will count the total no. of nodes in the BST */
void  nodecount(struct  node  *root) {
	if(root != NULL) {
		nodecount(root->left);
		++ncount; 
		nodecount(root->right);
	}
}

/* This function will count the total no. of leaf nodes in the BST. Moreover, 
it will also display data value of leaf nodes. */
void  leafnodecount(struct  node  *root) {
	if(root != NULL) {
		leafnodecount(root->left);
		if(root->left == NULL && root->right == NULL) {
			++lcount;
			printf("%d ",root->data);
		}
		leafnodecount(root->right);
	}
}

/*  This function will find the smallest value in the BST */
void findsmallest() {
	struct node *current;

	if(root == NULL)
		printf("Tree is empty. \n");
	else {
		current = root;
		while(current->left != NULL)
			current=current->left;
		printf("Smallest node's data is %d. \n",current->data);
	}
}

/*  This function will find the largest value in the BST */
void findlargest() {
	struct node *current;

	if(root == NULL)
		printf("Tree is empty. \n");
	else {
		current = root;
		while(current->right != NULL)
			current = current->right;
		printf("Largest node's data is %d. \n",current->data);
	}
}

void inorderNR(struct node* root) {// non-recursive
	struct Stack s;
	struct node* current;
	s.top = -1;

	while(root) {
		s.push(&s,root);
		root = root->left;
	}

	while(!s.isEmpty()) {
		current = s.pop();
		printf("%d",current->data);
		current = current->right;

		while(current){
			s.push(&s,current);
			current = current->left;
		}
	}
}

void main() {
	int x, k, ch;

	do {
		printf("1.Create. \n");
		printf("2.Insert. \n");
		printf("3.Preorder. \n");
		printf("4.Inorder. \n");
		printf("5.Postorder. \n");
		printf("6.Delete Node. \n");
		printf("7.Search \n");
		printf("8.Level Node. \n");
		printf("9.Node Count. \n");
		printf("10.Leaf Count. \n");
		printf("11.Find smallest\n");
		printf("12.Find Largest\n");
		printf("13.Exit\n");
		printf("14.non-recursive inorder");

		printf("Enter your choice: ");
		scanf("%d",&ch);

		switch(ch) {
			case 1:
				create();
				break;

			case 2:
				printf("Enter the data value of new node: ");
				scanf("%d",&x);
				insert(x);
				break;

			case 3:
				preorder(root);
				printf("\n");
				break;

			case 4:
				inorder(root);
				printf("\n");
				break;

			case 5:
				postorder(root);
				printf("\n");
				break;

			case 6:
				printf("Enter data of the node to be deleted: ");
				scanf("%d",&k);
				deletenode(k);
				break;

			case 7:
				printf("Enter data of the node to be searched: ");
				scanf("%d",&k);
				search(k);
				break;

			case 8:
				printf("Enter data of the node whose level is to be found: ");
				scanf("%d",&k);
				levelnode(k);
				break;

			case 9:
				ncount = 0;
				nodecount(root);
				printf("No. of nodes present in the tree are %d \n", ncount);
				break;

			case 10:
				lcount = 0;
				leafnodecount(root);
				printf("No. of Leaf nodes present in the tree are %d \n", lcount);
				break;

			case 11:
				findsmallest();
				break;

			case 12:
				findlargest();
				break;

			case 14:
				inorderNR(root);
				break;
		}
	} while(ch!=13);
}









