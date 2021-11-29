/**
 * The CyclicCoordinateMethod class provides an implementation fo the Cyclic Coordinate Method
 * of optimizing a multivariable function.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class CyclicCoordinateMethod implements MultidimensionalOptimizationMethod
{

  @Override public Vector minimize(MultivariableFunction f)
  {

    return new Vector(new double[] {0});
  }
}
