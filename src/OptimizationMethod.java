import java.util.function.DoubleUnaryOperator;

/**
 * The OptimizationMethod interface defines the operations an optimization method can perform.
 * <p>
 * This interface is for optimization methods for functions of a single variable.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface OptimizationMethod
{
  /**
   * Minimize function f, possibly using fPrime and fDoublePrime, which are the first and second
   * derivatives of f, respectively, on the interval [a1, b1].
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize
   * @param fDoublePrime the second derivative of the function to minimize
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the midpoint of the final interval of uncertainty
   */
  public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1);

  public void addOptimizationObserver(OptimizationObserver observer);

  public void notifyObservers(EventType type, double value, String text);

  public void removeOptimizationObserver(OptimizationObserver observer);


}
