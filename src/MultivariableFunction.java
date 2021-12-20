/**
 * The MultivariableFunction interface defines the operations of a function of multiple variables.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface MultivariableFunction
{
  /**
   * Evaluate this function with the given vector.
   *
   * @param v the Vector to use as input to the function
   * @return the function's value with the given input
   */
  double f(Vector v);

  /**
   * Evaluate the partial derivative of this function with respect to some variable.
   * <p>
   * For example, to take the partial derivative of f with respect to x_0, pass 0 as the argument
   * for varWithRespectTo
   *
   * @param v                the Vector where the partial derivative is being evaluated at
   * @param varWithRespectTo the variable that the partial derivative is taken with respect to
   * @return the value of the partial derivative with respect to the given variable at the given
   * input Vector
   */
  double partialDerivative(Vector v, int varWithRespectTo);

  /**
   * Get the degree of this function.
   * <p>
   * This returns the number of variables this function takes as input, or the number of components
   * the input vectors need.
   *
   * @return the degree of this function.
   */
  int degree();
}
