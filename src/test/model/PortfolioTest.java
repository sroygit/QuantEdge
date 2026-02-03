package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortfolioTest {
    private Portfolio testPortfolio;

    @BeforeEach
    public void runBefore() {
        testPortfolio = new Portfolio();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testPortfolio.getPortfolioSize());
    }

    @Test
    public void testBuyStock() {
        testPortfolio.buyStock("Apache", 10, 10);
        assertEquals(1, testPortfolio.getPortfolioSize());

        testPortfolio.buyStock("Apache", 10, 10);
        assertEquals(1, testPortfolio.getPortfolioSize());
        Stock testStock1 = testPortfolio.getStock(0);
        assertEquals(20, testStock1.getShares());

        testPortfolio.buyStock("Apple", 20, 20);
        assertEquals(2, testPortfolio.getPortfolioSize());
        Stock testStock2 = testPortfolio.getStock(1);
        assertEquals(20, testStock2.getShares());

    }

    @Test
    public void testSellStock() {
        testPortfolio.buyStock("Apache", 10, 10);
        testPortfolio.sellStock("Apache", 5, 10);
        Stock testStock1 = testPortfolio.getStock(0);
        assertEquals(5, testStock1.getShares());
        assertEquals(1, testPortfolio.getPortfolioSize());

        testPortfolio.sellStock("Apache", 5, 10);
        assertEquals(1, testPortfolio.getPortfolioSize());
        assertEquals(0, testStock1.getShares());

        testPortfolio.buyStock("Apple", 20, 20);
        testPortfolio.sellStock("Apple", 10, 20);
        Stock testStock2 = testPortfolio.getStock(1);
        assertEquals(10, testStock2.getShares());
        assertEquals(2, testPortfolio.getPortfolioSize());

        ArrayList<Stock> testStocks = testPortfolio.getPortfolioList();
        assertEquals(testStock1, testStocks.get(0));
        assertEquals(testStock2, testStocks.get(1));

    }

}
