package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper {
    List<ProductBean> list();
    ProductBean detail(ProductBean bean);


}
