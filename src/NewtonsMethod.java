import java.util.function.DoubleUnaryOperator;

/**
 * The NewtonsMethod class is an implementation of Newton's Method for optimizing a single variable
 * function using the first and second derivatives of a function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class NewtonsMethod extends AbstractSubject implements OptimizationMethod
{
  private final double start;
  private final double delta;

  /**
   * Create a NewtonsMethod object with the given starting point and the maximum distance between
   * two observation points to determine convergence.
   *
   * @param start where to start minimization
   * @param delta the maximum difference between two subsequent tests points to determine convergence
   */
  public NewtonsMethod(double start, double delta)
  {
    this.start = start;
    this.delta = delta;
  }

  /**
   * Minimize f using Newton's Method.
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize
   * @param fDoublePrime the second derivative of the function to minimize
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the input value that gives the minimum value of the function
   */
  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {
    double lambda = start;
    double lastLambda = 0;
    double beforeLastLambda;
    double stop = 100; // Stop if this number of iterations is reached.

    notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");

    for (int i = 0; i < stop; i++)
    {
      notifyObservers(EventType.ITERATION, i, "iteration: ");

      beforeLastLambda = lastLambda;
      lastLambda = lambda;

      double fPrimeOfLambda = fPrime.applyAsDouble(lambda);
      double fDoublePrimeOfLambda = fDoublePrime.applyAsDouble(lambda);
      notifyObservers(EventType.EVALUATION, fPrimeOfLambda, "fPrime(lambda): ");
      notifyObservers(EventType.EVALUATION, fDoublePrimeOfLambda, "fDoublePrime(lambda): ");

      lambda = lambda - (fPrime.applyAsDouble(lambda) / fDoublePrime.applyAsDouble(lambda));
      notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");

      if (Math.abs(lambda - lastLambda) < delta || fPrimeOfLambda == 0)
      {
        return lambda;
      }

      if (i > 2 && Math.abs(beforeLastLambda - lambda) < 1e-12)
      {
        throw new RuntimeException("Does not converge");
      }

    }

    // Should never get here
    return Double.NaN;
  }
}
