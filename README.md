# **My Personal Project**

## **Project Proposal** 

QuantEdge is a comprehensive investment tracking, visualization, and analysis platform designed to help users manage their stock portfolios with ease. The app allows investors to add stock purchases, generate detailed charts for asset allocation and market trends, and run predictive models to forecast future stock prices.

Targeted primarily at investors seeking a free yet reliable tool, QuantEdge provides essential features for tracking and analyzing investment performance.

This project holds special significance to me, as I’ve been passionate about trading from a young age. QuantEdge combines my interest in financial markets with the power of data-driven techniques like quantitative trading to offer valuable insights for investors.

## **User Stories**

### *Phase One*: 

- **Add a Stock for the first time:**
As a user, I would like to purchase stock (X) and add it to my portfolio (Y), specifying the stock ticker, price per stock, and the number of stocks purchased.

- **Buy Shares of Existing Stocks:**
As a user, I would like to re-purchase any stock (X) from portfolio (Y), specifying the stock ticker, price per stock, and the number of stocks. 

This would update my stock (X) with a new Average Price per share and number of shares owned.

- **View Portfolio:**
As a user, I would like to view the list of stocks I currently hold in my portfolio, along with total value of holdings.

- **View Stock Details:**
As a user, I should be able to select a specific stock from my portfolio and view details such as the number of shares I own and the purchase price.

- **Sell Stock:**
As a user, I should be able to sell any number of shares from a particular stock in my portfolio, and my portfolio should update to reflect the sale and remaining shares.


#### *Phase Two*:

- **Save Portfolio to a file:**
As a user, when I 'quit' the application, I would like the option to save my Portfolio to a file. This save would include all my stocks, along with all the information about the stocks such as Average price. 

- **Load Portfolio from a file:**
As a user, when I open QuantEdge, I would like to be asked if I want to open a previously saved Portfolio from file.


## Instructions for End User

- You can add Stocks to the portfolio, by filling out the Ticker, Price and Quantity and clicking "Add Stock".
- You can sell Stocks to the portfolio, by filling out the Ticker, Price and Quantity and clicking "Sell Stock".
- You can View your Portfolio by clicking the "View Portfolio" button.
- You can save your Portfolio when you exit the program by clicking "Yes" to the save prompt.


#### Phase 4: Task 2 (Example of Event Logging)
- === Event Log ===
- Fri Nov 29 00:12:47 PST 2024
- Added new Stock:PURPE ,Quantity 110, price:23
- Fri Nov 29 00:13:19 PST 2024
- Added new Stock:APPLE ,Quantity 20, price:20
- Fri Nov 29 00:13:29 PST 2024
- Added new Stock:PURPE ,Quantity 10, price:20
- Fri Nov 29 00:13:36 PST 2024
- Sold Shares:APPLE ,Quantity 5, price:20
- =================

#### Phase 4: Task 3 Design Reflection based on UML
- If I had more time to work on the project, I would focus on refactoring to enhance maintainability and modularity. One improvement would be to decouple tightly coupled components. Currently, the user interface (QuantEdgeGUI) interacts directly with the portfolio data model, which creates a strong coupling. The application logic could be abstracted, making it easier to modify the UI or backend independently. The shared code between the two UIs such as writing and reading from JSON will also be abstracted as a result.

- Another area for refactoring is the use of singletons like EventLog. I could use singleton for the Portfolio class if I am using the same object for both UI (terminal and GUI). This way, I can just get an Instance of it, instead of creating a Portfolio object and passing it to the GUI in multiple threads which I am doing right now.
