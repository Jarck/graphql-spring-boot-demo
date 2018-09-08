package hello.entity;

import lombok.Data;

import java.util.Date;

@Data
public class City {
    private Integer id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}