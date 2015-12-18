/**
* Evaluates a given expression of the form ( 4 + 3 ) * 2 / 7 OR 2 - 7 * 3 + 4
* An expression must separate tokens using space as a delimeter
* @author Varun Maudgalya
***/
import java.util.Deque;
import java.util.ArrayDeque;

public class ExpressionEvaluator {

  public double evaluate(final String expression) throws Exception {
    if (expression == null) {
      throw new NullPointerException("Null expressions cannot be evaluated");
    } else if (expression.equals("")) {
      throw new Exception("Empty expressions cannot be evaluated");
    } // assumes valid input from here onwards
    final String postfixExpression = this.infixToPostfix(expression);
    return evaluatePostfix(postfixExpression);
  }

  public static void main(String[] args) throws Exception {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    System.out.println(evaluator.evaluate("2 * 3 + 5 * 4 - 9"));
  }

  private String infixToPostfix(final String expression) {
    Deque<String> stack = new ArrayDeque<String>();
    String[] tokens = expression.split(" ");
    StringBuilder postfixExpression = new StringBuilder();
    for (int i = 0; i < tokens.length; i++) {

      if (isOpeningParanthesis(tokens[i])) {
        stack.push(tokens[i]);

      } else if (isClosingParanthesis(tokens[i])) {
        while (!isOpeningParanthesis(stack.peek())) {
          postfixExpression.append(stack.pop() + " ");
        }
        stack.pop(); // Remove the leftover paranthesis

      } else if (isOperator(tokens[i])) {
        if (stack.isEmpty() || isOpeningParanthesis(stack.peek())) {
          stack.push(tokens[i]);
        } else {
          if (getOperatorPriority(stack.peek()) >= getOperatorPriority(tokens[i])) {
            while (!stack.isEmpty() && getOperatorPriority(stack.peek()) >= getOperatorPriority(tokens[i])) {
              postfixExpression.append(stack.pop()  + " ");
            }
            stack.push(tokens[i]);
          } else {
            stack.push(tokens[i]);
          }
        }

      } else if (isNumeric(tokens[i])) {
        postfixExpression.append(Integer.parseInt(tokens[i])  + " ");
      }

    }
    while (!stack.isEmpty()) {
      postfixExpression.append(stack.pop()  + " "); // Pop everything left on the stack and append to the postfix expression
    }
    return postfixExpression.toString().trim();
  }

  private double evaluatePostfix(String postfixExpression) throws Exception {
    Deque<Double> stack = new ArrayDeque<Double>();
    String[] tokens = postfixExpression.split(" ");
    for (int i = 0; i < tokens.length; i++) {
      if (isOperator(tokens[i])) {
        double secondOperand = stack.pop();
        double firstOperand = stack.pop();
        double result = performCalculation(tokens[i], firstOperand, secondOperand);
        stack.push(result);
      } else if (isNumeric(tokens[i])){
        stack.push(Double.parseDouble(tokens[i]));
      }
    }
    return stack.pop();
  }

  private double performCalculation(final String operator,
        final double first, final double second) throws Exception {
    if (operator.equals("+")) {
      return first + second;
    } else if (operator.equals("-")) {
      return first - second;
    } else if (operator.equals("*")) {
      return first * second;
    } else if (operator.equals("/")) {
      return first / second;
    }
    throw new Exception("Invalid operator in expression");
  }

  private boolean isNumeric(final String token) {
    try {
      Double.parseDouble(token);
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
