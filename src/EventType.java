/**
 * The EventType enumerated type enumerates the types of calculations and events that can occur during the optimization
 * of a function.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public enum EventType
{
  BOUNDS, // The bounds of the interval being optimized over
  ITERATION, // Describes the current iteration
  TEST_POINT, // The point where functions will be evaluated at
  EVALUATION, // The result of evaluating functions at the test points
  OTHER // Calculations/events that don't fit any other category
}
