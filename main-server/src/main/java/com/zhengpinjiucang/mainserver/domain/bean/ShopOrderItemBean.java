package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShopOrderItemBean {

    private Long longId;
    private Long longShopOrderId;
    private Long longProductId;
    private String strProductTitle;
    private Integer intProductPrice;
    private String strProductImage;
    private Integer intNum;
    private Long longCreatedTime;
}
