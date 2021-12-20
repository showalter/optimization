/**
 * The PrintingOptimizationObserver class is an OptimizationObserver that prints all the
 * information for all the events it received.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class PrintingOptimizationObserver implements OptimizationObserver
{
  @Override public void handleEvent(Subject subject, EventType type, double value, String text)
  {
    System.out.printf("%s: %s %f\n", subject.getClass().toString(), text, value);
  }
}
