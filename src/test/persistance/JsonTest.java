package persistance;

import model.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStock(Stock stock, String ticker, int avgPrice, int numShares) {
        assertEquals(ticker, stock.getTicker());
        assertEquals(avgPrice, stock.getPrice());
        assertEquals(numShares, stock.getShares());
    }
}
