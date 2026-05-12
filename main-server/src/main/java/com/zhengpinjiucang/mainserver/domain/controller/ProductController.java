package com.zhengpinjiucang.mainserver.domain.controller;


import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ProductController {
    @RequestMapping("/product/list")
    public List<ProductBean> list(){
        log.info("/product/list,查询产品列表，请求参数:null");
        List<ProductBean> list = new ArrayList<>();

        list.add(new ProductBean("1", "https://images.unsplash.com/photo-1585238342024-78d387f4a707?auto=format&fit=crop&w=800&q=80", "海盐薯片", 100000,null));
        list.add(new ProductBean("2", "https://images.unsplash.com/photo-1600271886742-f049cd5bba3f?auto=format&fit=crop&w=800&q=80", "橙汁饮料", 50000,null));
        list.add(new ProductBean("3", "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&w=800&q=80", "新鲜苹果", 200000,null));
        list.add(new ProductBean("4", "https://images.unsplash.com/photo-1610552050890-fe99536c2615?auto=format&fit=crop&w=800&q=80", "洗衣液补充装", 20000,null));
        log.info("/product/list,查询产品列表，请求参数:{}",list);
        return list;
    }
    @RequestMapping("/product/detail")
    public ProductBean detail(ProductBean bean){
        log.info("/product/detail,查询产品详细，请求参数:{}",bean);

        log.info("/product/detail,查询产品详细，响应值:null");
        return null;
    }
}
