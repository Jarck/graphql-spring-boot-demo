package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.exception.GraphQLValidateException;
import com.hello.world.service.IPermissionService;
import com.hello.world.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:16
 **/
@Component
public class PermissionMutation implements GraphQLMutationResolver {
  @Autowired
  private IPermissionService permissionService;

  /**
   * 新建权限
   *
   * @param createPermissionDto createPermissionDto
   * @return 权限
   * @throws GraphQLValidateException 参数异常
   * @throws ArgumentsException 参数异常
   */
  public PermissionDto createPermission(CreatePermissionDto createPermissionDto)
          throws GraphQLValidateException, ArgumentsException {
    // 校验参数
    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(createPermissionDto);
    if (errorMap != null) {
      throw new GraphQLValidateException(errorMap.toString());
    }

    PermissionDto permissionDto = permissionService.createPermission(createPermissionDto);

    return permissionDto;
  }
}
