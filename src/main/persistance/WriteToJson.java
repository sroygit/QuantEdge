/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package persistance;

import org.json.JSONObject;

import model.Portfolio;
import java.io.*;

// Respresents a writer which writes JSON representation of portfolio to file
public class WriteToJson {
    private String location;
    private static final int INDENT = 4;
    private PrintWriter writer;

    // EFFECTS: constructs writer which writes to the file at location.
    public WriteToJson(String location) {
        this.location = location;

    }

    // MODIFIES: this
    // EFFECTS: opens the writer. Throws FileNotFoundException if destination file
    // cannot be opened.
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(location));

    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of a Portfolio object to file opened.
    public void write(Portfolio portfolio) {
        JSONObject json = portfolio.toJson();
        String jsonFormatted = json.toString(INDENT);
        writer.print(jsonFormatted);

    }

    // MODIFIES: this
    // EFFECTS: closes the writer.
    public void close() {
        writer.close();

    }

}
