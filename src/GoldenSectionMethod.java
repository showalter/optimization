import java.util.function.DoubleUnaryOperator;

/**
 * The DichotomousSearchMethod class is an implementation of the Dichotomous Search Method for
 * optimizing a function of a single variable.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class GoldenSectionMethod extends AbstractOptimizationMethod
{
  private final double l;

  /**
   * Create a GoldenSectionMethod object that will optimize functions to the given interval of
   * uncertainty.
   *
   * @param l the desired final interval of uncertainty
   */
  public GoldenSectionMethod(double l)
  {
    this.l = l;
  }

  /**
   * Minimize f using the Golden Section Method.
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize
   * @param fDoublePrime the second derivative of the function to minimize
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the midpoint of the final interval of uncertainty
   */
  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {

    double a = a1;
    double b = b1;

    notifyObservers(EventType.BOUNDS, a, "a: ");
    notifyObservers(EventType.BOUNDS, b, "b: ");

    double alpha = 0.618;

    while (b - a > l)
    {
      double lambda = a + (1 - alpha) * (b - a);
      double mu = a + alpha * (b - a);

      notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");
      notifyObservers(EventType.TEST_POINT, mu, "mu: ");

      double fOfLambda = f.applyAsDouble(lambda);
      double fOfMu = f.applyAsDouble(mu);

      notifyObservers(EventType.EVALUATION, fOfLambda, "f(lambda): ");
      notifyObservers(EventType.EVALUATION, fOfMu, "f(mu): ");

      if (fOfLambda > fOfMu)
      {
        a = lambda;
        lambda = mu;
        mu = a + alpha * (b - a);
      }
      else
      {
        b = mu;
        mu = lambda;
        lambda = a + (1 - alpha) * (b - a);
      }

      notifyObservers(EventType.BOUNDS, a, "a: ");
      notifyObservers(EventType.BOUNDS, b, "b: ");
      notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");
      notifyObservers(EventType.TEST_POINT, mu, "mu: ");
    }

    return (a + b) / 2;

  }

}
