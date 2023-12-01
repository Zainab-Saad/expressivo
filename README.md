## Representing Expressions
Define an immutable, recursive abstract data type to represent expressions as abstract syntax trees.

Your AST should be defined in the provided Expression interface (in Expression.java ) and implemented by several concrete variants, one for each kind of expression. Each variant should be defined in its own appropriately-named .java file.

Concrete syntax in the input, such as parentheses and whitespace, should not be represented at all in your AST.

## 1.1 Expression
To repeat, your data type must be immutable and recursive . Follow the recipe for creating an ADT:

Spec . Choose and specify operations. For this part of the problem set, the only operations Expression needs are creators and producers for building up an expression, plus the standard observers toString() , equals() , and hashCode() . We are strengthening the specs for these standard methods; see below.
Test . Partition and test your operations in ExpressionTest.java , including tests for toString() , equals() , and hashCode() . Note that we will not run your test cases on other implementations, just on yours.
Code . Write the rep for your Expression as a data type definition in a comment inside Expression . Implement the variant classes of your data type.
Remember to include a Javadoc comment above every class and every method you write; define abstraction functions and rep invariants, and write checkRep; and document safety from rep exposure.

## 1.2 toString()
Define the toString() operation on Expression so it can output itself as a string. This string must be a valid expression as defined above. You have the freedom to decide how to format the output with whitespace and parentheses for readability, but the expression must have the same mathematical meaning.

Your toString() implementation must be recursive, and must not use instanceof .

Use the @Override annotation to ensure you are overriding the toString() inherited from Object .

Remember that your tests must obey the spec. If your toString() tests expect a certain formatting of whitespace and parentheses, you must specify this formatting in your spec.

## 1.3 equals() and hashCode()
Define the equals() and hashCode() operations on your AST to implement structural equality .

Structural equality defines two expressions to be equal if:

the expressions contain the same variables, numbers, and operators;
those variables, numbers, and operators are in the same order, read left-to-right;
and they are grouped in the same way.
For example, the AST for 1 + x is not equal to the AST for x + 1 , but it is equal to the ASTs for 1+x , (1+x) , and (1)+(x) .

For n -ary groupings where n is greater than 2:

Such expressions must be equal to themselves. For example, the ASTs for 3 + 4 + 5 and (3 + 4 + 5) must be equal.
However, whether they are equal or not to different groupings with the same mathematical meaning is not specified , and you should choose an appropriate specification and implementation for your AST. For example, you must determine whether the ASTs for (3 + 4) + 5 and 3 + (4 + 5) are equal.
For equality of numbers, you have the freedom to choose a reasonable definition. Integers that can be represented exactly as a double should be considered equal. For example, the ASTs for x + 1 and x + 1.000 must be equal.

Remember: concrete syntax, including parentheses, should not be represented in your AST. Grouping, for example, should be reflected in the AST’s structure.

Be sure that AST instances which are considered equal according to this definition and according to equals() also satisfy observational equality .

Your equals() and hashCode() implementations must be recursive. Only equals() can use instanceof , and hashCode() must not.

Remember to use the @Override annotation.