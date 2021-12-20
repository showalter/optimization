/**
 * The Subject interface defines the methods required for a class to be observed by
 * OptimizationObservers.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public interface Subject
{
  /**
   * Add an OptimizationObserver to this Subject.
   *
   * @param observer the OptimizationObserver to add
   */
  void addOptimizationObserver(OptimizationObserver observer);

  /**
   * Notify all observers of an event.
   *
   * @param type  the type of event that occurred
   * @param value the value associated with the event
   * @param text  the text associated with the event
   */
  void notifyObservers(EventType type, double value, String text);

  /**
   * Remove an OptimizationObserver from this Subject.
   *
   * @param observer the OptimizationObserver to remove
   */
  void removeOptimizationObserver(OptimizationObserver observer);

}
