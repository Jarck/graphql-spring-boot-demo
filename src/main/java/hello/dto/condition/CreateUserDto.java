package hello.dto.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String name;
    private String phone;
    private Long cityId;
    private Long companyId;
    private Long[] roleIds;
}
