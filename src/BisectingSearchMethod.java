import java.util.function.DoubleUnaryOperator;

/**
 * The BisectingSearchMethod class is an implementation of the Bisecting Search Method
 * for optimizing a single variable function using the derivative of a function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class BisectingSearchMethod extends AbstractSubject implements OptimizationMethod
{
  private final double l;

  /**
   * Create a BisectingSearchMethod object that will minimize functions to the given interval of
   * uncertainly l.
   *
   * @param l The desired final interval of uncertainty
   */
  public BisectingSearchMethod(double l)
  {
    this.l = l;
  }

  /**
   * Minimize f using the bisecting search method.
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize
   * @param fDoublePrime the second derivative of the function to minimize (unused)
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the midpoint of the final interval of uncertainty
   */
  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {

    if (f == null)
    {
      throw new IllegalArgumentException("f cannot be null");
    }

    if (fPrime == null)
    {
      throw new IllegalArgumentException("fPrime cannot be null");
    }

    int n = 0;
    double halfToTheNth = 1;

    double a = a1;
    double b = b1;

    notifyObservers(EventType.BOUNDS, a, "starting a: ");
    notifyObservers(EventType.BOUNDS, b, "starting b: ");

    // Find the needed number of iterations to get the given final interval of uncertainty
    while (halfToTheNth > l / (b - a))
    {
      n++;
      halfToTheNth *= 0.5;
    }

    notifyObservers(EventType.OTHER, n, "number of iterations: ");

    for (int i = 0; i < n; i++)
    {
      double lambda = (a + b) / 2;
      double fPrimeAtLambda = fPrime.applyAsDouble(lambda);

      notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");
      notifyObservers(EventType.EVALUATION, fPrimeAtLambda, "fPrimeAtLambda: ");

      if (fPrimeAtLambda == 0)
      {
        return lambda;
      }
      else if (fPrimeAtLambda > 0)
      {
        b = lambda;
        notifyObservers(EventType.BOUNDS, b, "new b: ");
      }
      else
      {
        a = lambda;
        notifyObservers(EventType.BOUNDS, a, "new a: ");
      }
    }

    return (a + b) / 2;
  }
}
