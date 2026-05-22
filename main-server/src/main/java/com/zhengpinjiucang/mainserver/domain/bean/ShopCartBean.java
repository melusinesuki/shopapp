package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;

import java.util.List;

@Data
public class ShopCartBean {

    private Long longId;
    private Long longProductId;
    private Long longMemberAccountId;
    private Integer intNum;
    private Long longCreatedTime;
    private Long longUpdatedTime;

    private ProductBean productBean;

    private List<Long> idList;

}
