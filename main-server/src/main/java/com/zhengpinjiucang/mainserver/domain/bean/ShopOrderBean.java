package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ShopOrderBean {

    private Long longId;
    private Long longMemberAccountId;
    private String strOrderCode;
    private String strProductListJson;
    private Integer intTotalPrice;
    private Integer intPayPrice;
    private String strAddressJson;
    private Integer intStatus;
    private Long longCreatedTime;
    private Long longUpdatedTime;
    private Integer intIsDeleted;
    private Long longDeletedTime;

    private Long longAddressId;
    private List<Long> shopCartIdList;
}
