package model;

import org.json.JSONObject;

import persistance.ParseAble;

// Represents a Stock which has been purchased having a ticker for identification, 
// average buying price per share and number of shares.
public class Stock implements ParseAble {
    private String ticker; // represents the id in stock market
    private int averagePrice; // the average buying price per share
    private int numShares; // the number of shares owned
    private int profitLoss; // the total profit/ loss from transactions of this stock.
    private int assetValue; // the value of all the shares of the stock combined.

    /*
     * REQUIRES: stockTicker has a non-zero length and buyPrice >= 0 and numShares
     * >= 0.
     * EFFECTS: ticker of the Stock object created will be set to stockTicker.
     * The buyprice of the stock will be set to averagePrice when created.
     * The numShares will be set to the numberOfShares.
     */
    public Stock(String stockTicker, int numberOfShares, int buyPrice) {
        this.ticker = stockTicker;
        this.numShares = numberOfShares;
        this.averagePrice = buyPrice;
        this.assetValue = numberOfShares * buyPrice;
        this.profitLoss = 0;

    }

    public int getPrice() {
        return averagePrice;
    }

    public String getTicker() {
        return ticker;
    }

    public int getShares() {
        return numShares;
    }

    public void setTicker(String stockTicker) {
        this.ticker = stockTicker;
    }

    public int getProfitLoss() {
        return profitLoss;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ticker", ticker);
        json.put("averagePrice", averagePrice);
        json.put("numberOfShares", numShares);
        return json;
    }

    /*
     * REQUIRES: numberofShares >= 0.
     * MODIFIES: this
     * EFFECTS: adds shares to numShares
     * calculates and updates the averagePrice from buyPrice, and
     * updated numShares is returned.
     * 
     */
    public int addShares(int numberOfShares, int buyPrice) {
        numShares += numberOfShares;
        assetValue += (numberOfShares * buyPrice);
        averagePrice = assetValue / numShares;
        return numShares;
    }

    /*
     * REQUIRES: numShares >= numberofShares >= 0.
     * MODIFIES: this
     * EFFECTS: removes shares from numShares and updated numShares is returned and
     * updates profitLoss.
     * 
     */
    public int sellShares(int numberOfShares, int sellPrice) {
        numShares -= numberOfShares;
        int profitLossPerShare = sellPrice - averagePrice;
        profitLoss = (profitLossPerShare * numberOfShares);
        return numShares;
    }

}
