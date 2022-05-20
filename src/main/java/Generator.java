public class Generator {

    public double execute(Integer max) {
        int countPositive = 0;
        int countNegative = 0;
        for (int i = 0; i < max; i++) {
            Random random = new Random();
            if (random.negative()) {
                countNegative++;
            }
            if (random.positive()) {
                countPositive++;
            }
        }
        return (double) countNegative / countPositive;
    }

}
