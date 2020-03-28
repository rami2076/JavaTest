package domain.bigidecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimal_Test_Scale {

    public static void main(String[] args) {

        System.out.println(BigDecimal.ZERO.setScale(2).toString());

        BigDecimal a = new BigDecimal("1.1111").setScale(2, RoundingMode.HALF_UP);

        BigDecimal b = new BigDecimal("1.55555555").setScale(0, RoundingMode.HALF_UP);

        System.out.println("a" + a);
        System.out.println("b" + b);

        System.out.println(BigDecimal.ZERO.setScale(2).toString());

    }
}
