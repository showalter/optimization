/**
 * The HookeAndJeevesDescreteStepsMethod class provides an implementation of the line search version
 * of the method of Hooke and Jeeves for optimizing a multivariable function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class HookeAndJeevesDiscreteStepsMethod extends AbstractSubject
    implements MultidimensionalOptimizationMethod
{
  private final double e, alpha, initialStepSize;
  private final Vector start;

  /**
   * Create a HookeAndJeevesDiscreteStepsMethod object with the given starting point, termination
   * scalar e, acceleration constant alpha, and initial step size delta.
   *
   * @param start           the point to start the search
   * @param e               the termination scalar
   * @param alpha           the acceleration constant
   * @param initialStepSize the initial step size
   */
  public HookeAndJeevesDiscreteStepsMethod(Vector start, double e, double alpha,
      double initialStepSize)
  {
    this.start = start;
    this.e = e;
    this.alpha = alpha;
    this.initialStepSize = initialStepSize;
  }

  @Override public Vector minimize(MultivariableFunction f)
  {
    int n = f.degree();

    Vector[] coordinateVectors = Vector.getCoordinateDirections(n);

    Vector x = start; // x is the old point
    Vector y = x; // y is the new 'test point'
    double delta = initialStepSize; // delta is the current step size
    int iteration = 1;

    while (true)
    {

      notifyObservers(EventType.ITERATION, iteration, "iteration: ");
      notifyObservers(EventType.OTHER, delta, "delta: ");

      for (int i = 0; i < n; i++)
      {
        notifyObservers(EventType.TEST_POINT, x.getComponent(i), "component " + i + " of x: ");
      }

      // Build the test point by checking in all directions for lower function values
      for (int j = 0; j < n; j++)
      {
        notifyObservers(EventType.ITERATION, j, "j: ");

        double fAtTestY = f.f(y.add(coordinateVectors[j].scale(delta)));
        double fAtY = f.f(y);

        notifyObservers(EventType.EVALUATION, fAtTestY, "f(y + delta(direction " + j + "): ");
        notifyObservers(EventType.EVALUATION, fAtY, "f(y): ");

        if (fAtTestY < fAtY)
        {
          y = y.add(coordinateVectors[j].scale(delta));
        }
        else
        {
          fAtTestY = f.f(y.add(coordinateVectors[j].scale(-1 * delta)));

          notifyObservers(EventType.EVALUATION, fAtTestY, "f(y - delta(direction " + j + "): ");

          if (f.f(y.add(coordinateVectors[j].scale(-1 * delta))) < f.f(y))
          {
            y = y.add(coordinateVectors[j].scale(-1 * delta));
          }
        }
      }

      for (int i = 0; i < n; i++)
      {
        notifyObservers(EventType.TEST_POINT, y.getComponent(i), "component " + i + "of y: ");
      }

      double fAtX = f.f(x);
      double fAtY = f.f(y);

      notifyObservers(EventType.EVALUATION, fAtY, "f(y): ");
      notifyObservers(EventType.EVALUATION, fAtX, "f(x): ");

      // Check if the point we've 'made' is better than the last point.
      if (f.f(y) < f.f(x))
      {
        Vector lastX = x;
        x = y;

        y = x.add(x.add(lastX.scale(-1 * alpha)));
      }
      else
      {
        // Getting here means the new point is no better than the old point

        // Check if the step size is small enough to quit
        if (delta < e)
        {
          return x;
        }

        // Reduce the step size
        delta /= 2;

        y = x;
      }

      iteration++;
    }
  }
}
