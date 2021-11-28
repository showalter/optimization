import java.util.function.DoubleUnaryOperator;

public interface OptimizationMethodWithDerivatives
{
  public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime, DoubleUnaryOperator fDoublePrime, double a1, double b1);
}
