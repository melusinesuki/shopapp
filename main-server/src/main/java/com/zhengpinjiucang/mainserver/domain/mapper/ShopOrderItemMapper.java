package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderItemBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopOrderItemMapper {

    List<ShopOrderItemBean> select(ShopOrderItemBean bean);
    int insert(ShopOrderItemBean bean);
    int insertBatch(List<ShopOrderItemBean> list);

    @Delete("delete from shop_order_item where shop_order_id = #{longShopOrderId}")
    int deleteByOrderId(ShopOrderItemBean bean);
}
