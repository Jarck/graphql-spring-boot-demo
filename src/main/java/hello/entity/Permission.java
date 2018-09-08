package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Permission {
    private Long id;
    private String name;
    private String permission;
    private String resource_type;
    private String avaliable;
    private Date createdAt;
    private Date updatedAt;
}