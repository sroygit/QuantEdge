/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package persistance;

import org.json.JSONArray;
import org.json.JSONObject;
import model.Portfolio;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a portfolio from JSON file
public class ReadFromJson {
    private String location;

    // EFFECTS: constructs a reader to from from the file at location
    public ReadFromJson(String location) {
        this.location = location;

    }

    // EFFECTS: reads portfolio from file and returns it.
    // throws IOException if any issue with reading data from file
    public Portfolio read() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);

    }

    // EFFECTS: reads location file as string and returns it.
    private String readFile(String location) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();

    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        Portfolio portfolio = new Portfolio();
        addStocks(portfolio, jsonObject);
        return portfolio;

    }

    // MODIFIES: portfolio
    // EFFECTS: parses list of stocks from JSON object and adds to workroom
    private void addStocks(Portfolio portfolio, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stocks");
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStock(portfolio, nextStock);
        }

    }

    // MODIFIES: portfolio
    // EFFECTS: parses stock from JSON object and adds to portfolio
    private void addStock(Portfolio portfolio, JSONObject jsonObject) {
        String ticker = jsonObject.getString("ticker");
        int numShares = jsonObject.getInt("numberOfShares");
        int avgPrice = jsonObject.getInt("averagePrice");

        portfolio.buyStock(ticker, numShares, avgPrice);

    }

}
