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
    private String strNo;
    private String strSurface;
    private String strTitle;
    private Integer intPrice;//分钱
    private String strUse;//分钱

}
