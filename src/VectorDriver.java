public class VectorDriver
{

  public static void main(String[] args) {
    double[] components = { 1., 2., 3.};

    Vector v = new Vector(components);

    System.out.println(v);

    components[1] = -5.;

    System.out.println(v);

    System.out.println(v.scale(2));

    System.out.println(v);
  }
}
