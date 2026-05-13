package com.zhengpinjiucang.mainserver.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean {
    private Integer intCode;
    private String strMessage;
    private Object objData;

}
