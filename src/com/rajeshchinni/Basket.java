package com.rajeshchinni;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();   // changed to tree map. This makes the basket in alphabetical order.
    }

    // method
    public int addToBasket(StockItem item, int quantity){
        if ((item != null) && (quantity > 0)){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    // new method added
    public int removeFromBasket(StockItem item, int quantity){
        if ((item != null) && (quantity > 0)){
            // check that if we already have that item in the basket
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity; // changed to minus

            if (newQuantity > 0){
                list.put(item, newQuantity);
                return quantity;
            } else if (newQuantity == 0){
                list.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    // new method added
    public void clearBasket(){
        list.clear();
    }

    public Map<StockItem, Integer> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";   // changes made here
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()){
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost = totalCost + item.getKey().getPrice() * item.getValue();

        }
        return s + "total cost " + totalCost;
    }
}
