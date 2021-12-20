/**
 * This driver class is used to demonstrate the various optimization methods.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class Driver
{

  /**
   * The entry point of the program.
   *
   * @param args command line arguments (unused)
   */
  public static void main(String[] args)
  {
    OptimizationObserver o = new PrintingOptimizationObserver();

    OptimizationMethod d = new DichotomousSearchMethod(0.2, 0.01);
    System.out.println("DichotomousSearch: " + d.minimize((x) -> x * x + 2 * x, null, null, -3, 5));

    System.out.println("Golden Section: ");
    OptimizationMethod g = new GoldenSectionMethod(0.2);
    g.addOptimizationObserver(new TestPointObserver());
    System.out.println(g.minimize((x) -> x * x + 2 * x, null, null, -3, 5));

    System.out.print("Fibonacci Search: ");
    OptimizationMethod f = new FibonacciMethod(0.2, 0.01);
    System.out.println(f.minimize((x) -> x * x + 2 * x, null, null, -3, 5));

    OptimizationMethod b = new BisectingSearchMethod(0.2);
    b.addOptimizationObserver(o);
    System.out.println("Bisecting search");
    System.out.println(b.minimize((x) -> x * x + 2, (x) -> 2 * x + 2, null, -3, 6));

    OptimizationMethod n = new NewtonsMethod(0.4, 0.005);
    double result = n.minimize(
        (x) -> x >= 0 ? 4 * x * x * x - 3 * x * x * x * x : 4 * x * x * x + 3 * x * x * x * x,
        (x) -> x >= 0 ? 12 * x * x - 12 * x * x * x : 12 * x * x + 12 * x * x * x,
        (x) -> x >= 0 ? 24 * x - 36 * x * x : 24 * x + 36 * x * x, Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY);
    System.out.println("Newton start at 0.4: " + result);

    // This will throw an exception because it will not converge to a minimum value.
    // n = new NewtonsMethod(0.6, 0.005);

    // result = n.minimize((x) -> x >= 0 ? 4*x*x*x - 3*x*x*x*x : 4*x*x*x + 3*x*x*x*x,
    //    (x) -> x >= 0 ? 12*x*x - 12*x*x*x : 12*x*x + 12*x*x*x,
    //    (x) -> x >= 0 ? 24*x - 36*x*x : 24*x + 36*x*x,
    //    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

    // System.out.println("Newton start at 0.6: " + result);

    n = new NewtonsMethod(0.01, 0.005);
    result = n.minimize(
        (x) -> x >= 0 ? 4 * x * x * x - 3 * x * x * x * x : 4 * x * x * x + 3 * x * x * x * x,
        (x) -> x >= 0 ? 12 * x * x - 12 * x * x * x : 12 * x * x + 12 * x * x * x,
        (x) -> x >= 0 ? 24 * x - 36 * x * x : 24 * x + 36 * x * x, Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY);
    System.out.println("Newton start at 0.0: " + result);

    MultivariableFunction mvf = new MultiVarFun1();
    Vector start = new Vector(new double[] {0., 3.});

    MultidimensionalOptimizationMethod ccm = new CyclicCoordinateMethod(start, 0.03,
        new FibonacciMethod(0.01, 0.004));
    ccm.addOptimizationObserver(o);
    System.out.println(ccm.minimize(mvf));

    MultidimensionalOptimizationMethod hj = new HookeAndJeevesMethod(start, 0.03,
        new FibonacciMethod(0.01, 0.004));
    hj.addOptimizationObserver(o);
    System.out.println(hj.minimize(mvf));

    MultidimensionalOptimizationMethod hjds = new HookeAndJeevesDiscreteStepsMethod(start, 0.1, 1.0,
        0.2);
    System.out.println(hjds.minimize(mvf));

    MultidimensionalOptimizationMethod r = new RosenbrockMethod(start, 0.03,
        new FibonacciMethod(0.01, 0.04));
    System.out.println("rosenbrock: " + r.minimize(mvf));

    MultidimensionalOptimizationMethod sd = new SteepestDescentMethod(start, 0.1,
        new FibonacciMethod(0.01, 0.04));
    System.out.println("steepest descent: " + sd.minimize(mvf));
  }

  private static class MultiVarFun1 implements MultivariableFunction
  {
    @Override public double f(Vector v)
    {
      if (v.length() < 2)
      {
        throw new IllegalArgumentException("Not enough components in v");
      }

      // (x_1 - 2)^4 + (x_1 - 2x_2)^2
      return Math.pow(v.getComponent(0) - 2, 4) + Math.pow(
          v.getComponent(0) - (2 * v.getComponent(1)), 2);
    }

    @Override public double partialDerivative(Vector v, int varWithRespectTo)
    {
      if (v.length() < 2)
      {
        throw new IllegalArgumentException("Not enough components in v");
      }

      double x0 = v.getComponent(0);
      double x1 = v.getComponent(1);

      // d/dx_0 (f) = 4*x0^3 - 24*x0^2 + 50*x0 - 4*x1 - 32
      if (varWithRespectTo == 0)
      {
        return 4 * x0 * x0 * x0 - 24 * x0 * x0 + 50 * x0 - 4 * x1 - 32;
      }

      // -4*x0 + 8*x1
      if (varWithRespectTo == 1)
      {
        return -4 * x0 + 8 * x1;
      }

      throw new IllegalArgumentException(
          "Invalid variable to take partial derivative with respect to");
    }

    @Override public int degree()
    {
      return 2;
    }
  }
}
