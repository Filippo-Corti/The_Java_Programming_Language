public class Punto3 implements Punto {

    private Punto2 p;
    private int z;

    public Punto3(int x, int y, int z) {
        p = new Punto2(x, y);
        this.z = z;
    }

    public int getX() {
        return p.getX();
    }

    public int getY() {
        return p.getY();
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punto3 other = (Punto3) obj;
        if (getX() != other.getX())
            return false;
        if (getY() != other.getY())
            return false;
        if (z != other.z)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Punto3 [x=" + getX() + ", y=" + getY() + ", z=" + z + "]";
    }

}
