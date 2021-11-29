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
  public double f(Vector v);

  /**
   * Get the degree of this function.
   * <p>
   * This returns the number of variables this function takes as input, or the number of components
   * the input vectors need.
   *
   * @return the degree of this function.
   */
  public int degree();
}
