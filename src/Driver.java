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

    System.out.print("Golden Section: ");

    OptimizationMethod g = new GoldenSectionMethod(0.2);

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

  }
}
