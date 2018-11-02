package com.hello.world.service.impl;

import com.hello.world.auth.JWTUtil;
import com.hello.world.exception.LoginFailedException;
import com.hello.world.service.IUserService;
import com.hello.world.util.SpringContextUtil;
import com.hello.world.constant.SystemConstant;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dao.UserMapper;
import com.hello.world.dao.UserRoleMapper;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import com.hello.world.entity.User;
import com.hello.world.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Service
public class UserServiceImpl implements IUserService {
  @Value("${secret_key}")
  private String secretKey;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserRoleMapper userRoleMapper;

  @Autowired
  private RoleMapper roleMapper;

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public List<User> findAll() {
    return userMapper.findAll();
  }

  @Override
  public UserDto searchWithId(Long userId) {
    User user = userMapper.selectByPrimaryKey(userId);
    UserDto userDto = null;

    if (user != null) {
      userDto = new UserDto(user);
      setUserRolePermission(userDto);
    }

    return userDto;
  }

  @Override
  public UserDto searchWithPhone(String phone) {
    User user = userMapper.selectByPhone(phone);
    UserDto userDto = new UserDto(user);
    userDto.setSecretKey(secretKey);
    setUserRolePermission(userDto);

    return userDto;
  }

  @Override
  public User getUserByPhone(String phone) {
    return userMapper.selectByPhone(phone);
  }

  @Override
  public String login(LoginDto loginDto) {
    String redisVerifyCode = stringRedisTemplate.opsForValue().get(loginDto.getPhone()
            + SystemConstant.VERIFY_CODE_SUFFIX);
    if ("prod".equals(SpringContextUtil.getActiveProfile())) {
      if (!loginDto.getVerifyCode().equals(redisVerifyCode)) {
        throw new LoginFailedException("验证码错误");
      }
    }

    UserDto userDto = searchWithPhone(loginDto.getPhone());
    String token = JWTUtil.sign(userDto);

    stringRedisTemplate.opsForValue().set(SystemConstant.TOKEN_HEADER + userDto.getPhone(),
            token, 1, TimeUnit.DAYS);

    return token;
  }

  /**
   * 设置用户角色
   *
   * @param userDto userDto
   */
  private void setUserRolePermission(UserDto userDto) {
    List<UserRole> userRoleList = userRoleMapper.searchWithUserId(userDto.getId());
    List<Role> roleList = userDto.getRoles();
    List<Permission> permissionList = userDto.getPermissions();

    userRoleList.stream().forEach(userRole -> {
      Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
      RoleDto roleDto = new RoleDto(role);
      roleDto.getPermissions().stream().forEach(permission -> permissionList.add(permission));
      roleDto.setPermissions(null);

      roleList.add(roleDto);
    });
  }

  @Override
  public List<User> searchWithCondition(SearchUserDto searchUserDto) {
    return userMapper.searchCondition(searchUserDto);
  }

  @Override
  @Transactional
  public Long createUser(CreateUserDto createUserDto) {
    return userMapper.insertUser(createUserDto);
  }
}
