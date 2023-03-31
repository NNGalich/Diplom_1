package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientComplexTest {

    private final String name;
    private final float price;
    private final IngredientType type;

    private Ingredient ingredient;

    public IngredientComplexTest(IngredientType type, String name, float price) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getPotentialNames() {
        return new Object[][] {
                {IngredientType.SAUCE, "Булочка с кунжутом", 10.0f },
                {IngredientType.FILLING, "Белая итальянская булочка", 10.33f }
        };
    }

    @Test
    public void checkThatGetTypeReturnsCorrectType() {
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void checkThatGetNameReturnsCorrectString() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void checkThatGetPriceReturnsCorrectFloat() {
        assertEquals(price, ingredient.getPrice(), 1e-5);
    }

}
