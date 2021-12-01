/**
 * The TestPointObserver is an OptimizationObserver that prints out the text and the value only of test point events.
 *
 * This work complies with the JMU Honor Code.
 *
 * @author Ryan Showalter
 * @version 1
 */
public class TestPointObserver implements OptimizationObserver {
    @Override
    public void handleEvent(OptimizationMethod subject, EventType type, double value, String text) {
        if (type == EventType.TEST_POINT) {
            System.out.printf("%s %f\n", text, value);
        }
    }
}
