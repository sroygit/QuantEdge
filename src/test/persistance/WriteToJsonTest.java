/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import model.Portfolio;
import model.Stock;

public class WriteToJsonTest {

    @Test
    public void testEmptyPortfolio() throws IOException {
        Portfolio testPF = new Portfolio();
        WriteToJson testWriter = new WriteToJson("./data/testEmptyPortfolio.json");
        testWriter.openWriter();
        testWriter.write(testPF);
        testWriter.close();

        ReadFromJson reader = new ReadFromJson("./data/testEmptyPortfolio.json");
        testPF = reader.read();
        assertEquals(0, testPF.getPortfolioSize());

    }

    @Test
    public void testGeneralPortfolio() throws IOException {
        Portfolio testPF = new Portfolio();
        testPF.buyStock("APPLE", 20, 100);
        testPF.buyStock("ORANGE", 20, 50);
        WriteToJson testWriter = new WriteToJson("./data/testGeneralPortfolio.json");
        testWriter.openWriter();
        testWriter.write(testPF);
        testWriter.close();

        ReadFromJson reader = new ReadFromJson("./data/testGeneralPortfolio.json");
        testPF = reader.read();
        assertEquals(2, testPF.getPortfolioSize());
        Stock testStock1 = testPF.getStock(0);
        Stock testStock2 = testPF.getStock(1);

        assertEquals("APPLE", testStock1.getTicker());
        assertEquals(100, testStock1.getPrice());
        assertEquals(20, testStock1.getShares());

        assertEquals("ORANGE", testStock2.getTicker());
        assertEquals(50, testStock2.getPrice());
        assertEquals(20, testStock2.getShares());

    }

}
