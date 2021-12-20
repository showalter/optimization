import java.util.function.DoubleUnaryOperator;

/**
 * The HookeAndJeevesMethod class provides an implementation of the line search version of the method
 * of Hooke and Jeeves for optimizing a multivariable function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class HookeAndJeevesMethod extends AbstractSubject
    implements MultidimensionalOptimizationMethod
{

  private final double e;
  private final Vector start;
  private final OptimizationMethod oneDimensionMethod;

  /**
   * Create a HookeAndJeevesMethod object with the given starting point, termination scalar e, and
   * single variable optimization method.
   *
   * @param start              the point to start the search
   * @param e                  the termination scalar
   * @param oneDimensionMethod an OptimizationMethod object for optimizing functions of a single
   *                           variable
   */
  public HookeAndJeevesMethod(Vector start, double e, OptimizationMethod oneDimensionMethod)
  {
    this.start = start;
    this.e = e;
    this.oneDimensionMethod = oneDimensionMethod;
  }

  @Override public Vector minimize(MultivariableFunction f)
  {
    Vector[] coordinateVectors = Vector.getCoordinateDirections(f.degree());

    Vector x = start;
    Vector lastX = x;
    int iteration = 1;

    while (true)
    {
      notifyObservers(EventType.ITERATION, iteration, "iteration k: ");

      for (int i = 0; i < f.degree(); i++)
      {
        notifyObservers(EventType.ITERATION, i, "component number: ");

        // We have to save these variables in variables which are 'effectively final' for the
        // purposes of the lambda.
        Vector finalX = x;
        int finalI = i;

        DoubleUnaryOperator function = operand -> f.f(
            finalX.add(coordinateVectors[finalI].scale(operand)));

        // Find the value that minimizes the function
        double optimalLambda = oneDimensionMethod.minimize(function, null, null, -5, 5);

        notifyObservers(EventType.TEST_POINT, optimalLambda, "OptimalLambda: ");

        x = x.add(coordinateVectors[finalI].scale(optimalLambda));
      }

      for (int i = 0; i < f.degree(); i++)
      {
        notifyObservers(EventType.OTHER, x.getComponent(i), "component " + i + " of x: ");
      }

      notifyObservers(EventType.OTHER, Vector.distance(x, lastX),
          "Distance between x and last x: ");

      double dist = Vector.distance(x, lastX);
      notifyObservers(EventType.OTHER, dist, "Distance between x and last x: ");

      // Exit condition
      if (dist < e)
      {
        return x;
      }

      Vector patternDirection = x.add(lastX.scale(-1));

      Vector finalX1 = x;
      double optimalLambda = oneDimensionMethod.minimize(
          d -> f.f(finalX1.add(patternDirection.scale(d))), null, null, -5, 5);

      for (int i = 0; i < f.degree(); i++)
      {
        notifyObservers(EventType.OTHER, patternDirection.getComponent(i),
            "component " + i + " of pattern direction: ");
      }

      notifyObservers(EventType.OTHER, optimalLambda, "optimal lambda for pattern direction: ");

      lastX = x;
      x = x.add(patternDirection.scale(optimalLambda));

      for (int i = 0; i < f.degree(); i++)
      {
        notifyObservers(EventType.OTHER, x.getComponent(i), "component " + i + " of x: ");
      }

      iteration++;
    }
  }
}
