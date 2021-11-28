import java.util.function.DoubleUnaryOperator;

public class DichotomousSearchMethod implements OptimizationMethod
{

  @Override
  public double minimize(DoubleUnaryOperator function, double a1, double b1)
  {
    double e = 0.01;
    double l = 0.2;
    
    double a = a1;
    double b = b1;
    
    while (b - a > l) {
      double lambda = (a + b) / 2 - e;
      double mu = (a + b) / 2 + e;
      
      if (function.applyAsDouble(lambda) < function.applyAsDouble(mu)) {
        b = mu;
      } else {
        a = lambda;
      }
      
      System.out.println(b - a);
    }

    return (a + b) / 2;
  }

}
