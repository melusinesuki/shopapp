package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopOrderMapper {

    List<ShopOrderBean> select(ShopOrderBean bean);

    ShopOrderBean selectOne(ShopOrderBean bean);

    int insert(ShopOrderBean bean);

    int update(ShopOrderBean bean);

    int delete(ShopOrderBean bean);
}
