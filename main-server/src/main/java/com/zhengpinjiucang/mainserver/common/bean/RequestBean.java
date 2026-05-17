package com.zhengpinjiucang.mainserver.common.bean;

import lombok.Data;

@Data
public class RequestBean<T> {

    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;

    private T params;

}
