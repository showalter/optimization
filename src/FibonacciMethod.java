import java.util.function.DoubleUnaryOperator;

/**
 * The FibonacciMethod class is an implementation of the Fibonacci method for optimizing
 * functions of a single variable.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class FibonacciMethod extends AbstractSubject implements OptimizationMethod
{

  // An array of the Fibonacci sequence up until the numbers can't fit into a long.
  private static final long[] fib = {1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L,
      610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L,
      196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L,
      14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L, 267914296L, 433494437L,
      701408733L, 1134903170L, 1836311903L, 2971215073L, 4807526976L, 7778742049L, 12586269025L,
      20365011074L, 32951280099L, 53316291173L, 86267571272L, 139583862445L, 225851433717L,
      365435296162L, 591286729879L, 956722026041L, 1548008755920L, 2504730781961L, 4052739537881L,
      6557470319842L, 10610209857723L, 17167680177565L, 27777890035288L, 44945570212853L,
      72723460248141L, 117669030460994L, 190392490709135L, 308061521170129L, 498454011879264L,
      806515533049393L, 1304969544928657L, 2111485077978050L, 3416454622906707L, 5527939700884757L,
      8944394323791464L, 14472334024676221L, 23416728348467685L, 37889062373143906L,
      61305790721611591L, 99194853094755497L, 160500643816367088L, 259695496911122585L,
      420196140727489673L, 679891637638612258L, 1100087778366101931L, 1779979416004714189L,
      2880067194370816120L, 4660046610375530309L, 7540113804746346429L};

  private final double l;
  private final double e;

  /**
   * Create a FibonacciMethod object which will optimize functions to the given interval of
   * uncertainty.
   *
   * @param l the desired final interval of uncertainty
   * @param e the distinguishability constant
   */
  public FibonacciMethod(double l, double e)
  {
    this.l = l;
    this.e = e;
  }

  /**
   * Minimize the given function using the Fibonacci method.
   *
   * @param f            the function to minimize
   * @param fPrime       the first derivative of the function to minimize (unused)
   * @param fDoublePrime the second derivative of the function to minimize (unused)
   * @param a1           the lower bound of the interval to minimize over
   * @param b1           the upper bound of the interval to minimize over
   * @return the midpoint of the final interval of uncertainty
   */
  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {

    double a = a1;
    double b = b1;
    notifyObservers(EventType.BOUNDS, a, "starting a: ");
    notifyObservers(EventType.BOUNDS, b, "starting b: ");

    int n;

    // Find the first fibonacci number where f > (b-a)/l
    for (n = 0; n < fib.length && fib[n] <= (b - a) / l; n++)
      ;

    notifyObservers(EventType.OTHER, n, "number of iterations: ");

    double lambda = a + ((double) fib[n - 2] / fib[n]) * (b - a);
    double mu = a + ((double) fib[n - 1] / fib[n]) * (b - a);

    notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");
    notifyObservers(EventType.TEST_POINT, mu, "mu: ");

    for (int i = 1; i < n; i++)
    {
      notifyObservers(EventType.ITERATION, i, "iteration: ");

      double fOfLambda = f.applyAsDouble(lambda);
      double fOfMu = f.applyAsDouble(mu);

      notifyObservers(EventType.EVALUATION, fOfLambda, "f(lambda): ");
      notifyObservers(EventType.EVALUATION, fOfMu, "f(mu): ");

      if (fOfLambda > fOfMu)
      {
        a = lambda;
        lambda = mu;
        mu = a + ((double) fib[n - i - 1] / fib[n - i]) * (b - a);
      }
      else
      {
        b = mu;
        mu = lambda;
        lambda = a + ((double) fib[n - i - 2] / fib[n - i]) * (b - a);
      }

      notifyObservers(EventType.BOUNDS, a, "a: ");
      notifyObservers(EventType.BOUNDS, b, "b: ");
      notifyObservers(EventType.TEST_POINT, lambda, "lambda: ");
      notifyObservers(EventType.TEST_POINT, mu, "mu: ");

      if (i == n - 2)
      {
        mu = lambda + e;

        notifyObservers(EventType.TEST_POINT, mu, "final mu: ");

        fOfLambda = f.applyAsDouble(lambda);
        fOfMu = f.applyAsDouble(mu);

        notifyObservers(EventType.EVALUATION, fOfLambda, "final f(lambda): ");
        notifyObservers(EventType.EVALUATION, fOfMu, "final f(mu): ");

        if (fOfLambda > fOfMu)
        {
          a = lambda;
        }
        else
        {
          b = lambda;
        }

        notifyObservers(EventType.BOUNDS, a, "final a: ");
        notifyObservers(EventType.BOUNDS, b, "final b: ");

        return (a + b) / 2;
      }

    }

    // We should never get here.
    return (a + b) / 2;
  }
}
