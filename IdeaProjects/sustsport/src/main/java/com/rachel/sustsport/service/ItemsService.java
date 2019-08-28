package com.rachel.sustsport.service;

import com.rachel.sustsport.entity.Item;
import java.util.List;


public interface ItemsService {
    List<Item> getItemList();
    boolean addItem(Item item);
    List<Item> getItemByType(String type);

    //    boolean modifyItem(Item item);
    //    boolean deleteItem(int id);
}


