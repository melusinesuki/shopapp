package com.zhengpinjiucang.mainserver.domain.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.common.bean.RequestBean;
import com.zhengpinjiucang.mainserver.domain.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public PageInfo<ProductBean> list(RequestBean<ProductBean> bean) {

        PageHelper.startPage(bean.getPageNum(),bean.getPageSize(),bean.getOrderBy());
        List<ProductBean>productList =productMapper.select(bean.getParams());
        PageInfo<ProductBean> pageInfo=PageInfo.of(productList);
        System.out.println(pageInfo);
        return pageInfo;
    }

    public ProductBean detail(ProductBean bean){
        log.debug("查询商品详情，检查参数");
        if(bean == null){
            throw new NormalException("参数不能为空");
        }
        if(bean.getLongId() == null){
            throw new NormalException("产品ID不能为空");
        }
        log.debug("查询商品详情，执行数据库参数");
        return  productMapper.selectOne(bean);
    }
}
