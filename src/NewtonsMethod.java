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
public class NewtonsMethod extends AbstractOptimizationMethod
{
  private double start;
  private double delta;

  /**
   * Create a NewtonsMethod object with the given starting point and the maximum distance between
   * two observation points to determine convergence.
   *
   * @param start
   * @param delta
   */
  public NewtonsMethod(double start, double delta)
  {
    this.start = start;
    this.delta = delta;
  }

  /**
   * This is a helper method for determining if an input gives a local minumum.
   *
   * @param lambda the input value
   * @param fPrime the first derivative of the function being minimized
   * @return true if the input gives a local minimum; false otherwise
   */
  private boolean isLocalMin(double lambda, DoubleUnaryOperator fPrime)
  {
    return Math.abs(fPrime.applyAsDouble(lambda)) == 0;
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
    double beforeLastLambda = 0;
    double stop = 100; // Stop if this number of iterations is reached.

    if (isLocalMin(lambda, fPrime))
    {
      return lambda;
    }

    for (int i = 0; i < stop; i++)
    {
      beforeLastLambda = lastLambda;
      lastLambda = lambda;

      lambda = lambda - (fPrime.applyAsDouble(lambda) / fDoublePrime.applyAsDouble(lambda));

      if (Math.abs(lambda - lastLambda) < delta || isLocalMin(lambda, fPrime))
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
