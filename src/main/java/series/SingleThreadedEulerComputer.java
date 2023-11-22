package series;

import java.math.BigDecimal;
import java.math.MathContext;

public class SingleThreadedEulerComputer implements EulerNumberComputer{

    @Override
    public BigDecimal calculateEulerNumber(int terms, int precision) {
        final MathContext mc = new MathContext(precision);
        BigDecimal result = one;
        BigDecimal lastFactorial = one;
        BigDecimal newFactorial;
        for (int i = 1; i < terms; i++) {
            newFactorial = lastFactorial.multiply(BigDecimal.valueOf(i));
            BigDecimal coef = one.divide(newFactorial, mc);
            lastFactorial = newFactorial;
            result = result.add(coef);
        }
        return result;
    }

}
