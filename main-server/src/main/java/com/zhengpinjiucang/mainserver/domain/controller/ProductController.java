package com.zhengpinjiucang.mainserver.domain.controller;
import com.github.pagehelper.PageInfo;
import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.domain.bean.RequestBean;
import com.zhengpinjiucang.mainserver.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/product/list")
    public ResultBean list(@RequestBody RequestBean<ProductBean> bean) {

        log.info("/product/list,查询产品列表，请求参数:null");

        PageInfo<ProductBean> pageInfo = productService.list(bean);
        log.info("/product/list,查询产品列表，响应值:{}", pageInfo);
        return new ResultBean(200, "查询成功", pageInfo);

    }

    @PostMapping("/product/detail")
    public ResultBean detail(@RequestBody ProductBean bean) {
        log.info("/product/detail,查询产品详细，请求参数:{}", bean);

        ProductBean detail = productService.detail(bean);
        log.info("/product/detail,查询产品详细，响应值:{}", detail);
        return new ResultBean(200, "查询成功", detail);



    }
}
