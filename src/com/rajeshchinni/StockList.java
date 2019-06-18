package com.rajeshchinni;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    // constructor. select none
    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    // method
    public int addStock(StockItem item){
        if (item != null){
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if there are already stocks on this item, adjust the quantity
            if (inStock != item){
                item.adjustStock(inStock.availableQuantity());
            }
            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    // changes made in the below method
    public int sellStock(String item, int quantity){
//        StockItem inStock = list.getOrDefault(item, null);
//        if ((quantity > 0) && (inStock != null) && (inStock.availableQuantity() >= quantity) ){
//            inStock.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0;
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0) ){
            return inStock.finaliseStock(quantity);
        }
        return 0;
    }

    // new method added
    public int reserveStock(String item, int quantity){
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0) ){
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    // new method added
    public int unreserveStock(String item, int quantity){
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0) ){
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);  // https://www.geeksforgeeks.org/collections-unmodifiablelist-method-in-java-with-examples/
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item: list.entrySet() ){  // https://www.geeksforgeeks.org/map-entry-interface-java-example/
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value of items:";
            s = s + String.format("%.2f",itemValue) + "\n";
            totalCost = totalCost + itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
