package hello.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class createPermissionDto {
  String name;
  String permission;
  String resourceType;
}
