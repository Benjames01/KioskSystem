# KioskSystem
A supermarket chain requires an Automated Checkout System with a Kiosk User Interface for customers to scan and pay for their goods and a stock database.

The stock database automatically removes items from stock when bought. It will also add, remove and edit existing stock items.

The stock database requires admin accounts for staff. Admin users require a login name and password with access to the stock database. The system will highlight any stock that needs replenishing when an admin logs in. Admin users can send orders for new stock and update replenished items when deliveries arrive.

The kiosk User Interface allows customers to scan a code on items, this code will match the stock database code for that item. When the items are paid for, the stock database updates its remaining items. The kiosk interface displays a list of the currently scanned items with their names, prices and a running total price for all items.

Customers can pay using cash or a card. Cash payment will calculate and display any change required to be given to the customer. Card payments need a verification screen that displays an accept or deny message from their bank.

The receipt requires a printout of the item names and their prices, the total price and payment method. If the payment method is cash, also print the change given.

The receipt display is required to be in a separate GUI panel of the application. Use threading to inject the receipt data into the panel and output the text as you may see in paper receipts. This includes the company name, date and the information above. The process should only start when the thread has finished processing the receipt data.



## Sample Card Numbers

|           Card Type     |Cardnumber  |                   
|----------------|-------------------------------|
|Visa|`'4716300374117084'`     
|Visa|`'4539812081840761'`  
|Visa|`'4916406021609650'`    
|Mastercard|`'5393805149343440'`    
|Mastercard|`'5119758954662938'` 
|Mastercard|`'5515819271454621'`              

## Credits



|                |Available at                        |License Type                        |
|----------------|-------------------------------|-----------------------------|
|SQLITE-JDBC|`'https://github.com/xerial/sqlite-jdbc'`            |'Apache License'            |



#
```
