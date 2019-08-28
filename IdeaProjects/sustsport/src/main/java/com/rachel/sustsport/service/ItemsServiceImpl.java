package com.rachel.sustsport.service;

import com.rachel.sustsport.entity.Item;
import com.rachel.sustsport.mapper.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    ItemsMapper itemsMapper;

    @Override
    public List<Item> getItemList() {
        return itemsMapper.queryItems();
    }


    @Override
    public boolean addItem(Item item) {
     return itemsMapper.insertItem(item);
    }

    @Override
    public List<Item> getItemByType(String type) {
        return itemsMapper.queryItemsByType(type);
    }
}
