package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Long id;
    private String name;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private String remark;
}