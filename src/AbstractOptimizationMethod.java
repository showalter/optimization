import java.util.Collection;
import java.util.HashSet;
import java.util.function.DoubleUnaryOperator;

public abstract class AbstractOptimizationMethod implements OptimizationMethod
{
  private final Collection<OptimizationObserver> observers = new HashSet<>();

  public abstract double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1);

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
