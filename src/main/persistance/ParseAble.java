/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package persistance;

import org.json.JSONObject;

public interface ParseAble {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
