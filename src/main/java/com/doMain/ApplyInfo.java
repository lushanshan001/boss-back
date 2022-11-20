package com.doMain;

import lombok.Data;

@Data
public class ApplyInfo {
    private String aid;//编号
    private String name;//求职者姓名
    private String phone;//联系方式
    private String education;//学历
    private String position;//应聘职位
    private String address;//简历地址
    private int status;//状态
}
