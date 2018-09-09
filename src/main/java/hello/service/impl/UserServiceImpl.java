package hello.service.impl;

import hello.auth.JWTUtil;
import hello.constant.SystemConstant;
import hello.dao.RoleMapper;
import hello.dao.UserMapper;
import hello.dao.UserRoleMapper;
import hello.dto.condition.LoginDto;
import hello.dto.condition.SearchUserDto;
import hello.dto.create.CreateUserDto;
import hello.dto.result.RoleDto;
import hello.dto.result.UserDto;
import hello.entity.Permission;
import hello.entity.Role;
import hello.entity.User;
import hello.entity.UserRole;
import hello.exception.LoginFailedException;
import hello.service.IUserService;
import hello.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    UserDto userDto = new UserDto(user);
    setUserRolePermission(userDto);

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
    String redisVerifyCode = stringRedisTemplate.opsForValue().get(loginDto.getPhone() + SystemConstant.VERIFY_CODE_SUFFIX);
    if ("prod".equals(SpringContextUtil.getActiveProfile())) {
      if (!loginDto.getVerifyCode().equals(redisVerifyCode)) {
        throw new LoginFailedException("验证码错误");
      }
    }

    UserDto userDto = searchWithPhone(loginDto.getPhone());
    String token = JWTUtil.sign(userDto);

    stringRedisTemplate.opsForValue().set(SystemConstant.TOKEN_HEADER + userDto.getPhone(), token, 1, TimeUnit.DAYS);

    return token;
  }

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
    List<User> userList = userMapper.searchCondition(searchUserDto);

    return userList;
  }

  @Override
  @Transactional
  public String createUser(CreateUserDto createUserDto) {
    try {
      User user = generateUser(createUserDto);
      userMapper.insertUser(user);
    } catch (Exception e) {
      log.error("Create user error", e);
      return SystemConstant.RETURN_ERROR;
    }

    return SystemConstant.RETURN_SUCCESS;
  }

  /**
   * 构建user
   *
   * @param createUserDto 构建参数
   * @return user
   */
  private User generateUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setName(createUserDto.getName());
    user.setPhone(createUserDto.getPhone());
    user.setCityId(createUserDto.getCityId());
    user.setCompanyId(createUserDto.getCompanyId());

    return user;
  }
}
