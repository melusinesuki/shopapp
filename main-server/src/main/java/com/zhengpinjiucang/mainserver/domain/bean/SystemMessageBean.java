package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SystemMessageBean {

    private Long longId;
    private Integer intTargetType;
    private Integer intBizType;
    private Long longMemberAccountId;
    private Integer intStatus;
    private Integer intChannel;
    private String strEmail;
    private String strPath;
    private String strTitle;
    private String strContent;
    private Long longCreatedTime;
    private Long longUpdatedTime;

    private Long longLastCreatedTime;
}
