
public class Driver
{
  public static void main(String[] args) {
    OptimizationMethod d = new DichotomousSearchMethod();
    
    System.out.println(d.minimize((x) -> x*x + 2*x, -3, 5));
    
    System.out.print("Golden Section: ");
    
    OptimizationMethod g = new GoldenSectionMethod();
    
    System.out.println(g.minimize((x) -> x*x + 2*x, -3, 5));
    
  }
}
