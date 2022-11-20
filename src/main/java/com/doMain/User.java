package com.doMain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private char gender;
    private Date birthday;
    private String avatar;
    private int type;

}
