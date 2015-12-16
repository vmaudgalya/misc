/**
* Evaluates a given expression of the form ( 4 + 3 ) * 2 / 7 OR 2 - 7 * 3 + 4
* An expression must separate tokens using space as a delimeter
* @author Varun Maudgalya
***/
import java.util.Deque;
import java.util.ArrayDeque;

public class ExpressionEvaluator {

//  public double evaluate(final String expression) {
//    if (expression == null) {
//      throw new NullPointerException("Null expressions cannot be evaluated");
//    } else if (expression.equals("")) {
//      throw new Exception("Empty expressions cannot be evaluated");
//    } // assumes valid input from here onwards
//    final String postfixExpression = this.infixToPostfix(expression);
//    return evaluatePostfix(postfixExpression);
//  }

  public static void main(String[] args) {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
//    System.out.println(evaluator.infixToPostfix("( 1 + 2 / 3 * ( 4 + 5 ) - 6"));
    System.out.println(evaluator.infixToPostfix("4 + 2 * 5 + 1"));
  }


  /**
  * Valid input is assumed, requires space delimited infix expression
  * O(n) time and space
  */
  private String infixToPostfix(final String expression) {
    Deque<String> stack = new ArrayDeque<String>();
    String[] tokens = expression.split(" ");
    StringBuilder postfixExpression = new StringBuilder();
    for (int i = 0; i < tokens.length; i++) {

      if (isOpeningParanthesis(tokens[i])) {
        stack.push(tokens[i]);

      } else if (isClosingParanthesis(tokens[i])) {
        while (!isOpeningParanthesis(stack.peek())) {
          postfixExpression.append(stack.pop());
        }
        stack.pop(); // Remove the leftover paranthesis

      } else if (isOperator(tokens[i])) {
        if (stack.isEmpty() || isOpeningParanthesis(stack.peek())) {
          stack.push(tokens[i]);
        } else {
          if (getOperatorPriority(stack.peek()) >= getOperatorPriority(tokens[i])) {
            while (!stack.isEmpty() && getOperatorPriority(stack.peek()) >= getOperatorPriority(tokens[i])) {
              postfixExpression.append(stack.pop());
            }
            stack.push(tokens[i]);
          } else {
            stack.push(tokens[i]);
          }
        }

      } else if (isInteger(tokens[i])) {
        postfixExpression.append(Integer.parseInt(tokens[i]));
      }

    }
    while (!stack.isEmpty()) {
      postfixExpression.append(stack.pop());
    }
    return postfixExpression.toString();
  }

  private boolean isInteger(final String token) {
    try {
      Integer.parseInt(token);
    } catch (NumberFormatException error) {
      return false;
    }
    return true;
  }

  private boolean isOpeningParanthesis(final String token) {
    if (token.equals("(")) {
      return true;
    }
    return false;
  }

  private boolean isClosingParanthesis(final String token) {
    if (token.equals(")")) {
      return true;
    }
    return false;
  }

  private boolean isOperator(final String token) {
    if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
      return true;
    }
    return false;
  }

  private int getOperatorPriority(final String operator) {
    if (operator.equals("*") || operator.equals("/")) {
      return 2;
    }
    return 1;
  }

}
