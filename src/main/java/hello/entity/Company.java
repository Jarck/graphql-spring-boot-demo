package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Company {
    private Integer id;
    private String name;
    private String shortName;
    private String address;
    private String linkName;
    private String phone;
    private Integer cityId;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
}