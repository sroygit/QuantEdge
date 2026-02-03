package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.ParseAble;

/*Representation of a collection of stocks which the investor has purchased.

*/
public class Portfolio implements ParseAble {

    private ArrayList<Stock> stocks; // a list containing stock objects

    // EFFECTS: Constructs an empty collection of stock objects
    public Portfolio() {

        stocks = new ArrayList<>();

    }

    /*
     * REQUIRES: stockTicker has a non-zero length and buyPrice >= 0 and
     * numberOfShares
     * >= 0.
     * MODIFIES: this
     * EFFECTS: adds a new Stock object, if the stockTicker is not in portfolio.
     * adds new Shares, if stockTicker is already in the list.
     */
    public void buyStock(String stockTicker, int numberOfShares, int buyPrice) {

        boolean stockExists = false;
        for (Stock stock : stocks) {

            if (stock.getTicker().equals(stockTicker)) {

                stockExists = true;
                stock.addShares(numberOfShares, buyPrice);
                EventLog.getInstance().logEvent(new Event(
                        "Added :" + stockTicker + " ,Quantity " + numberOfShares + ", price:" + buyPrice));
            }

        }

        if (!stockExists) {

            Stock newStock = new Stock(stockTicker, numberOfShares, buyPrice);
            stocks.add(newStock);

            EventLog.getInstance().logEvent(new Event(
                    "New Stock:" + stockTicker + " ,Quantity " + numberOfShares + ", price:" + buyPrice));

        }

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stocks", stocksToJson());
        return json;
    }

    // EFFECTS: returns things in this portfolio as a JSON array
    private JSONArray stocksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Stock s : stocks) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    /*
     * REQUIRES: stockTicker has a non-zero length and is in portfolio
     * and sellPrice >= 0 and numberOfShares >= 0.
     * MODIFIES: this
     * EFFECTS: removes shares from stockTicker in the list. Retains the stock
     * ticker
     * even if all shares are sold.
     */

    public void sellStock(String stockTicker, int numberOfShares, int sellPrice) {
        for (Stock stock : stocks) {

            if (stock.getTicker().equals(stockTicker)) {
                stock.sellShares(numberOfShares, sellPrice);
                EventLog.getInstance().logEvent(new Event(
                        "Sold Shares:" + stockTicker + " ,Quantity " + numberOfShares + ", price:" + sellPrice));
            }
        }
    }

    public int getPortfolioSize() {
        return stocks.size();
    }

    public Stock getStock(int index) {
        return stocks.get(index);
    }

    public ArrayList<Stock> getPortfolioList() {
        return stocks;
    }

}
