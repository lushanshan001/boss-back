package com.doMain;

import lombok.*;

@Data
public class RecruitInfo {
    private int id;//招聘标号
    private String name;//发布人
    private String position;//招聘岗位
    private int number;//招聘数量
    private String request;//招聘要求
}
