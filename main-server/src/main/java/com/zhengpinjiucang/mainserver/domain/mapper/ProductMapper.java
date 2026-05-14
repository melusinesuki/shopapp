package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper {

    List<ProductBean> select(ProductBean bean);

    ProductBean selectOne(ProductBean bean);
    int insert(ProductBean bean);
    int update(ProductBean bean);
    int delete(ProductBean bean);

}