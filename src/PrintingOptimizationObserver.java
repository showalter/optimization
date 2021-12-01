/**
 * The PrintingOptimizationObserver is an OptimizationObserver that prints the class, the text, and the value of every
 * event that it is notified of.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class PrintingOptimizationObserver implements OptimizationObserver
{
  @Override public void handleEvent(OptimizationMethod subject, EventType type, double value, String text)
  {
    System.out.printf("%s: %s %f\n", subject.getClass().toString(), text, value);
  }
}
