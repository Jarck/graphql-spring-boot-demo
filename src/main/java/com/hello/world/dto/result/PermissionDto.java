package com.hello.world.dto.result;

import com.hello.world.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jarck-lou
 * @date 2018/9/01 20:16
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PermissionDto extends Permission implements Serializable {
}
