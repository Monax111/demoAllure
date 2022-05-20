public class Random {

    Integer i = new java.util.Random().nextInt();

    public Boolean positive() {
        return (i > 0);
    }

    public Boolean negative() {
        return (i < 0);
    }

}
