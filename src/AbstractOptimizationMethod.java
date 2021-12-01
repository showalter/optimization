import java.util.Collection;
import java.util.HashSet;
import java.util.function.DoubleUnaryOperator;

/**
 * The AbstractOptimizationMethod class is an abstract class which implements the methods for an OptimizationMethod
 * to be an Observer.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public abstract class AbstractOptimizationMethod implements OptimizationMethod
{
  private final Collection<OptimizationObserver> observers = new HashSet<>();

  /**
   * Minimize the single variable function f on the interval [a1, b1].
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize
   * @param fDoublePrime the second derivative of the function to minimize
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the input value which minimizes f
   */
  public abstract double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1);

  @Override public void addOptimizationObserver(OptimizationObserver observer)
  {
    observers.add(observer);
  }

  @Override public void notifyObservers(EventType type, double value, String text)
  {
    observers.forEach(observer -> observer.handleEvent(this, type, value, text));
  }

  @Override public void removeOptimizationObserver(OptimizationObserver observer)
  {
    observers.remove(observer);
  }
}
