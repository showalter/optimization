/**
 * The OptimizationObserver interface defines the necessary operations for an Observer of OptimizationMethod objects.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface OptimizationObserver
{
  /**
   * Handle an event.
   *
   * @param subject the OptimizationMethod who had the event.
   * @param type the type of event that occurred
   * @param value the value associated with the event
   * @param text a description of value
   */
  public void handleEvent(OptimizationMethod subject, EventType type, double value, String text);

}
