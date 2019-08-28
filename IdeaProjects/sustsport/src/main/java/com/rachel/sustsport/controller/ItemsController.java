package com.rachel.sustsport.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rachel.sustsport.entity.Item;
import com.rachel.sustsport.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/listitem")
    private Map<String, Object> listItem() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Item> list = new ArrayList<>();
        // 获取区域列表
        list = itemsService.getItemList();
        modelMap.put("itemList", list);
        return modelMap;
    }


    @RequestMapping("/typelist")
    private Map<String, Object> getItemByType(@RequestBody String type) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Item> list = new ArrayList<Item>();
        list = itemsService.getItemByType(type);
        System.out.println(list);
        modelMap.put("typeList", list);
        return modelMap;
    }

    @RequestMapping(value="/additem",method = RequestMethod.POST)
    private Map<String, Object> addItem(@RequestBody Item item) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", itemsService.addItem(item));
        System.out.println(item);
        return modelMap;
    }

}
