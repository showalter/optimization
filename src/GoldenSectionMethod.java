import java.util.function.DoubleUnaryOperator;

public class GoldenSectionMethod implements OptimizationMethod
{

  @Override
  public double minimize(DoubleUnaryOperator function, double a1, double b1)
  {
    double l = 0.2;
    
    double a = a1;
    double b = b1;
    
    double alpha = 0.618;
    
    while (b - a > l) {
      double lambda = a + (1 - alpha) * (b - a);
      double mu = a + alpha * (b - a);
      
      if (function.applyAsDouble(lambda) > function.applyAsDouble(mu)) {
        a = lambda;
        lambda = mu;
        mu = a + alpha * (b - a);
      } else {
        b = mu;
        mu = lambda;
        lambda = a + (1 - alpha) * (b - a);
      }
    }
    
    return (a + b) / 2;
    
  }

}
