/**
 * The OptimizationObserver interface defines the necessary methods for a class to observe
 * optimization methods.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface OptimizationObserver
{
  /**
   * Handle an event for the given subject with a given type, value, and text.
   *
   * @param subject the subject that had the event
   * @param type    the type of event
   * @param value   a value associated with the event
   * @param text    the text associated with the event
   */
  void handleEvent(Subject subject, EventType type, double value, String text);

}
