package series;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class STEulerTest {

    public static final String EULER_100000_DIGITS = "src/test/resources/euler100000digits.txt";

    @Test
    public void testEuler_100digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("100000 - file not found");
        }
        expected = expected.substring(0, 102);
        EulerNumberComputer comp = new SingleThreadedEulerComputer();
        String actual = comp.calculateEulerNumber(80, 100).toString().substring(0, 102);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEuler_1000digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("100000 - file not found");
        }
        expected = expected.substring(0, 1002);
        EulerNumberComputer comp = new SingleThreadedEulerComputer();
        String actual = comp.calculateEulerNumber(500, 1000).toString().substring(0, 1002);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEuler_10000digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("10000 - file not found");
        }
        expected = expected.substring(0, 10002);
        EulerNumberComputer comp = new SingleThreadedEulerComputer();
        String actual = comp.calculateEulerNumber(4000, 10000).toString().substring(0, 10002);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEuler_100000digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("100000 - file not found");
        }
        EulerNumberComputer comp = new SingleThreadedEulerComputer();
        String actual = comp.calculateEulerNumber(30000, 100000).toString().substring(0, 100000);
        Assertions.assertEquals(expected, actual);
    }

}
