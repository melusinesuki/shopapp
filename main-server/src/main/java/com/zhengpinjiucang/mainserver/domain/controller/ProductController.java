package com.zhengpinjiucang.mainserver.domain.controller;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.domain.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductMapper productMapper;




    @RequestMapping("/product/list")
    public List<ProductBean> list(){
        log.info("/product/list,查询产品列表，请求参数:null");

        List<ProductBean> list =  productMapper.list();;

        log.info("/product/list,查询产品列表，请求参数:{}",list);
        return list;
    }
    @PostMapping("/product/detail")
    public ProductBean detail(@RequestBody ProductBean bean){
        log.info("/product/detail,查询产品详细，请求参数:{}",bean);

        ProductBean detail = productMapper.detail(bean);
        log.info("/product/detail,查询产品详细，响应值:null");
        return null;
    }
}
