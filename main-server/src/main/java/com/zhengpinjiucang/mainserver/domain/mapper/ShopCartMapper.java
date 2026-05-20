package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ShopCartBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCartMapper {

    List<ShopCartBean> select(ShopCartBean bean);

    ShopCartBean selectOne(ShopCartBean bean);

    int insert(ShopCartBean bean);

    int update(ShopCartBean bean);

    int delete(ShopCartBean bean);
}
