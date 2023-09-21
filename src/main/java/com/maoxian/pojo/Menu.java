package com.maoxian.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Long id;

    private String menuName;

    private String perms;

    private String status;

    private String defFlag;
}
