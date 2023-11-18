
public class Punto3 extends Punto2 {

    private int z;

    public Punto3(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    //Primo equals: getClass()
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

    //Secondo equals: instanceof
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Punto3))
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

    //Terzo equals: instanceof + differenziazione controlli
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (obj instanceof Punto3) {
            Punto3 other = (Punto3) obj;
            if (getX() != other.getX())
                return false;
            if (getY() != other.getY())
                return false;
            if (z != other.z)
                return false;
            return true;
        } else {
            return super.equals(obj);
        }
    }

}
