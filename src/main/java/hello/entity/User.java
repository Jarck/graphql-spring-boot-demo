package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private String phone;
    private Long cityId;
    private Long companyId;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
}