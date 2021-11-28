import javax.swing.*;
import java.util.function.DoubleUnaryOperator;

public class NewtonsMethod implements OptimizationMethodWithDerivatives
{
  private double start;
  private double delta;

  public NewtonsMethod(double start, double delta) {
    this.start = start;
    this.delta = delta;
  }

  private boolean isLocalMin(double lambda, DoubleUnaryOperator fPrime) {
    return Math.abs(fPrime.applyAsDouble(lambda)) == 0;
  }

  @Override public double minimize(DoubleUnaryOperator f, DoubleUnaryOperator fPrime,
      DoubleUnaryOperator fDoublePrime, double a1, double b1)
  {

    double lambda = start;
    double lastLambda = 0;
    double beforeLastLambda = 0;
    double stop = 100;

    if (isLocalMin(lambda, fPrime)) {
      return lambda;
    }

    for (int i = 0; i < stop; i++) {
      System.out.println(i);
      beforeLastLambda = lastLambda;
      lastLambda = lambda;

      lambda = lambda - (fPrime.applyAsDouble(lambda) / fDoublePrime.applyAsDouble(lambda));

      System.out.println(lambda);

      if (Math.abs(lambda - lastLambda) < delta || isLocalMin(lambda, fPrime)) {
        return lambda;
      }

      if (i > 2 && Math.abs(beforeLastLambda - lambda) < 1e-12) {
        throw new RuntimeException("Does not converge");
      }

    }

    // Should never get here
    return Double.NaN;
  }
}
