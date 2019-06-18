package com.rajeshchinni;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);  // newly added to check if this is working
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s: stockList.Items().keySet()){
            System.out.println(s);
        }

        Basket RajsBasket = new Basket("Raj");
        sellItem(RajsBasket, "car", 1);
        System.out.println(RajsBasket);

        sellItem(RajsBasket, "car", 1);
        System.out.println(RajsBasket);

        sellItem(RajsBasket, "car", 1);
        sellItem(RajsBasket, "spanner", 5);
        //System.out.println(RajsBasket);

        sellItem(RajsBasket, "juice", 4);
        sellItem(RajsBasket, "cup", 12);
        sellItem(RajsBasket, "bread", 1);
        //System.out.println(RajsBasket);

        //System.out.println(stockList);

        // newly added
        Basket sudhaBasket = new Basket("sudha");
        sellItem(sudhaBasket, "cup", 100);
        sellItem(sudhaBasket, "juice", 5);
        removeItem(sudhaBasket, "cup", 1);
        System.out.println(sudhaBasket);

        // newly added
        removeItem(RajsBasket, "car", 1);
        removeItem(RajsBasket, "cup", 9);
        removeItem(RajsBasket, "car", 1);
        System.out.println("cars removed: " + removeItem(RajsBasket, "car", 1));  // should not remove any
        System.out.println(RajsBasket);

        // newly added. removes all items from Rajs basket
        removeItem(RajsBasket, "bread", 1);
        removeItem(RajsBasket, "cup", 3);
        removeItem(RajsBasket, "juice", 4);
        removeItem(RajsBasket, "cup", 3);
        System.out.println(RajsBasket);

        // newly added
        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(sudhaBasket);
        System.out.println(stockList);
        checkOut(sudhaBasket);
        System.out.println(sudhaBasket);
        System.out.println(stockList);


        // newly added
        checkOut(RajsBasket);
        System.out.println(RajsBasket);




    }

    public static int sellItem(Basket basket, String item, int quantity){
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0){   // sellStock is changed to reserveStock
            return basket.addToBasket(stockItem, quantity);          // quantity is changed to basket.addToBasket(stockItem, quantity)
        }
        return 0;
    }


    public static int removeItem(Basket basket, String item, int quantity){
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity){
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    // new method added
    public static void checkOut(Basket basket){
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }



}
