/**
 * The RosenbrockMethod class provides an implementation of the line search version of Rosenbrock's
 * method for optimizing a multivariable function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 *
 * @author Ryan Showalter
 * @version 1
 */
public class RosenbrockMethod extends AbstractSubject implements MultidimensionalOptimizationMethod
{
  private final Vector start;
  private final OptimizationMethod oneDimensionMethod;
  private final double e;

  /**
   * Create a RosenbrockMethod object with the given starting point, termination scalar e, and
   * single variable optimization method.
   * @param start the point to start the search
   * @param e the termination scalar
   * @param oneDimensionMethod an OptimizationMethod object for optimizing functions of a single
   *                           variable
   */
  public RosenbrockMethod(Vector start, double e, OptimizationMethod oneDimensionMethod) {
    this.start = start;
    this.e = e;
    this.oneDimensionMethod = oneDimensionMethod;
  }

  /**
   * Get new search directions based on old search directions and the amount traveled in those
   * directions.
   *
   * This method uses the Gram-Schmidt process to get orthogonal vectors.
   *
   * @param oldDirections the old search directions
   * @param lambdas the amount traveled in the corresponding search direction - lambdas[i] is the
   *                distance traveled in the direction of oldDirections[i]
   * @return the new search directions
   */
  public static Vector[] getSearchDirections(Vector[] oldDirections, double[] lambdas) {
    int n = lambdas.length;

    Vector[] as = new Vector[n];
    for (int i = 0; i < n; i++) {
      if (lambdas[i] == 0) {
        as[i] = oldDirections[i];
      } else {
        Vector ai = new Vector(new double[n]);
        for (int j = i; j < n; j++) {
          ai = ai.add(oldDirections[j].scale(lambdas[j]));
        }
        as[i] = ai;
      }
    }

    Vector[] newDirections = new Vector[n];
    for (int i = 0; i < n; i++) {
      Vector b = as[i];

      Vector sum = new Vector(new double[n]);
      for (int j = 0; j < i; j++) {
        sum = sum.add(newDirections[j].scale(as[i].dot(newDirections[j])));
      }

      b = b.add(sum.scale(-1));
      newDirections[i] = b.normalize();
    }

    return newDirections;
  }

  @Override public Vector minimize(MultivariableFunction f)
  {
    int n = f.degree();

    Vector[] directions = Vector.getCoordinateDirections(n);

    Vector x = start;
    Vector y = x;
    int iteration = 1;

    while (true) {
      double[] lambdas = new double[n];

      notifyObservers(EventType.ITERATION, iteration, "iteration: ");

      for (int i = 0; i < n; i++)
      {
        notifyObservers(EventType.TEST_POINT, x.getComponent(i), "component " + i + " of x: ");
      }

      for (int i = 0; i < n; i++) {
        notifyObservers(EventType.ITERATION, i, "i: ");

        Vector direction = directions[i];

        for (int j = 0; j < n; j++)
        {
          notifyObservers(EventType.OTHER, direction.getComponent(j),
              "component " + j + "of direction " + i + ": ");
        }

        // We have to save this variable in a variable that is 'effectively final' for the purposes
        // of the lambda
        Vector finalY = y;

        lambdas[i] = oneDimensionMethod.minimize(d -> f.f(finalY.add(direction.scale(d))), null, null, -5, 5);

        notifyObservers(EventType.OTHER, lambdas[i], "lambdas[" + i + "]: ");

        y = y.add(directions[i].scale(lambdas[i]));
      }

      for (int i = 0; i < n; i++)
      {
        notifyObservers(EventType.TEST_POINT, x.getComponent(i), "component " + i + " of new x: ");
      }

      Vector lastX = x;
      x = y;

      double dist = Vector.distance(x, lastX);
      notifyObservers(EventType.OTHER, dist, "Distance between x and last x: ");

      // Exit condition
      if (dist < e)
      {
        return x;
      }

      directions = getSearchDirections(directions, lambdas);
      iteration++;
    }
  }
}
