package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageCodeBean {
    private String strEmail;
    private String strCode;
    private Long longCreatedTime;
}
