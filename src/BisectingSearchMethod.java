import java.util.function.DoubleUnaryOperator;

public class BisectingSearchMethod implements OptimizationMethodWithDerivatives
{

  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime, DoubleUnaryOperator fDoublePrime, double a1,
      double b1)
  {

    double l = 0.2;
    int n = 0;
    double halfToTheNth = 1;

    double a = a1;
    double b = b1;

    while (halfToTheNth > l / (b - a)) {
      n++;
      halfToTheNth *= 0.5;
    }



    for (int i = 0; i < n; i++) {
      double lambda = (a + b) / 2;
      double fPrimeAtLambda = fPrime.applyAsDouble(lambda);

      if (fPrimeAtLambda == 0) {
        return lambda;
      } else if (fPrimeAtLambda > 0) {
        b = lambda;
      } else {
        a = lambda;
      }
    }

    return (a + b) / 2;
  }
}
