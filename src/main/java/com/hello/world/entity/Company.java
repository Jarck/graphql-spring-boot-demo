package com.hello.world.entity;

import com.hello.world.enums.CompanyStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel
public class Company implements Serializable {
  private static final long serialVersionUID = 7253748290959526944L;

  @ApiModelProperty(value = "公司ID")
  private Long id;

  @ApiModelProperty(value = "公司名称")
  private String name;

  @ApiModelProperty(value = "公司简称")
  private String shortName;

  @ApiModelProperty(value = "公司地址")
  private String address;

  @ApiModelProperty(value = "公司联系人")
  private String linkName;

  @ApiModelProperty(value = "联系号码")
  private String phone;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;

  @ApiModelProperty(value = "状态")
  private CompanyStatusEnum status;

  @ApiModelProperty(value = "创建时间")
  private Date createdAt;

  @ApiModelProperty(value = "更新时间")
  private Date updatedAt;
}
