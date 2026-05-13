package com.zhengpinjiucang.mainserver.domain.service;

import com.zhengpinjiucang.mainserver.common.exception.NomalException;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
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

    public List<ProductBean> list(){

        return productMapper.selectOne();
    }

    public ProductBean detail(ProductBean bean){
        log.debug("查询商品详情，检查参数");
        if(bean == null){
            throw new NomalException("参数不能为空");
        }
        if(bean.getStrNo() == null){
            throw new NomalException("产品编号不能为空");
        }
        log.debug("查询商品详情，执行数据库参数");
        return  productMapper.selectOne(bean);
    }
}
