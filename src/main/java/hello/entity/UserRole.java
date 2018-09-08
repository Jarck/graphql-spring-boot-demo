package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
    private Date createdAt;
    private Date updatedAt;
}