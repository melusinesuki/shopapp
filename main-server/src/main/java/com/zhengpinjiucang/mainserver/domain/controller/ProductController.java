package com.zhengpinjiucang.mainserver.domain.controller;


import lombok.extern.slf4j.Slf4j;
import java.util.arraylist;

@RestController

public class ProductController {
    @RequestMapping("/product/list")
    public List<String> list(){
        List<String> list = new list();
        list.add("饼干");
        list.add("可乐");

        list.add("牛奶");
        list.add("面包");
        return list;

    }
}
