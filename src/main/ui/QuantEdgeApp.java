/* CITATION: The code was modelled after JsonSerializationDemo 
 * initialled commited by Paul Carter of UBC CS on Oct 17, 2020.
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Event;
import model.EventLog;
import model.Portfolio;
import model.Stock;
import persistance.ReadFromJson;
import persistance.WriteToJson;

// QuantEdge application class, which runs the terminal UI and loads a new QuantEdgeGuI object.
public class QuantEdgeApp {

    private Portfolio portfolio;
    private Scanner input;
    private WriteToJson writeToJson;
    private ReadFromJson readFromJson;
    private static final String LOCATION = "./data/portfolio.json";

    // EFFECTS: Constructs the QuantEdge Application
    public QuantEdgeApp(Portfolio portfolio) {
        this.portfolio = portfolio;
        //portfolio = new Portfolio();
        //Start the GUI 
        //new QuantEdgeGUI(portfolio);

        //Terminal settings 
        input = new Scanner(System.in);
        writeToJson = new WriteToJson(LOCATION); // Initialize writeToJson
        readFromJson = new ReadFromJson(LOCATION); // Initialize readFromJson
        runQuantEdge();

    }

    // MODIFIES: this
    // EFFECTS: processes user commands
    private void runQuantEdge() {
        boolean isQuit = false;
        String command = null;

        while (!isQuit) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                System.out.println("Type yes to save portfolio and no to skip");
                command = input.next();
                if (command.equals("yes")) {
                    savePortfolio();
                    printEventLog();

                }
                isQuit = true;
            } else {
                checkCommand(command);
            }
        }

        System.out.println("Thank you for using QuantEdge!");
    }

    // MODIFIES: this
    // EFFECTS: takes and checks userCommand to perform tasks.
    private void checkCommand(String userCommand) {
        if (userCommand.equals("1")) {
            viewPortfolio();
        } else if (userCommand.equals("2")) {
            System.out.println("Please type the ticker of the Stock to Buy");
            String ticker = input.next();
            System.out.println("Please type the number of the Shares purchased");
            int numberOfShares = Integer.valueOf(input.next());
            System.out.println("Please type the buying price of the Stock");
            int buyPrice = Integer.valueOf(input.next());

            portfolio.buyStock(ticker, numberOfShares, buyPrice);

        } else if (userCommand.equals("3")) {
            System.out.println("Please type the ticker of the Stock to Sell");
            String ticker = input.next();
            System.out.println("Please type the number of the Shares sold");
            int numberOfShares = Integer.valueOf(input.next());
            System.out.println("Please type the selling price of the Stock");
            int sellPrice = Integer.valueOf(input.next());

            portfolio.sellStock(ticker, numberOfShares, sellPrice);
        } else if (userCommand.equals("4")) {
            loadPortfolio();
        }
    }

    // EFFECTS: prints the list of options available to the investor
    private void menu() {
        System.out.println("Welcome to QuantEdge, choose from the options:");
        System.out.println("Type 1 to view your Portfolio");
        System.out.println("Type 2 to buy Stocks");
        System.out.println("Type 3 to sell Stocks");
        System.out.println("Type 4 to load saved portfolio");
        System.out.println("Type quit to close QuantEdge");

    }

    // EFFECTS: shows the list of Stock objects in the portfolio, along
    // the number of shares and average Price.
    private void viewPortfolio() {
        ArrayList<Stock> myPortfolio = portfolio.getPortfolioList();

        if (portfolio.getPortfolioSize() == 0) {
            System.out.println("No Stocks in portfolio");

        } else {
            for (Stock stock : myPortfolio) {
                System.out.println("Ticker:" + stock.getTicker());
                System.out.println("Shares:" + stock.getShares());
                System.out.println("AvgPrice:" + stock.getPrice());
                System.out.println("_________");
            }
        }
    }

    // EFFECTS: saves the portfolio to file
    private void savePortfolio() {
        try {
            writeToJson.openWriter();
            writeToJson.write(portfolio);
            writeToJson.close();
            System.out.println("Saved portfolio");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPortfolio() {
        try {
            portfolio = readFromJson.read();
            System.out.println("Loaded File");
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints all the logs from eventlog
    private void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("\n=== QuantEdge Event Logger ===");
        for (Event event : eventLog) {
            System.out.println(event);
        }
        System.out.println("=================");
    }

}
