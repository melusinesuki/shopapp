package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ProductMapper {
    @Select("select longId,strSurface,strTitle,strPrice from product")
    List<ProductBean> selectOne();

    ProductBean selectOne(ProductBean bean);
    int insert(ProductBean bean);
    int update(ProductBean bean);
    int delete(ProductBean bean);

}
