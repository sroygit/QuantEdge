package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {
    private Stock apple;

    @BeforeEach
    public void runBefore() {
        apple = new Stock("AAPL", 20, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals("AAPL", apple.getTicker());
        assertEquals(100, apple.getPrice());
        assertEquals(20, apple.getShares());
    }

    @Test
    public void testAddShares() {
        apple.addShares(20, 300);
        assertEquals(200, apple.getPrice());
        assertEquals(40, apple.getShares());

        apple.setTicker("APPLE");
        assertEquals("APPLE", apple.getTicker());
    }

    @Test
    public void testAddSharesMultipleTimes() {
        apple.addShares(20, 300);
        assertEquals(200, apple.getPrice());
        assertEquals(40, apple.getShares());

        apple.addShares(40, 500);
        assertEquals(350, apple.getPrice());
        assertEquals(80, apple.getShares());
    }

    @Test
    public void testSellShares() {
        apple.sellShares(10, 200);
        assertEquals(100, apple.getPrice());
        assertEquals(10, apple.getShares());
        assertEquals(1000, apple.getProfitLoss());

    }

    @Test
    public void testSellSharesMultipleTimes() {
        apple.sellShares(10, 200);
        assertEquals(100, apple.getPrice());
        assertEquals(10, apple.getShares());
        assertEquals(1000, apple.getProfitLoss());

        apple.sellShares(5, 300);
        assertEquals(100, apple.getPrice());
        assertEquals(5, apple.getShares());
        assertEquals(1000, apple.getProfitLoss());

    }

}
