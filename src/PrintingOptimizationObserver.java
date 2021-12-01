public class PrintingOptimizationObserver implements OptimizationObserver
{
  @Override public void handleEvent(OptimizationMethod subject, EventType type, double value, String text)
  {
    System.out.printf("%s: %s %f\n", subject.getClass().toString(), text, value);
  }
}
