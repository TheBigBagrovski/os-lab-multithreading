package series;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MTEulerTest {

    public static final String EULER_100000_DIGITS = "src/test/resources/euler100000digits.txt";

    @Test
    public void testEuler_100digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("100000 - file not found");
        }
        expected = expected.substring(0, 100);
        EulerNumberComputer comp1 = new MultiThreadedEulerComputer(1);
        EulerNumberComputer comp2 = new MultiThreadedEulerComputer(4);
        EulerNumberComputer comp3 = new MultiThreadedEulerComputer(12);
        String actual1 = comp1.calculateEulerNumber(30, 100).toString().substring(0, 100);
        String actual2 = comp2.calculateEulerNumber(30, 100).toString().substring(0, 100);
        String actual3 = comp3.calculateEulerNumber(30, 100).toString().substring(0, 100);
        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);
        Assertions.assertEquals(expected, actual3);
    }

    @Test
    public void testEuler_1000digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("100000 - file not found");
        }
        expected = expected.substring(0, 1000);
        EulerNumberComputer comp1 = new MultiThreadedEulerComputer(1);
        EulerNumberComputer comp2 = new MultiThreadedEulerComputer(4);
        EulerNumberComputer comp3 = new MultiThreadedEulerComputer(12);
        String actual1 = comp1.calculateEulerNumber(200, 1001).toString().substring(0, 1000);
        String actual2 = comp2.calculateEulerNumber(200, 1001).toString().substring(0, 1000);
        String actual3 = comp3.calculateEulerNumber(200, 1001).toString().substring(0, 1000);
        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);
        Assertions.assertEquals(expected, actual3);
    }

    @Test
    public void testEuler_10000digits() {
        String expected = "";
        try {
            expected = Files.readString(Paths.get(EULER_100000_DIGITS), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("10000 - file not found");
        }
        expected = expected.substring(0, 10000);
        EulerNumberComputer comp1 = new MultiThreadedEulerComputer(1);
        EulerNumberComputer comp2 = new MultiThreadedEulerComputer(4);
        EulerNumberComputer comp3 = new MultiThreadedEulerComputer(12);
        String actual1 = comp1.calculateEulerNumber(2000, 10002).toString().substring(0, 10000);
        String actual2 = comp2.calculateEulerNumber(2000, 10002).toString().substring(0, 10000);
        String actual3 = comp3.calculateEulerNumber(2000, 10002).toString().substring(0, 10000);
        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);
        Assertions.assertEquals(expected, actual3);
    }

}
