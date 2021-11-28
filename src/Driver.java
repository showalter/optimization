
public class Driver
{
  public static void main(String[] args) {
    OptimizationMethod d = new DichotomousSearchMethod();
    
    System.out.println(d.minimize((x) -> x*x + 2*x, -3, 5));
    
    System.out.print("Golden Section: ");
    
    OptimizationMethod g = new GoldenSectionMethod();
    
    System.out.println(g.minimize((x) -> x*x + 2*x, -3, 5));

    System.out.print("Fibonacci Search: ");

    OptimizationMethod f = new FibonacciMethod();

    System.out.println(f.minimize((x) -> x*x + 2*x, -3, 5));

    OptimizationMethodWithDerivatives b = new BisectingSearchMethod();

    System.out.println("Bisecting Search: " + b.minimize((x) -> x*x + 2, (x) -> 2*x + 2, null, -3, 6));

    OptimizationMethodWithDerivatives n = new NewtonsMethod(0.4, 0.005);

    double result = n.minimize((x) -> x >= 0 ? 4*x*x*x - 3*x*x*x*x : 4*x*x*x + 3*x*x*x*x,
        (x) -> x >= 0 ? 12*x*x - 12*x*x*x : 12*x*x + 12*x*x*x,
        (x) -> x >= 0 ? 24*x - 36*x*x : 24*x + 36*x*x, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

    System.out.println("Newton start at 0.4: " + result);

    /*n = new NewtonsMethod(0.6, 0.005);

    result = n.minimize((x) -> x >= 0 ? 4*x*x*x - 3*x*x*x*x : 4*x*x*x + 3*x*x*x*x,
        (x) -> x >= 0 ? 12*x*x - 12*x*x*x : 12*x*x + 12*x*x*x,
        (x) -> x >= 0 ? 24*x - 36*x*x : 24*x + 36*x*x, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

    System.out.println("Newton start at 0.6: " + result);*/

    n = new NewtonsMethod(0.01, 0.005);

    result = n.minimize((x) -> x >= 0 ? 4*x*x*x - 3*x*x*x*x : 4*x*x*x + 3*x*x*x*x,
        (x) -> x >= 0 ? 12*x*x - 12*x*x*x : 12*x*x + 12*x*x*x,
        (x) -> x >= 0 ? 24*x - 36*x*x : 24*x + 36*x*x, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

    System.out.println("Newton start at 0.0: " + result);
    
  }

  private class CyclicFunctionExample implements MultivariableFunction {

    @Override public double f(double... components)
    {
      return Math.pow(components[0] - 2, 4) + Math.pow(components[0] - 2*components[1], 2);
    }

    @Override public int degree()
    {
      return 2;
    }

  }
}
