package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ShopLogisticsBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopLogisticsMapper {

    List<ShopLogisticsBean> select(ShopLogisticsBean shopLogisticsBean);

    ShopLogisticsBean selectOne(ShopLogisticsBean shopLogisticsBean);

    int insert(ShopLogisticsBean shopLogisticsBean);

    int update(ShopLogisticsBean shopLogisticsBean);

}
