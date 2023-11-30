public class TipoMutabile implements Cloneable {
    
    private String s;

    public TipoMutabile(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public Object clone(){
        try {
        return super.clone();
        } catch (CloneNotSupportedException e) {
            return new TipoMutabile(s);
        }
    }

}
