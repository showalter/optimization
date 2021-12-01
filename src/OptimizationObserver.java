public interface OptimizationObserver
{
  public void handleEvent(OptimizationMethod subject, EventType type, double value, String text);

}
