import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    /**
     *  Estrategia de la prueba
     *
     *  particion:
     *      a < b
     *      a > b
     *      a = b
     */

    // cubre a < b
    @Test
    public void testAMenorQueB(){
        int a = 5;
        int b = 10;
        int maximo = Math.max(a, b);
        assertEquals(b, maximo);
    }

    // cubre a > b
    @Test
    public void testAMayorQueB(){
        int a = 50;
        int b = 10;
        int maximo = Math.max(a, b);
        assertEquals(a, maximo);
    }

    // cubre a = b
    @Test
    public void testAIgualQueB(){
        int c = 10;
        int a = c;
        int b = c;
        int maximo = Math.max(a, b);
        assertEquals(c, maximo);
    }

}
