/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;


public class SplayTree implements TreeInterface    {
        /**
         * Construct the tree.
         */
        public SplayTree( )
        {
            root = nullNode;
        }

        /**
         * Insert into the tree.
         * @param x the item to insert.
         */
        public void insertar( Association<String,String> x )
        {
            if( newNode == null )
                newNode = new SplayNode( null );
            newNode.valor = x;

            if( root == nullNode )
            {
                newNode.left = newNode.right = nullNode;
                root = newNode;
            }
            else
            {
                root = splay((Comparable) x, root );
                if( x.compareTo((Comparable) root.valor) < 0 )
                {
                    newNode.left = root.left;
                    newNode.right = root;
                    root.left = nullNode;
                    root = newNode;
                }
                else
                if( x.compareTo((Comparable) root.valor) > 0 )
                {
                    newNode.right = root.right;
                    newNode.left = root;
                    root.right = nullNode;
                    root = newNode;
                }
                else
                    return;
            }
            newNode = null;   // So next insert will call new
        }

        /**
         * Remove from the tree.
         * @param x the item to remove.
         */
        public void remove( Comparable x )
        {
            SplayNode newTree;

                // If x is found, it will be at the root
            root = splay( x, root );
            if( root.valor.compareTo( x ) != 0 )
                return;   // Item not found; do nothing

            if( root.left == nullNode )
                newTree = root.right;
            else
            {
                // Find the maximum in the left subtree
                // Splay it to the root; and then attach right child
                newTree = root.left;
                newTree = splay( x, newTree );
                newTree.right = root.right;
            }
            root = newTree;
        }

        /**
         * Find the smallest item in the tree.
         * Not the most efficient implementation (uses two passes), but has correct
         *     amortized behavior.
         * A good alternative is to first call Find with parameter
         *     smaller than any item in the tree, then call findMin.
         * @return the smallest item or null if empty.
         */
        public Comparable findMin( )
        {
            if( isEmpty( ) )
                return null;

            SplayNode ptr = root;

            while( ptr.left != nullNode )
                ptr = ptr.left;

            root = splay((Comparable) ptr.valor.key, root );
            return (Comparable) ptr.valor;
        }

        /**
         * Find the largest item in the tree.
         * Not the most efficient implementation (uses two passes), but has correct
         *     amortized behavior.
         * A good alternative is to first call Find with parameter
         *     larger than any item in the tree, then call findMax.
         * @return the largest item or null if empty.
         */
        public Comparable findMax( )
        {
            if( isEmpty( ) )
                return null;

            SplayNode ptr = root;

            while( ptr.right != nullNode )
                ptr = ptr.right;

            root = splay((Comparable) ptr.valor, root );
            return (Comparable) ptr.valor;
        }

        /**
         * Find an item in the tree.
         * @param x the item to search for.
         * @return the matching item or null if not found.
         */
        public Association<String,String> Buscar(String x)
        {
            root = splay( x, root );

            if( isEmpty( ) || root.valor.compareTo( x ) != 0 )
                return null;

            return root.valor;
        }

        /**
         * Make the tree logically empty.
         */
        public void makeEmpty( )
        {
            root = nullNode;
        }

        /**
         * Test if the tree is logically empty.
         * @return true if empty, false otherwise.
         */
        public boolean isEmpty( )
        {
            return root == nullNode;
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

        /**
         * Internal method to perform a top-down splay.
         * The last accessed node becomes the new root.
         * @param x the target item to splay around.
         * @param t the root of the subtree to splay.
         * @return the subtree after the splay.
         */
        private SplayNode splay( Comparable x, SplayNode t )
        {
            SplayNode leftTreeMax, rightTreeMin;

            header.left = header.right = nullNode;
            leftTreeMax = rightTreeMin = header;

            nullNode.valor = (Association) x;   // Guarantee a match

            for( ; ; )
                if( x.compareTo( t.valor ) < 0 )
                {
                    if( x.compareTo( t.left.valor ) < 0 )
                        t = rotateWithLeftChild( t );
                    if( t.left == nullNode )
                        break;
                    // Link Right
                    rightTreeMin.left = t;
                    rightTreeMin = t;
                    t = t.left;
                }
                else if( x.compareTo( t.valor ) > 0 )
                {
                    if( x.compareTo( t.right.valor ) > 0 )
                        t = rotateWithRightChild( t );
                    if( t.right == nullNode )
                        break;
                    // Link Left
                    leftTreeMax.right = t;
                    leftTreeMax = t;
                    t = t.right;
                }
                else
                    break;

            leftTreeMax.right = t.left;
            rightTreeMin.left = t.right;
            t.left = header.right;
            t.right = header.left;
            return t;
        }

        /**
         * Rotate binary tree node with left child.
         */
        static SplayNode rotateWithLeftChild( SplayNode k2 )
        {
            SplayNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            return k1;
        }

        /**
         * Rotate binary tree node with right child.
         */
        static SplayNode rotateWithRightChild( SplayNode k1 )
        {
            SplayNode k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            return k2;
        }

        /**
         * Internal method to print a subtree in sorted order.
         * WARNING: This is prone to running out of stack space.
         * @param t the node that roots the tree.
         */
        private void printTree( SplayNode t )
        {
            if( t != t.left )
            {
                printTree( t.left );
                System.out.println( t.valor.toString( ) );
                printTree( t.right );
            }
        }


        private SplayNode root;
        private static SplayNode nullNode;
            static         // Static initializer for nullNode
            {
                nullNode = new SplayNode( null );
                nullNode.left = nullNode.right = nullNode;
            }

        private static SplayNode newNode = null;  // Used between different inserts
        private static SplayNode header = new SplayNode( null ); // For splay

    @Override
    public void insertar(NodoRedBlack<Association<String, String>> nodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Buscar(String palabra, NodoRedBlack<Association<String, String>> nodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}