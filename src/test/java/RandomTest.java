import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomTest {

    @Test
    public void whenIntLessZeroIsNegative(){
        Random random = new Random();
        random.i = -1;
        Assertions.assertTrue(random.negative());
        Assertions.assertFalse(random.positive());
    }
    @Test
    public void whenIntGreaterZeroIsNegative(){
        Random random = new Random();
        random.i = 1;
        Assertions.assertFalse(random.negative());
        Assertions.assertTrue(random.positive());
    }
    @Test
    public void whenIntEqualsZeroIsNegative(){
        Random random = new Random();
        random.i = 0;
        Assertions.assertFalse(random.negative());
        Assertions.assertFalse(random.positive());
    }

}