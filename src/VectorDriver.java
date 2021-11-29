/**
 * This class is used to test the Vector class.
 * <p>
 * This work complies with the JMU Honor Code
 *
 * @author Ryan Showalter
 * @version 1
 */
public class VectorDriver
{

  /**
   * The entry point of the program
   *
   * @param args the command line arguments (unused)
   */
  public static void main(String[] args)
  {
    double[] components = {1., 2., 3.};

    Vector v = new Vector(components);

    System.out.println(v);

    // Check that the Vector class makes a copy of the argument array
    components[1] = -5.;

    System.out.println(v);

    System.out.println(v.scale(2));

    // Check that the original Vector is not modified
    System.out.println(v);
  }
}
