package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun mockedBun;

    @Mock
    Ingredient mockedIngredient1;

    @Mock
    Ingredient mockedIngredient2;

    @Mock
    Ingredient mockedIngredient3;

    Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(mockedBun);
        burger.addIngredient(mockedIngredient1);
        burger.addIngredient(mockedIngredient2);
        burger.addIngredient(mockedIngredient3);
    }

    @Test
    public void checkThatGetPriceReturnsCorrectPrice() {
        Mockito.when(mockedBun.getPrice()).thenReturn(10.0f);
        Mockito.when(mockedIngredient1.getPrice()).thenReturn(1.0f);
        Mockito.when(mockedIngredient2.getPrice()).thenReturn(2.0f);
        Mockito.when(mockedIngredient3.getPrice()).thenReturn(4.0f);

        float actualPrice = burger.getPrice();
        float expectedPrice = 27.0f;

        assertEquals(expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void checkThatAddIngredientAddIngredientsInOrder() {
        List<Ingredient> expected = new ArrayList<>(3);
        expected.add(mockedIngredient1);
        expected.add(mockedIngredient2);
        expected.add(mockedIngredient3);

        assertEquals(burger.ingredients, expected);
    }

    @Test
    public void removeTest1() {
        burger.removeIngredient(2);

        List<Ingredient> expected = new ArrayList<>(2);
        expected.add(mockedIngredient1);
        expected.add(mockedIngredient2);

        assertEquals(burger.ingredients, expected);
    }

    @Test
    public void removeTest2() {
        burger.removeIngredient(0);

        List<Ingredient> expected = new ArrayList<>(2);
        expected.add(mockedIngredient2);
        expected.add(mockedIngredient3);

        assertEquals(burger.ingredients, expected);
    }

    @Test
    public void moveTest() {
        burger.moveIngredient(2, 0);

        List<Ingredient> expected = new ArrayList<>(3);
        expected.add(mockedIngredient3);
        expected.add(mockedIngredient1);
        expected.add(mockedIngredient2);

        assertEquals(burger.ingredients, expected);
    }

    @Test
    public void receipt() {
        Mockito.when(mockedBun.getName()).thenReturn("Булочка");
        Mockito.when(mockedBun.getPrice()).thenReturn(10.0f);

        Mockito.when(mockedIngredient1.getName()).thenReturn("Котлетка");
        Mockito.when(mockedIngredient1.getPrice()).thenReturn(1.0f);
        Mockito.when(mockedIngredient1.getType()).thenReturn(IngredientType.FILLING);

        Mockito.when(mockedIngredient2.getName()).thenReturn("Какой-то непонятный салатик");
        Mockito.when(mockedIngredient2.getPrice()).thenReturn(2.0f);
        Mockito.when(mockedIngredient2.getType()).thenReturn(IngredientType.FILLING);

        Mockito.when(mockedIngredient3.getName()).thenReturn("Кетчуп");
        Mockito.when(mockedIngredient3.getPrice()).thenReturn(4.0f);
        Mockito.when(mockedIngredient3.getType()).thenReturn(IngredientType.SAUCE);

        String expected = readFileFromResource("/burger_receipt_1.txt");
        assertEquals(expected, burger.getReceipt());
    }

    private static String readFileFromResource(String path) {
        InputStream inputStream = BurgerTest.class.getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }
}