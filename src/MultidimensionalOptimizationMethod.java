import java.util.function.Function;

public interface MultidimensionalOptimizationMethod
{
  public double[] minimize(MultivariableFunction f);
}
