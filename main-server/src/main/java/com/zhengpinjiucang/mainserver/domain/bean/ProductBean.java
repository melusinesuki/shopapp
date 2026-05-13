package com.zhengpinjiucang.mainserver.domain.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProductBean {
    private Long longId;
    private String strNo;
    private String strSurface;
    private String strTitle;
    private Integer intPrice;
    private String strUse;
    private String strContent;
    private Integer intInventory;
    private Integer intSales;
    private long createdTime;
    private long longUpdateTime;
    private Integer intIsDeleted;
    private long longDeleteTime;




}
