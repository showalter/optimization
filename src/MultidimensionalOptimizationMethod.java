/**
 * The MultidimensionalOptimizationMethod interface defines the operations an optimization method
 * can perform.
 * <p>
 * This interface is for optimization methods for functions of multiple variables.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface MultidimensionalOptimizationMethod extends Subject
{

  /**
   * Minimize function f.
   *
   * @param f the function to minimize
   * @return the Vector that minimizes f
   */
  Vector minimize(MultivariableFunction f);

}
