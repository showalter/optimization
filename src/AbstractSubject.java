import java.util.Collection;
import java.util.HashSet;

/**
 * The AbstractSubject class provides a base implementation of methods required for a class to be
 * a Subject for the Observer pattern.
 * <p>
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class AbstractSubject implements Subject
{
  private final Collection<OptimizationObserver> observers = new HashSet<>();

  @Override public void addOptimizationObserver(OptimizationObserver observer)
  {
    observers.add(observer);
  }

  @Override public void notifyObservers(EventType type, double value, String text)
  {
    observers.forEach(observer -> observer.handleEvent(this, type, value, text));
  }

  @Override public void removeOptimizationObserver(OptimizationObserver observer)
  {
    observers.remove(observer);
  }
}
