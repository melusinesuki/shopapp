package com.zhengpinjiucang.mainserver.domain.controller;
import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.common.exception.NomalException;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.domain.mapper.ProductMapper;
import com.zhengpinjiucang.mainserver.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/product/list")
    public ResultBean list(){
        log.info("/product/list,查询产品列表，请求参数:null");

        List<ProductBean> list =  productService.list();
        log.info("/product/list,查询产品列表，响应值:{}",list);
        return new ResultBean(200,"查询成功",list) ;

    }
    @PostMapping("/product/detail")
    public ResultBean detail(@RequestBody ProductBean bean){
        log.info("/product/detail,查询产品详细，请求参数:{}",bean);


        ProductBean detail = productService.detail(bean);
        log.info("/product/detail,查询产品详细，响应值:{}",detail);
        return new ResultBean(200,"查询成功",detail) ;

    }
}
