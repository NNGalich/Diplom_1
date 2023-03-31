package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunComplexTest {

    private final String name;
    private final float price;

    private Bun bun;

    public BunComplexTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getPotentialNames() {
        return new Object[][] {
                { "Булочка с кунжутом", 10.0f },
                { "Белая итальянская булочка", 10.33f }
        };
    }

    @Test
    public void checkThatGetNameReturnsCorrectString() {
        assertEquals(name, bun.getName());
    }

    @Test
    public void checkThatGetPriceReturnsCorrectFloat() {
        assertEquals(price, bun.getPrice(), 1e-5);
    }

}
