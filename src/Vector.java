import java.util.Arrays;

public class Vector
{

  private double[] components;

  public Vector(double[] components) {
    this.components = components.clone();
  }

  public double[] toArray() {
    return components.clone();
  }

  public Vector scale(double scalar) {
    double[] newComponents = Arrays.stream(components).map((x) -> scalar * x).toArray();
    return new Vector(newComponents);
  }

  public String toString() {
    return Arrays.toString(components);
  }

  public Vector add(Vector addend) {
    if (this.components.length != addend.components.length) {
      throw new IllegalArgumentException("Vectors must have the same number of components to be added.");
    }

    double[] resultComponents = new double[this.components.length];

    for (int i = 0; i < resultComponents.length; i++) {
      resultComponents[i] = this.components[i] + addend.components[i];
    }

    return new Vector(resultComponents);
  }

  /**
   * Determine whether this Vector is equal to the given Object.
   *
   * This method was generated by IntelliJ IDEA.
   *
   * @param o the other Object
   * @return whether this object is equal to o
   */
  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Vector vector = (Vector) o;
    return Arrays.equals(components, vector.components);
  }

  /**
   * Get the hashCode of this Vector.
   *
   * This method was generated by IntelliJ IDEA.
   *
   * @return the hashCode of this Vector
   */
  @Override public int hashCode()
  {
    return Arrays.hashCode(components);
  }
}