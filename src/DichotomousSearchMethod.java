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
public class DichotomousSearchMethod implements OptimizationMethod
{

  public final double l;
  public final double e;

  /**
   * Create a DichotomousSearchMethod object that will minimize functions to the given final
   * interval of uncertainty.
   *
   * @param l the desired final interval of uncertainty
   * @param e the distinguishability constant
   */
  public DichotomousSearchMethod(double l, double e)
  {
    this.l = l;
    this.e = e;
  }

  /**
   * Minimize f using the Dichotomous Search Method.
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize (unused)
   * @param fDoublePrime the second derivative of the function to minimize (unused)
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return The midpoint of the final interval of uncertainty
   */
  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {

    double a = a1;
    double b = b1;

    while (b - a > l)
    {
      double lambda = (a + b) / 2 - e;
      double mu = (a + b) / 2 + e;

      if (f.applyAsDouble(lambda) < f.applyAsDouble(mu))
      {
        b = mu;
      }
      else
      {
        a = lambda;
      }
    }

    return (a + b) / 2;
  }

}
