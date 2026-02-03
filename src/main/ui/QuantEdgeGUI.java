package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.*;
import persistance.ReadFromJson;
import persistance.WriteToJson;
import model.EventLog;
import model.Portfolio;
import model.Stock;
import model.Event;

// QuantEdge application GUI class, which runs the GUI of the application.
public class QuantEdgeGUI extends JFrame {
    private Portfolio portfolio;
    private static final String LOCATION = "./data/portfolio.json";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JDesktopPane desktop;
    private JInternalFrame portfolioPanel;
    private JTextArea portfolioDisplay;
    private WriteToJson writeToJson;
    private ReadFromJson readFromJson;
    private JPanel pfPanel;

    //Contructs the GUI and runs other methods
    public QuantEdgeGUI(Portfolio portfolio) {
        this.portfolio = portfolio;
        readFromJson = new ReadFromJson(LOCATION);
        writeToJson = new WriteToJson(LOCATION);

        setupDesktop();
        setupPortfolioPanel();
        setupInputPanel();
        setupWindowListener();

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

    // MODIFIES: this
    // EFFECTS: creates a desktop frame to act as primary frame
    private void setupDesktop() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        setContentPane(desktop);

        setTitle("QuantEdge: Your Edge in Quantitative Investment");
        setSize(WIDTH, HEIGHT);
        centreFrame();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel to display portfolio 
    private void setupPortfolioPanel() {
        portfolioPanel = new JInternalFrame("Your Portfolio", false, false, false, false);
        portfolioPanel.setLayout(new BorderLayout());

        pfPanel = new JPanel();
        pfPanel.setLayout(new BorderLayout());
        pfPanel.setBackground(Color.LIGHT_GRAY);
        pfPanel.setPreferredSize(new Dimension(750, 500));

        portfolioDisplay = new JTextArea();
        portfolioDisplay.setEditable(false);
        portfolioDisplay.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(portfolioDisplay);
        pfPanel.add(scrollPane, BorderLayout.CENTER);

        addButtonsToPanel();
        portfolioPanel.add(pfPanel, BorderLayout.CENTER);

        desktop.add(portfolioPanel);
        portfolioPanel.pack();
        portfolioPanel.setVisible(true);
    }   

    // MODIFIES: this
    // EFFECTS: adds buttons for viewin and loading portfolio
    private void addButtonsToPanel() {
        JButton loadButton = new JButton("Load Portfolio from File");
        JButton viewButton = new JButton("View Portfolio");

        loadButton.addActionListener(e -> loadPortfolio());
        viewButton.addActionListener(e -> viewPortfolio());

        pfPanel.add(loadButton, BorderLayout.SOUTH);
        pfPanel.add(viewButton, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for user inputs.
    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.ORANGE);
        inputPanel.setPreferredSize(new Dimension(400, 300));
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JTextField tickerField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JTextField qtyField = new JTextField(10);

        inputPanel.add(new JLabel("Ticker:"));
        inputPanel.add(tickerField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(qtyField);

        addStockButton(inputPanel, tickerField, priceField, qtyField);
        sellStockButton(inputPanel, tickerField, priceField, qtyField);

        portfolioPanel.add(inputPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for adding Stock
    private void addStockButton(JPanel inputPanel, JTextField tickerField, JTextField priceField, JTextField qtyField) {
        JButton addStockButton = new JButton("Add Stock");
        addStockButton.addActionListener(e -> addStock(tickerField, priceField, qtyField));
        inputPanel.add(new JLabel());
        inputPanel.add(addStockButton);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for selling Stock
    private void sellStockButton(JPanel inputP, JTextField tickerF, JTextField priceF, JTextField qtyF) {
        JButton sellStockButton = new JButton("Sell Stock");
        sellStockButton.addActionListener(e -> sellStock(tickerF, priceF, qtyF));
        inputP.add(new JLabel());
        inputP.add(sellStockButton);
    }

    // MODIFIES: this
    // EFFECTS: buys stocks in the portfolio object.
    private void addStock(JTextField tickerField, JTextField priceField, JTextField qtyField) {
        try {
            String ticker = tickerField.getText();
            int price = Integer.parseInt(priceField.getText());
            int quantity = Integer.parseInt(qtyField.getText());

            portfolio.buyStock(ticker, quantity, price);
            portfolioDisplay.append("Added Stock: " + ticker + ", Price: " + price + ", Quantity: " + quantity + "\n");

            tickerField.setText("");
            priceField.setText("");
            qtyField.setText("");
        } catch (NumberFormatException ex) {
            portfolioDisplay.append("Invalid price or quantity. Please enter valid numbers.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: sells stocks from portfolio object
    private void sellStock(JTextField tickerField, JTextField priceField, JTextField qtyField) {
        try {
            String ticker = tickerField.getText();
            int price = Integer.parseInt(priceField.getText());
            int quantity = Integer.parseInt(qtyField.getText());

            portfolio.sellStock(ticker, quantity, price);
            viewPortfolio();

            tickerField.setText("");
            priceField.setText("");
            qtyField.setText("");
        } catch (NumberFormatException ex) {
            portfolioDisplay.append("Invalid price or quantity. Please enter valid numbers.\n");
        }
    }
    // MODIFIES: this
    // EFFECTS: saves the portfolio to file, gives a prompt.
    
    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        QuantEdgeGUI.this,
                        "Do you want to save your portfolio before exiting?",
                        "Save Portfolio",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
    
                if (response == JOptionPane.YES_OPTION) {
                    savePortfolio();
                    printEventLog(); // Print logs before exiting
                    System.exit(0);
                } else if (response == JOptionPane.NO_OPTION) {
                    printEventLog(); // Print logs before exiting
                    System.exit(0);
                }
                // If CANCEL_OPTION, do nothing (window remains open)
            }
        });

    }

    // EFFECTS: centres the Frame based on display size
    private void centreFrame() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
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
            portfolioDisplay.setText("");
            portfolioDisplay.append("Portfolio Loaded:\n");

            ArrayList<Stock> myPortfolio = portfolio.getPortfolioList();

            if (portfolio.getPortfolioSize() == 0) {
                portfolioDisplay.append("No Stocks in portfolio");
            } else {
                for (Stock stock : myPortfolio) {
                    portfolioDisplay.append("Ticker:" + stock.getTicker() + "   ");
                    portfolioDisplay.append("Shares:" + stock.getShares() + "   ");
                    portfolioDisplay.append("AvgPrice:" + stock.getPrice() + "   ");
                    portfolioDisplay.append("\n________\n");
                }
            }
        } catch (IOException e) {
            portfolioDisplay.setText("Unable to read from file.\n");
        }

        pfPanel.revalidate();
        pfPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: views Portfolio from portfolio object
    private void viewPortfolio() {
        ArrayList<Stock> myPortfolio = portfolio.getPortfolioList();

        if (portfolio.getPortfolioSize() == 0) {
            portfolioDisplay.append("No Stocks in portfolio\n");
        } else {
            portfolioDisplay.append("Portfolio Details:\n");
            for (Stock stock : myPortfolio) {
                portfolioDisplay.append("Ticker: " + stock.getTicker() + "   ");
                portfolioDisplay.append("Shares: " + stock.getShares() + "   ");
                portfolioDisplay.append("AvgPrice: " + stock.getPrice() + "   ");
                portfolioDisplay.append("\n________\n");
            }
        }
        pfPanel.revalidate();
        pfPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Helper for focusing on window
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            QuantEdgeGUI.this.requestFocusInWindow();
        }
    }
}
