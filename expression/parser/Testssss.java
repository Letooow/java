package expression.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import expression.*;

public class Testssss {


    @Test
    public void testBase(){
        valid(0, 0);
        inValid(1);
        valid(32000000, 2);
        valid(121500000, 3);
        valid(341333333, 4);
        inValid(5);
        inValid(6);
        inValid(7);
        inValid(8);

    }



    private void valid(Object expected, int input) {
        int res = ExpressionParser.parse(new StringSource("1000000*x*x*x*x*x/(x-1)")).evaluate(input);
        System.out.println(res);
        Assertions.assertEquals(expected, res);
    }

    private void inValid(int input){
        try {
            ExpressionParser.parse(new StringSource("1000000*x*x*x*x*x/(x-1)")).evaluate(input);
            Assertions.fail("Invalid output");
        } catch (ExpressionsExceptions exp) {
            System.out.println(exp.getMessage());
        }
    }

}
