package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.exception.GraphQLValidateException;
import com.hello.world.service.IRoleService;
import com.hello.world.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:13
 **/
@Component
public class RoleMutation implements GraphQLMutationResolver {
  @Autowired
  private IRoleService roleService;

  /**
   * 新建角色
   *
   * @param createRoleDto createRoleDto
   * @return 角色
   * @throws GraphQLValidateException 参数校验异常
   */
  public RoleDto createRole(CreateRoleDto createRoleDto) throws GraphQLValidateException {
    // 校验参数
    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(createRoleDto);
    if (errorMap != null) {
      throw new GraphQLValidateException(errorMap.toString());
    }

    roleService.createRole(createRoleDto);
    RoleDto roleDto = roleService.searchWithId(createRoleDto.getId());

    return roleDto;
  }
}
