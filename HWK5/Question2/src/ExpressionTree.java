import java.util.Scanner;

public class ExpressionTree extends BinaryTree<String>  {
    /**
     * One paramter constructor( can be either pre or post fix )
     * @param expression prefix/postfix expression
     */
    public ExpressionTree(String expression){
        if( expression == null )
            throw new NullPointerException();
        this.root = readBinaryTree(new Scanner(expression)).root;
    }

    /**
     * genering method for reversing an array
     * @param arr array to be reversed
     * @param <T> generic type( works for any array )
     */
    private static <T>void reverse_array(T arr[]){
        for( int i = 0; i<arr.length/2; ++i ){
            T temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
    }

    /**
     * changes the position of each child for the tree give
     * @param tree tree that is to be reflected
     */
    private static void reflect(BinaryTree<String> tree){//reflects evey subtree of this tree along the vertical axis
        if( tree.isLeaf() ){//we can swap now, ending condition
            return;
        }
        else{
            reflect(tree.getLeftSubtree());
            reflect(tree.getRightSubtree());
            Node<String> temp = tree.root.left;
            tree.root.left = tree.root.right;
            tree.root.right = temp;
        }
    }

    /**
     * read binary tree for creating an expression tree
     * @param scan scanner that is very useful here since it is a recursive call and it kinda works like an index here since the .next() method will move the scanner
     * @return returns the binary tree formed
     * @throws NullPointerException when the scanner passed is null
     */
    public static BinaryTree<String>
    readBinaryTree(Scanner scan) {
        if( scan == null )
            throw new NullPointerException();
        if (!(scan.hasNext("\\+") || scan.hasNext("\\*") || scan.hasNext("-")) || scan.hasNext("/") ) {//meaning it is a postfix expression
            String expression = "";
            while (scan.hasNext())//getting it
                expression += scan.next() + " ";
            String str[] = expression.split(" ");
            reverse_array(str);//reversing the array now
            expression = "";
            for( String temp: str )//putting it into the string so that the scanner object could be made
                expression += temp + " ";

            expression = expression.substring(0, expression.length() - 1);//removing the last space just to make sure
            scan = new Scanner(expression);//now scanner holds the postfix in reverse
            BinaryTree<String> tree = readBinaryTreeHelper(scan);//sets the root to the root of the newly created expression tree
            reflect(tree);//change every childs position as they are currently mixed
            return tree;
        } else
            return readBinaryTreeHelper(scan);
    }
    /**
     * read binary tree for creating an expression tree
     * @param scan scanner that is very useful here since it is a recursive call and it kinda works like an index here since the .next() method will move the scanner
     * @return returns the binary tree formed
     */
    private static BinaryTree<String> readBinaryTreeHelper( Scanner scan ){
        String data = scan.next();
        if (data.equals("null"))//when the data is null which is not expected here, it would return null
            return null;
        else if( !isOperator(data.charAt(0)))//if it is an operand here we can return the tree with left and right node set to null since in expression tree all the operands are leaves
            return new BinaryTree<String>(data, null, null);
        else //when it is an operator, we need to figure out the left and the right subtree, scan will naturally keep the last visited operand/operation from the expression string( for postfix additional changes will have to be implemented )
            return new BinaryTree<String>(data, readBinaryTreeHelper(scan), readBinaryTreeHelper(scan) );//natural behaviour for prefix expression, for postfix additional changes would need to be made for this to work

    }
    /**
     * checks if the given character is an operator
     * @param o character to be checked
     * @return works for + - / *
     */
    private static boolean isOperator( char o ){
        switch (o){
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }
    /**
     * @param node current node
     * @param sb string builder tree is attached to as as tring
     */
    private void postOrderTraverse(Node<String> node,
                                  StringBuilder sb) {
        if( node == null )
            return;

        postOrderTraverse(node.left, sb);
        postOrderTraverse(node.right, sb);
        sb.append(node.toString()+" ");

    }

    /**
     * Does a postOrder traversal of the tree
     * @return tree as a string in postorder
     */
    public String toString2() {
        StringBuilder sb = new StringBuilder();
        postOrderTraverse(root, sb);
        return sb.toString();
    }

    /**
     * evaluates the expression tree and returns the result as an integer value
     * @return integer value
     */
    public int eval(){
        return eval(root);
    }
    /**
     * evaluates the expression and returns the result as an
     * integer
     * @return result of the expression tree
     */
    private int eval( Node<String> subtree ){
        if( subtree.left == null && subtree.right == null )//expression tree has either both children or no children
            return Integer.parseInt(subtree.data);
        else{
            int l,r;
            String o;
            l = eval( subtree.left );//left operand
            o = subtree.data;//operator
            r = eval( subtree.right );//right operand
            return evaluate( l,o,r );
        }
    }

    /**
     * private method
     * @param left_operand left operator to be evaluated
     * @param operator operation to be executed between these two
     * @param right_operand right opreator to be evaluted
     * @return the value of the expression passed with these three parameters
     */
    private int evaluate( int left_operand, String operator, int right_operand){
        switch (operator){
            case "+":
                return left_operand + right_operand;
            case "-":
                return left_operand - right_operand;
            case "/":
                return left_operand / right_operand;
            case "*":
                return left_operand * right_operand;
        }
        return 0;//if operator is not valid
    }
}
