import java.util.Stack;

import static java.lang.Double.parseDouble;

public class Q3 {
    /**
     * Method is used for reversing a string
     * @param str String to be reversed
     * @return returns the string that is reversed
     */
    public static String reverse( String str ){
        return reverse2(str, 0);
    }

    /**
     * Helper function used for reversing the string. Used position of the string instead of a substring since producing substring has a linear execution time and
     * i am not sure if it is using a loop inside
     * @param str string to breversed
     * @param start current position of the string
     * @return reversed string
     */
    public static String reverse2( String str, int start ){
        if( start == str.length() )//base case when we readched the end of our string( could use substring and check if our substring is reached aso
            return  "";
        String res = reverse2(str, start+1);
        if( str.charAt(start) == ' ' )//when there is space it means word will follow
            res += subStr( str, start + 1) + " ";
        else if( start == 0)//the same as for space just the call for getting substring is different
            res += subStr( str, start );
        return res;
    }

    /**
     * Helper function used in a couple of methods implemented here to get first word of a string using recursion
     * @param str string from which we are getting the first word
     * @param start
     * @return
     */
    public static String subStr( String str, int start ){
        if( start == str.length() || str.charAt(start) == ' ' )//base condition to( either reached a space or the end of the string )
            return "";
        return  str.charAt(start) + subStr(str, start + 1);
    }


    /**
     * method that checks if the word is elfish using recursion
     * @param word string ot be checked for this
     * @return boolean vaue for the word that is checked
     */
    public static boolean isElfish( String word ){
        return isElfish( word, 0,  false, false, false );
    }

    /**
     * Helper funciton for checking the elfish function
     * @param word word to be checked
     * @param start position in the work that is being checked( not using the substring for the same reason )
     * @param e boolean value for e( true if e has been found, false otherwise )
     * @param l boolean value for l( true if e has been found, false otherwise )
     * @param f boolean value for f( true if e has been found, false otherwise )
     * @return true if the word is elfish and false other wise( all the boolean paramteres need to be true for the output to be true )
     */
    public static boolean isElfish( String word, int start, boolean e, boolean l, boolean f ){
        if( start == word.length() )
            return e && l && f;
        if( word.charAt(start) == 'e' )
            e = true;
        if( word.charAt(start) == 'l' )
            l = true;
        if( word.charAt(start) == 'f' )
            f = true;
        return isElfish( word, start+1, e, l, f );
    }


    /**
     * sorting using recursion
     * @param arr array to be sorted
     */
    public static void sort_rec( int[] arr ){
        sort_rec( arr, 0 );//calling it with the starting index
    }

    /**
     * Helper functoin for sorting recursevly
     * @param arr array to be sorted
     * @param i positon at which the array is currently
     */
    public static void sort_rec( int[] arr, int i ){
        if( i == arr.length )//base case appears when the end of the array is reached
            return;
        placeMin( arr, i, i+1, i );//helper function that finds and places the minimum element in our array
        sort_rec(arr, i+1);//recursive call
    }

    /**
     * Helper function for recursivly sorting
     * @param arr array to be sorted
     * @param i index it is currently at
     * @param j index of subarray in which the minimal element is to be found and placed in its right place
     * @param minI index of the minimum element in the array
     */
    public static void placeMin(int[] arr, int i, int j, int minI ){
        if( j >= arr.length ){//base case is reached whent he end of the subarray is reached
            int t = arr[i];
            arr[i] = arr[minI];
            arr[minI] = t;
            return;
        }
        if( arr[minI] > arr[j] )//minI and i start with the same value
            minI = j;
        placeMin(arr, i, j+1, minI );//recursive call
    }


    /**
     * Postfix expression evauation
     * @param expr String of our postfix expresion( every operator and operation are separated with a space here
     * @return returns the result as a double value since division and mutltiplication are allowed
     */
    public static double evaluatePostfixRec( String expr ){
        return evaluatePostfixRec( expr, new Stack<Double>(), 0 );
    }

    /**
     * Helper function for our postfix evauation
     * @param expr String ( postfix expression )
     * @param stack stack which manipuates operations
     * @param i position in the string we are currently at( again used this instead of substring for the same reason as in the previous examples )
     * @return the result of Postfix expresion we are evaluating
     */
    public static double evaluatePostfixRec( String expr, Stack<Double> stack, int i ){
        if( i == expr.length() )//base condition that will
            return stack.pop();//will be left with only one element inside in the end
        if( i == 0 || expr.charAt(i) == ' ' ){//when at the begining or when the space is reached new operator or operand need to be proccessed
            String next;
            if( i==0 )
                next = subStr(expr,i);//the same method useed as a helper recursive method for the first part
            else
                next = subStr(expr, i+1);//the same method useed as a helper recursive method for the first part
            if( isOperation(next) ){//helper method used for checking the string is a supporting operation
                double second_operand = stack.pop();
                double first_operand = stack.pop();
                stack.push( evaluate(first_operand, second_operand, next ) );//next is operation here, it is checked beforehand
            }
            else
                stack.push(parseDouble(next));
        }
        return evaluatePostfixRec( expr, stack, i+1 );
    }

    /**
     * Evaluating prefix expression( almost th esame as for the postfix one with difference in the way the string is reverse and the was stack's operands are pushed and popped
     * @param expr prefix expression as a string
     * @return the value of the expression
     */
    public static double evaluatePrefixRec( String expr ){
        return evaluatePrefixRec( expr, new Stack<Double>(), expr.length()-1 );
    }

    /**
     * helper method which is pretty similar to the one we used as a helper for postfix evaluation
     * @param expr prefix expression
     * @param stack stack used for evaluating
     * @param i position in the string we are currently at
     * @return evaluated value of the prefix expression
     */
    public static double evaluatePrefixRec( String expr, Stack<Double> stack, int i ){
        if( i == -1 )
            return stack.pop();//will be left with only one element inside in the end
        if( i == 0 || expr.charAt(i) == ' ' ){
            String next;
            if( i==0 )
                next = subStr(expr,i);
            else
                next = subStr(expr, i+1);
            if( isOperation(next) ){
                double first_operand = stack.pop();
                double second_operand = stack.pop();
                stack.push( evaluate(first_operand, second_operand, next ) );//next is operation here, it is checked beforehand
            }
            else
                stack.push(parseDouble(next));
        }
        return evaluatePrefixRec( expr, stack, i-1 );
    }

    /**
     * Used for both prefix and postfix recursive methods. This is a method with constant complexity that evaluates
     * two operators and an operation
     * @param first_operand left value
     * @param second_operand right value
     * @param operation binary operation to be performed
     * @return returns the evaluated expression
     */
    public static double evaluate( double first_operand, double second_operand, String operation ){
        double res = 0.0;
        switch( operation ){
            case "+":
                res = first_operand + second_operand;
                break;
            case "-":
                res = first_operand - second_operand;
                break;
            case "/":
                res = first_operand / second_operand;
                break;
            case "*":
                res = first_operand * second_operand;
                break;
        }
        return res;
    }

    /**
     * constant operation which just checks if the string passed is an opeartion or not
     * @param operation
     * @return
     */
    public static boolean isOperation( String operation ){
        return operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*");
    }

    /**
     * Operation used to print a 2d array in clockwise form
     * @param arr array to be printed
     */
    public static void printRec( int[][] arr ){
        printRec( arr, 0, arr[0].length-1, 0, arr.length-1, 0, 0, true, false, false ,false );
    }

    /**
     * Helper function for printing a 2d array
     * @param arr array to be print
     * @param l_l left limit( left bound )
     * @param r_l right limit( right bound )
     * @param u_l upper limit( upper bound )
     * @param d_l down limit( down bound )
     * @param i current row position
     * @param j current column position
     * @param r boolean ( right direction - moving )
     * @param d boolean ( down direction - moving )
     * @param l boolean ( left direction - moving )
     * @param u boolean ( up direction - moving )
     */
    public static void printRec( int[][] arr, int l_l, int r_l, int u_l, int d_l, int i, int j, boolean r, boolean d, boolean l, boolean u ){

        if( i>=u_l && i<=d_l && j>=l_l && j<=r_l )//base case( when row and columns position is out of bounds of our 2d subarray
            System.out.print(arr[i][j] + " ");
        else
            return;

        if( r ) {//when moving in right direciton
            ++j;//incrementing the column value
            if( j>r_l ){//when out of bounds
                --j;
                ++i;
                if( j != arr[0].length-1 )
                    ++l_l;
                r = false;
                d = true;
            }
        }
        else if( d ) {//when moving in down direction
            ++i;//moving the row
            if( i>d_l ){//if out of bounds here updating it
                --i;
                --j;
                ++u_l;
                l = true;
                d = false;
            }
        }
        else if( l ) {//when  moving in left direction
            --j;//reducing th column position
            if( j < l_l ){
                ++j;
                --i;
                --r_l;
                l = false;
                u = true;
            }
        }
        else if( u ) {//when moving in up direction
            --i;//reducing row position
            if( i<u_l ){//when out of bounds here updating it
                ++j;
                ++i;
                --d_l;
                u = false;
                r = true;
            }
        }
        printRec(arr, l_l, r_l, u_l, d_l, i, j, r, d, l, u);
    }
    public static void main(String[] args) {
        System.out.println("Testing first method");
        System.out.println("reversed of 'this function writes the sentence in reverse': \n" + reverse("this function writes the sentence in reverse"));
        System.out.println("Checking elfish function with whiteleaf tasteful unfriendly waffles:");
        if( isElfish("whiteleaf") && isElfish("tasteful") && isElfish("unfriendly") && isElfish("waffles") )
            System.out.println("whiteleaf tasteful unfriendly waffles are elfish " );
        else
            System.out.println("whiteleaf tasteful unfriendly waffles are not elfish");
        System.out.println("Testing if using djurol");
        if( isElfish("djurol") )
            System.out.println("djurol is elfish");
        else
            System.out.println("djurol is not elfish");
        System.out.println("Sorting an array '6,5,3,7,1' ");
        int arr[] = {6,5,3,7,1};
        sort_rec(arr);
        System.out.println("Sorted array is:");
        for( int a:arr )
             System.out.print(a + " ");
        System.out.println();
        System.out.println("Evaluating posfix of '2 3 * 5 4 * + 15 -': " + evaluatePostfixRec("2 3 * 5 4 * + 15 -") );
        System.out.println("Evaluating prefix of '- + * 2 3 * 5 4 9': " + evaluatePrefixRec("- + * 2 3 * 5 4 9"));

        System.out.println("Printing an array given as an example in homework");
        int arr2[][]={
             {1,2,3,4}
            ,{5,6,7,8}
            ,{9,10,11,12}
            ,{13,14,15,16}
            ,{17,18,19,20}
        };
        System.out.println("Print recursively the array in clockwise direction");
        printRec(arr2);
    }
}
