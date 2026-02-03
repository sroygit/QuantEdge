/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import model.Portfolio;
import model.Stock;

import java.io.IOException;
import java.util.ArrayList;

public class ReadFromJsonTest extends JsonTest {

    @Test
    public void testReaderNoFile() {
        ReadFromJson testReader = new ReadFromJson("./data/noFile.json");
        try {
            Portfolio testPF = testReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyPortfolio() {
        ReadFromJson testReader = new ReadFromJson("./data/testEmptyPortfolio.json");
        try {
            Portfolio testPF = testReader.read();
            assertEquals(0, testPF.getPortfolioSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        ReadFromJson testReader = new ReadFromJson("./data/testGeneralPortfolio.json");
        try {
            Portfolio testPF = testReader.read();
            ArrayList<Stock> testPortfolio = testPF.getPortfolioList();
            Stock stock1 = testPortfolio.get(0);
            Stock stock2 = testPortfolio.get(1);
            assertEquals(2, testPortfolio.size());
            checkStock(stock1, "APPLE", 100, 20);
            checkStock(stock2, "ORANGE", 50, 20);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
