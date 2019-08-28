package com.rachel.sustsport.mapper;

import com.rachel.sustsport.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemsMapper {

    public List<Item> queryItems();
    public boolean insertItem(Item item);
    public List<Item> queryItemsByType(String type);

}


