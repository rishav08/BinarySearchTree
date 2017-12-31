import java.util.Queue;
import java.util.LinkedList;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    public int nodeCount() {
    	if(isEmpty()) {
    		return 0;
    	}else {
    		return nodeCount(root);
    	}
    		
    }
    
    public boolean isFull() {
    	if(isEmpty()) {
    		return true;
    	}else {
    		return isFull(root);
    	}
    }
    
    public boolean compareStructure(BinarySearchTree<AnyType> t2) {
    	if(this.isEmpty() && t2.root == null) {
    		return true;
		}else if(this.isEmpty() && t2.root != null || !this.isEmpty() && t2.root == null) {
			return false;
		}else {
			return compareStructure(root, t2.root);
		}    	
    }
    
    public boolean equals(BinarySearchTree<AnyType> t2) {
    	if(this.isEmpty() && t2.root == null) {
    		return true;
		}else if((this.isEmpty() && t2.root != null) || (!this.isEmpty() && t2.root == null)) {
			return false;
		}else if(root.element.compareTo(t2.root.element) == 0) {
			return equals(root, t2.root);
		}else {
			return false;
		}
    }
    
    public BinarySearchTree<Integer> copy() {
    	if(isEmpty()) {
    		return null;
    	}else {
    		BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
    		t2.root = copy(root);
    		return t2;
    	}
    }
    
    public BinarySearchTree<Integer> mirror() {
    	if(isEmpty()) {
    		return null;
    	}else {
    		BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
    		t2.root = mirror(root);
    		return t2;
    	}
    }
    
    public boolean isMirror(BinarySearchTree<AnyType> t2) {
    	if(this.isEmpty() && t2.root == null) {
    		return true;
		}else if((this.isEmpty() && t2.root != null) || (!this.isEmpty() && t2.root == null)) {
			return false;
		}else if(root.element.compareTo(t2.root.element) == 0) {
			return isMirror(root, t2.root);
		}else {
			return false;
		}
    }
    
    public void rotateRight(AnyType x) {
    	if( isEmpty( ) ) {
            throw new UnderflowException( );
        }
    	if(this.contains(x)) {
    		rotateRight(x, root);
    	}else {
    		//throw Exception();
    	}
    }
    
    public void rotateLeft(AnyType x) {
    	if( isEmpty( ) ) {
            throw new UnderflowException( );
        }
    	if(this.contains(x)) {
    		rotateLeft(x, root);
    	}else {
    		//throw Exception();
    	}
    }
    
    public void printLevels ( )
    {
        if( isEmpty( ) ) {
            System.out.println( "Empty tree" );
        }else {        	
        	printLevels (root);   	
        }        	
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }
    
    private int nodeCount(BinaryNode<AnyType> t) {
    	if(t != null) {
    		return 1 + nodeCount(t.left) + nodeCount(t.right);  		
    	}else {
    		return 0;
    	}    	
    }
    
    private boolean isFull(BinaryNode<AnyType> t) {
		if(t.left == null && t.right == null) {
			return true;
		}else if(t.left != null && t.right != null) {
			return (isFull(t.left) && isFull(t.right));
		}else {
			return false;
		}
    }
    
    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if(t1.left == null && t2.left == null && t1.right == null && t2.right == null){
    		return true;
    	}else if((t1.left != null && t2.left != null) && (t1.right == null && t2.right == null)) {
    		return(compareStructure(t1.left, t2.left));    		
    	}else if((t1.left == null && t2.left == null) && (t1.right != null && t2.right != null)) {
    		return(compareStructure(t1.right, t2.right));    	
    	}else if((t1.left != null && t2.left != null) && (t1.right != null && t2.right != null)) {
    		return(compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right));    
    	}else {
    		return false;
    	}  	
    }
    
    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	
    	if(t1.left == null && t2.left == null && t1.right == null && t2.right == null){
    		return true;
    	}else if((t1.left != null && t2.left != null) && (t1.left.element.compareTo(t2.left.element) == 0) && (t1.right == null && t2.right == null)) {
    		return(equals(t1.left, t2.left));    		
    	}else if((t1.left == null && t2.left == null) && (t1.right != null && t2.right != null) && (t1.right.element.compareTo(t2.right.element) == 0)) {
    		return(equals(t1.right, t2.right));    	
    	}else if((t1.left != null && t2.left != null) &&  (t1.left.element.compareTo(t2.left.element) == 0) && (t1.right != null && t2.right != null) && (t1.right.element.compareTo(t2.right.element) == 0)) {
    		return(equals(t1.left, t2.left) && equals(t1.right, t2.right));    
    	}else {
    		return false;
    	}  	
    }
    
    private BinaryNode copy(BinaryNode<AnyType> t) {
    	if(t == null) {
    		return null;
    	}
    	BinaryNode temp = new BinaryNode(t.element);
    	temp.left = copy(t.left);
    	temp.right = copy(t.right);
    	return temp;
    	
    }
    
    
    private BinaryNode mirror(BinaryNode<AnyType> t) {
    	if(t == null) {
    		return null;
    	}
    	BinaryNode temp = new BinaryNode(t.element);
    	temp.right = mirror(t.left);
    	temp.left = mirror(t.right);    	
    	return temp;
    }
    
    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	
    	if(t1.left == null && t2.left == null && t1.right == null && t2.right == null){
    		return true;
    	}else if((t1.left != null && t2.right != null) && (t1.left.element.compareTo(t2.right.element) == 0) && (t1.right == null && t2.left == null)) {
    		return(isMirror(t1.left, t2.right));    		
    	}else if((t1.left == null && t2.right == null) && (t1.right != null && t2.left != null) && (t1.right.element.compareTo(t2.left.element) == 0)) {
    		return(isMirror(t1.right, t2.left));    	
    	}else if((t1.left != null && t2.right != null) &&  (t1.left.element.compareTo(t2.right.element) == 0) && (t1.right != null && t2.left != null) && (t1.right.element.compareTo(t2.left.element) == 0)) {
    		return(isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left));    
    	}else {
    		return false;
    	}  	
    }
    
    private void rotateRight(AnyType x, BinaryNode<AnyType> t) {
    	int compareResult = x.compareTo( t.element );
    	if(compareResult == 0) {    		
    		if(t.left == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t;
    			if(t2.left.right == null) {
					t = t2.left;
					t.right = t2;
					t2.left = null;
					root = t;
				}else {
					BinaryNode<AnyType> t3 = t2.left.right;
					t = t2.left;
					t.right = t2;
					t2.left = t3;
					root = t;
				}
    		}
    	}else if( compareResult < 0 ) {
    		if(t.left == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t.left;
    			if(x.compareTo(t2.element) == 0) {
    				if(t2.left == null) {
    					//Through exception
    				}else if(t2.left.right == null) {
    					t.left = t2.left;
    					t.left.right = t2;
    					t2.left = null;
    				}else {
    					BinaryNode<AnyType> t3 = t2.left.right;
    					t.left = t2.left;
    					t.left.right = t2;
    					t2.left = t3;
    				}
    			}else if(x.compareTo(t2.element) < 0 || x.compareTo(t2.element) > 0) {
    				rotateRight( x, t.left );
    			}
    		}        	
    	}else if( compareResult > 0 ) {
    		if(t.right == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t.right;
    			if(x.compareTo(t2.element) == 0) {
    				if(t2.left == null) {
    					//Through exception
    				}else if(t2.left.right == null) {
    					t.right = t2.left;
    					t.right.right = t2;
    					t2.left = null;
    				}else {
    					BinaryNode<AnyType> t3 = t2.left.right;
    					t.right = t2.left;
    					t.right.right = t2;
    					t2.left = t3;
    				}
    			}else if(x.compareTo(t2.element) < 0 || x.compareTo(t2.element) > 0) {
    				rotateRight( x, t.right );
    			}
    		}
    	}
    }
    
    
    private void rotateLeft(AnyType x, BinaryNode<AnyType> t) {
    	int compareResult = x.compareTo( t.element );
    	if(compareResult == 0) {    		
    		if(t.right == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t;
    			if(t2.right.left == null) {
					t = t2.right;
					t.left = t2;
					t2.right = null;
					root = t;
				}else {
					BinaryNode<AnyType> t3 = t2.right.left;
					t = t2.right;
					t.left = t2;
					t2.right = t3;
					root = t;
				}
    		}
    	}else if( compareResult < 0 ) {
    		if(t.left == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t.left;
    			if(x.compareTo(t2.element) == 0) {
    				if(t2.right == null) {
    					//Through exception
    				}else if(t2.right.left == null) {
    					t.left = t2.right;
    					t.left.left = t2;
    					t2.right = null;
    				}else {
    					BinaryNode<AnyType> t3 = t2.right.left;
    					t.left = t2.right;
    					t.left.left = t2;
    					t2.right = t3;
    				}
    			}else if(x.compareTo(t2.element) < 0 || x.compareTo(t2.element) > 0) {
    				rotateLeft( x, t.left );
    			}
    		}        	
    	}else if( compareResult > 0 ) {
    		if(t.right == null) {
    			//Through exception
    		}else {
    			BinaryNode<AnyType> t2 = t.right;
    			if(x.compareTo(t2.element) == 0) {
    				if(t2.right == null) {
    					//Through exception
    				}else if(t2.right.left == null) {
    					t.right = t2.right;
    					t.right.left = t2;
    					t2.right = null;
    				}else {
    					BinaryNode<AnyType> t3 = t2.right.left;
    					t.right = t2.right;
    					t.right.left = t2;
    					t2.right = t3;
    				}
    			}else if(x.compareTo(t2.element) < 0 || x.compareTo(t2.element) > 0) {
    				rotateLeft( x, t.right );
    			}
    		}
    	}
    }
    
    private void printLevels( BinaryNode<AnyType> t)
    {
    	if(t == null) {
    		return;
    	}
    	Queue<BinaryNode> q = new LinkedList<BinaryNode>();
    	q.add(t);
    	while(true) {
    		int QueueSize = q.size();
    		if(QueueSize == 0)
    			break;
    		while(QueueSize > 0) {
    			BinaryNode<AnyType> temp = q.peek();
    			System.out.print(temp.element + " ");
    			q.remove();
    			if(temp.left != null) {
    				q.add(temp.left);
    			}
    			if(temp.right != null) {
    				q.add(temp.right);
    			}
    			QueueSize--;
    		}
    		System.out.println();
    	}        
    }
    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;
    private int count = 0;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
//        final int NUMS = 10;
//        final int GAP  =   37;

//        System.out.println( "Checking... (no more output means success)" );
        
//        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
//            t.insert( i );

//        for( int i = 1; i < NUMS; i+= 2 )
//            t.remove( i );
//
//        if( NUMS < 40 )
//            t.printTree( );
//        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
//            System.out.println( "FindMin or FindMax error!" );
//
//        for( int i = 2; i < NUMS; i+=2 )
//             if( !t.contains( i ) )
//                 System.out.println( "Find error1!" );
//
//        for( int i = 1; i < NUMS; i+=2 )
//        {
//            if( t.contains( i ) )
//                System.out.println( "Find error2!" );
//        }
        int treeElements[] = {65, 13, 16, 52, 28, 11, 20, 14, 87, 50, 26};
//        int treeElements[] = {15,13,14,12,17};
                
        for(int i = 0; i < treeElements.length; i++) {
        	t.insert(treeElements[i]);
        }
        System.out.println("/******************************/");
        System.out.println("A. Solution");
        System.out.print("The original elements inserted to the tree are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.print("Count of the nodes: ");
        System.out.println(t.nodeCount());
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("B. Solution");
        System.out.print("The original elements inserted to the tree are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.print("If the tree is full: ");
        System.out.println(t.isFull());
        System.out.println("/******************************/");
        
        
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        int treeElements2[] = {65, 13, 16, 52, 28, 11, 20, 14, 87, 50, 26};
//        int treeElements2[] = {15,13,14,12,17};
              
		for(int i = 0; i < treeElements2.length; i++) {
			t2.insert(treeElements2[i]);
		}
        System.out.println("/******************************/");
        System.out.println("C. Solution");
        System.out.print("The original elements inserted to the tree 1 are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.print("The original elements inserted to the tree 2 are: ");
        for(int i = 0; i < treeElements2.length; i++) {
        	System.out.print(treeElements2[i] + " ");
        }
        System.out.println("");
        System.out.print("The structure of both the tree is same: ");
        System.out.println(t.compareStructure(t2));
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("D. Solution");
        System.out.print("The original elements inserted to the tree 1 are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.print("The original elements inserted to the tree 2 are: ");
        for(int i = 0; i < treeElements2.length; i++) {
        	System.out.print(treeElements2[i] + " ");
        }
        System.out.println("");
        System.out.print("Both the trees are equal: ");
        System.out.println(t.equals(t2));
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("E. Solution");
        System.out.print("The original elements inserted to the tree are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.println("Copied Tree is: ");
        BinarySearchTree<Integer> objCopy = new BinarySearchTree<>( );
        objCopy = t.copy();
        objCopy.printTree();
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("F. Solution");
        System.out.print("The original elements inserted to the tree are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.println("Mirrored Tree is: ");
        BinarySearchTree<Integer> objMirror = new BinarySearchTree<>( );
        objMirror = t.mirror();
        objMirror.printTree();
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("G. Solution");
        System.out.print("The original elements inserted to the tree 1 are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.print("The original elements inserted to the tree 2 are: ");
        for(int i = 0; i < treeElements2.length; i++) {
        	System.out.print(treeElements2[i] + " ");
        }
        System.out.println("");
        System.out.println("Mirrored of tree 2 is: ");
        BinarySearchTree<Integer> objMirror2 = new BinarySearchTree<>( );
        objMirror2 = t2.mirror();
        objMirror2.printTree();
        System.out.print("The tree 1 is mirror of tree 2: ");
        System.out.println(t.isMirror(objMirror2));
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("H. Solution");
        System.out.println("The original elements inserted to the tree are: ");
        t.printLevels();
        System.out.println("");
        System.out.println("Rotate Right on 65 to the original Tree is: ");
        int testRR = 65;
        t.rotateRight(testRR);
        t.printLevels();
        System.out.println("/******************************/");
        
        System.out.println("/******************************/");
        System.out.println("I. Solution");
        System.out.println("The original elements inserted to the tree are: ");
        t.printLevels();
        System.out.println("");
        System.out.println("Rotate Left on 13 to the original Tree is: ");
        int testRL = 13;
        t.rotateLeft(testRL);
        t.printLevels();
        System.out.println("/******************************/");
        
        
        System.out.println("/******************************/");
        System.out.println("J. Solution");
        System.out.print("The original elements inserted to the tree are: ");
        for(int i = 0; i < treeElements.length; i++) {
        	System.out.print(treeElements[i] + " ");
        }
        System.out.println("");
        System.out.println("Printing tree level-by-level: ");
        t.printLevels ();
        System.out.println("/******************************/");
        
    }
}