public class OuterClass {

    private int n;
    private InnerClass i;

    public OuterClass(int n, int inner) {
        this.n = n;
        this.i = new InnerClass(inner);
    }

    public InnerClass getInner() {
        return i;
    }

    public class InnerClass {
        private int n;

        public InnerClass(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            //Poiché ci sono due "n", notare la sintassi estesa
            return "Inner Class: " + n + "\t Outer Class: " + OuterClass.this.n;
        }
    }

    public static void main(String[] args) {
        OuterClass o = new OuterClass(1, -1);
        System.out.println(o.getInner());
    }

}
