/**
 * The SteepestDescentMethod class provides an implementation of method of steepest descent for
 * optimizing a multivariable function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class SteepestDescentMethod extends AbstractSubject
    implements MultidimensionalOptimizationMethod
{
  private final Vector start;
  private final double e;
  private final OptimizationMethod oneDimensionMethod;

  /**
   * Create a SteepestDescentMethod object with the given starting point, termination scalar e, and
   * single variable optimization method.
   *
   * @param start              the point to start the search
   * @param e                  the termination scalar
   * @param oneDimensionMethod an OptimizationMethod object for optimizing functions of a single
   *                           variable
   */
  public SteepestDescentMethod(Vector start, double e, OptimizationMethod oneDimensionMethod)
  {
    this.start = start;
    this.e = e;
    this.oneDimensionMethod = oneDimensionMethod;
  }

  @Override public Vector minimize(MultivariableFunction f)
  {
    int n = f.degree();
    Vector x = start;
    int iteration = 1;

    while (true)
    {
      notifyObservers(EventType.ITERATION, iteration, "iteration: ");

      for (int i = 0; i < n; i++)
      {
        notifyObservers(EventType.TEST_POINT, x.getComponent(i), "component " + i + " of x: ");
      }

      // Construct the gradient vector
      double[] componentsOfGradient = new double[n];
      for (int i = 0; i < n; i++)
      {
        componentsOfGradient[i] = f.partialDerivative(x, i);

        notifyObservers(EventType.EVALUATION, componentsOfGradient[i], "df/dx_" + i + ": ");
      }
      Vector gradientAtX = new Vector(componentsOfGradient);

      double gradientMagnitude = gradientAtX.magnitude();
      notifyObservers(EventType.OTHER, gradientMagnitude, "Magnitude of gradient vector: ");

      // Exit condition
      if (gradientMagnitude < e)
      {
        return x;
      }

      // We have to save this variable in a variable that is 'effectively final' for the purposes of
      // the lambda
      Vector finalX = x;

      // Find the amount to move in the direction that is steepest
      double optimalLambda = oneDimensionMethod.minimize(d -> f.f(finalX.add(gradientAtX.scale(d))),
          null, null, -5, 5);
      notifyObservers(EventType.OTHER, optimalLambda, "Optimal lambda: ");

      x = x.add(gradientAtX.scale(optimalLambda));

      iteration++;
    }
  }
}
