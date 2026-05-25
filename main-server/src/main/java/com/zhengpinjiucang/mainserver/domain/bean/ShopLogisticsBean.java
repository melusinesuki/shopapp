package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShopLogisticsBean {

    private Long longId;
    private Long longShopOrderId;
    private String strCode;
    private Integer intStatus;
    private String strContent;
    private Long longUpdatedTime;
    private Long longCreatedTime;
}
