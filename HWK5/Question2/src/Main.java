public class Main {
    public static void main(String[] args) {

        System.out.println("Constructing an expression tree from a prefix expression + + 10 * 5 15 20");
        ExpressionTree exp_tree = new ExpressionTree("+ + 10 * 5 15 20");
        System.out.println("Evaluation of this expression tree yields");
        System.out.println(exp_tree.eval());
        System.out.println("Preorder traversal gives");
        System.out.println(exp_tree);
        System.out.println("Postorder traversal gives:");
        System.out.println(exp_tree.toString2());

        System.out.println("Now creating a new expression tree with a postfix expression 10 5 15 * + 20 +");
        ExpressionTree expTree2 = new ExpressionTree("10 5 15 * + 20 +");
        System.out.println("Evaluation of this expression tree yields");
        System.out.println(expTree2.eval());
        System.out.println("Preorder traversal gives:");
        System.out.println(expTree2);
        System.out.println("Postorder traversal gives:");
        System.out.println(expTree2.toString2());

        System.out.println("Creating a new expression tree from a postfix expression 4 2 + 3 5 1 - * +");
        exp_tree = new ExpressionTree("4 2 + 3 5 1 - * +");
        System.out.println("Evaluation of this expression yields");
        System.out.println(exp_tree.eval());
        System.out.println("Preorder traveresal gives");
        System.out.println(exp_tree);
        System.out.println("Postorder traversal gives");
        System.out.println(exp_tree.toString2());

        System.out.println("Creating a new expression tree from a prefix expression + + 4 2 * 3 - 5 1");
        exp_tree = new ExpressionTree("+ + 4 2 * 3 - 5 1");
        System.out.println("Evaluation of this expression yields");
        System.out.println(exp_tree.eval());
        System.out.println("Preorder traveresal gives");
        System.out.println(exp_tree);
        System.out.println("Postorder traversal gives");
        System.out.println(exp_tree.toString2());

    }
}