public class OuterClass {

    private String s;

    public OuterClass(String s) {
        this.s = s;
    }

    public MyInterface getInner() { //Già qua non esiste LocalInnerClass, quindi devo restituire object oppure un'interfaccia

        class LocalInnerClass implements MyInterface { //Qua non può andare "public" !!!

            @Override
            public String saluta() {
                return "La mia classe esterna si chiama: " + s;
            }
        }

        return new LocalInnerClass();
    }

    public static void main(String[] args) {
        OuterClass o = new OuterClass("Pippo");

        System.out.println(o.getInner().saluta());
    }


}
