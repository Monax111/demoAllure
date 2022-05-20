import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Integration")
class GeneratorTest {

    @Test
    void countPositiveEqualsCountNegative() {
        double result = new Generator().execute(1000);
        System.out.println(result);
        Assertions.assertTrue(result > 0.9);
        Assertions.assertTrue(result < 1.1);
    }

}