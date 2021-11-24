import java.util.function.DoubleUnaryOperator;

public interface OptimizationMethod
{
  public double minimize(DoubleUnaryOperator function, double a1, double b1);
}
