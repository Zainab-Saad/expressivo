/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    /*
     * Expression
     */

    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) 
    {
        // remove spaces
        // spaces may not occur between variables and numbers
        // create ParseError
        // operator between numbers and variables
        Expression expression = null;

        if (input != null)
        {
            char[] chars = input.toCharArray();

            // if first character is an operator, throw an error
            if (isOperator(chars[0]))
                System.out.println("Error");;

            OperandNode opNode1 = null;
            OperandNode opNode2 = null;

           for (int i=0; i<chars.length; i++)
           {
                // if the character is an operand
                if (isOperand(chars[i]))
                {   
                    // if operand is variable
                    if (((chars[i] >= 'a') && (chars[i] <= 'z') ||
                    (chars[i] >= 'A') && (chars[i] <= 'Z')))
                    {
                        StringBuilder variable = new StringBuilder();

                        // loop till variable name isn't complete
                        do
                        {
                            variable.append(chars[i]);
                            i++;
                            if (i >= chars.length-1)
                                break;
                        }while(((chars[i] >= 'a') && (chars[i] <= 'z') ||
                        (chars[i] >= 'A') && (chars[i] <= 'Z')));
                        
                        if (opNode1 == null)
                            opNode1 = new OperandNode(variable.toString());
                        else
                            opNode2 = new OperandNode(variable.toString());
                        i--;
                    }

                    // if operand is constant
                    else if ((chars[i] >= '0') && (chars[i] <= '9'))
                    {
                        StringBuilder constant = new StringBuilder();

                        // loop till constant isn't complete
                        do
                        {
                            constant.append(chars[i]);
                            i++;
                            if (i >= chars.length-1)
                                break;
                        } while ((chars[i] >= '0') && (chars[i] <= '9'));

                        if (opNode1 == null)
                            opNode1 = new OperandNode(constant.toString());
                        else
                            opNode2 = new OperandNode(constant.toString());
                        i--;
                    }
                }

                // if the character is a space
                if ((chars[i] == ' ') && (i < (chars.length-1)))
                {
                    if (i == 0)
                        continue;

                    // if space is subseded by an operand 
                    if (isOperand(chars[i-1]))
                    { 
                        // if space is also followed by an operand
                        if (isOperand(chars[i+1]))
                        {
                            System.out.println("PARSEERROR: space between operands at " + i);
                            //return;
                        }
                    }

                    // if space is subseded by an operator
                    else if (isOperator(chars[i-1]))
                    {
                        // if space is also followed by an operator
                        if (isOperator(chars[i+1]))
                        {
                            System.out.println("PARSEERROR: space between operators at " + i);
                            //return;
                        }
                    }

                    else if (isSpace(chars[i+1]))
                        continue;
                }

                // if character is an operator
                if (isOperator(chars[i]))
                {
                    OperatorNode opNode = new OperatorNode(chars[i], opNode1, opNode2);
                }
           } 

        }
        
        return expression;
    }

    /* Utlilty Functions */

    // checks whether the character is a valid operand
    private static boolean isOperand(char ch)
    {
        boolean valid = false;
        // if character is a valid operand
        if (((ch >= '0') && (ch <='9')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <='Z')))
            valid = true;
        return valid;
    }

    // checks whether the character is a valid operator
    private static boolean isOperator(char ch)
    {
        boolean valid = false;
        // if character is a valid operator
        if ((ch == '+') || (ch == '*'))
            valid = true;
        return valid;
    }

    // checks whether the character is a space
    private static boolean isSpace(char ch)
    {
        boolean valid = false;
        // if character is a space
        if (ch == ' ')
            valid = true;
        return valid;
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    
}

class OperandNode
{
    String value;

    public OperandNode(String value)
    {
        this.value = value;
    }
}

class OperatorNode
{
    char operator;
    OperandNode left;
    OperandNode right;

    public OperatorNode(char operator, OperandNode left, OperandNode right)
    {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
}
