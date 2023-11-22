package series;

import java.math.BigDecimal;

/**
 * Calculates Euler number with given precision
 */
public interface EulerNumberComputer {

    BigDecimal zero = new BigDecimal(0);

    BigDecimal one = new BigDecimal(1);

    /**
     * @param precision required number of digits after point
     * @param terms required number of terms
     * @return Euler number with given number of digits
     */
    BigDecimal calculateEulerNumber(int terms, int precision);

}
