import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Add Two Strings")
public class AddTwoStringsTest {

    private AddTwoStrings sut;

    @BeforeEach
    public void beforeEach() {
        sut = new AddTwoStrings();
    }

    @DisplayName("when neither string has a decimal")
    @Test
    public void neitherStringHasADecimal() {
        String output = sut.addTwoStrings("1", "1");

        Assertions.assertEquals(output, "2", "1 + 1 = 2");
    }

    @DisplayName("when both strings are smaller than 1")
    @Test
    public void bothStringAreSmallerThanOne() {
        String output = sut.addTwoStrings(".1", ".1");

        Assertions.assertEquals(output, ".2", ".1 + .1 = .2");
    }

    @DisplayName("when one string has a decimal value")
    @Test
    public void oneStringHasDecimal() {
        String output = sut.addTwoStrings("1.2", "1");

        Assertions.assertEquals(output, "2.2", "1.2 + 1 = 2.2");
    }

    @DisplayName("when there is a carry with no decimal")
    @Test
    public void carryWhenStringsHaveNoDecimal() {
        String output = sut.addTwoStrings("6", "5");

        Assertions.assertEquals(output, "11", "6 + 5 = 11");
    }

    @DisplayName("when there is a carry in the decimal")
    @Test
    public void carryInTheDecimal() {
        String output = sut.addTwoStrings("9.95", ".2");

        Assertions.assertEquals(output, "10.15", "9.95 + .2 = 10.15");
    }

    @DisplayName("when string is larger than Integer.MAX")
    @Test
    public void stringIsLargerThanLargestInt() {
        String output = sut.addTwoStrings("2147483648", "1");

        Assertions.assertEquals(output, "2147483649", "2147483648 + 1 = 2147483649");
    }
}
