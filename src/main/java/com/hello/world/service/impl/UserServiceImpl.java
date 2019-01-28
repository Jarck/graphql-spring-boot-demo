package com.hello.world.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.world.auth.JWTUtil;
import com.hello.world.constant.SystemConstant;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dao.UserMapper;
import com.hello.world.dao.UserRoleMapper;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.exception.LoginFailedException;
import com.hello.world.service.IUserService;
import com.hello.world.util.SpringContextUtil;
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
  public List<UserDto> findAll() {
    return userMapper.findAll();
  }

  @Override
  public UserDto searchWithId(Long userId) {
    UserDto userDto = userMapper.selectByPrimaryKey(userId);

    return userDto;
  }

  @Override
  public UserDto searchWithPhone(String phone) {
    UserDto userDto = userMapper.selectByPhone(phone);

    return userDto;
  }

  @Override
  public UserDto getUserByPhone(String phone) {
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

    if (userDto == null) {
      throw new LoginFailedException("用户不存在");
    }

    String token = JWTUtil.sign(userDto);

    stringRedisTemplate.opsForValue().set(userDto.getPhone() + "_token",
            token, 1, TimeUnit.DAYS);

    return token;
  }

  @Override
  public List<UserDto> searchWithCondition(SearchUserDto searchUserDto) {
    return userMapper.searchCondition(searchUserDto);
  }

  @Override
  public PageInfo<UserDto> searchWithCondition(SearchUserDto searchUserDto, PageDto pageDto) {
    PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
    PageHelper.orderBy(pageDto.getOrderBy() + " " + (pageDto.isDesc() ? "desc" : "asc"));

    List<UserDto> userList = userMapper.searchCondition(searchUserDto);

    PageInfo<UserDto> userPageInfo = new PageInfo<>(userList);

    return userPageInfo;
  }

  @Override
  public PageInfo<UserDto> searchUserAndCityAndCompanyAndRoles(SearchUserDto searchUserDto, PageDto pageDto) {
    PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
    PageHelper.orderBy(pageDto.getOrderBy() + " " + (pageDto.isDesc() ? "desc" : "asc"));

    List<UserDto> userList = userMapper.searchUserAndCityAndCompanyAndRoles(searchUserDto);

    PageInfo<UserDto> userDtoPageInfo = new PageInfo<>(userList);

    return userDtoPageInfo;
  }

  @Override
  public List<UserDto> searchUserAndCityAndCompanyAndRoles(SearchUserDto searchUserDto) {
    List<UserDto> userList = userMapper.searchUserAndCityAndCompanyAndRoles(searchUserDto);

    return  userList;
  }

  @Override
  public UserDto searchUserAndCityAndCompanyAndRolesWithId(Long id) {
    return userMapper.searchUserAndCityAndCompanyAndRolesWithId(id);
  }

  @Override
  public UserDto searchUserAndCityAndCompanyAndRolesWithPhone(String phone) {
    return userMapper.searchUserAndCityAndCompanyAndRolesWithPhone(phone);
  }

  @Override
  @Transactional
  public Long createUser(CreateUserDto createUserDto) {
    return userMapper.insertUser(createUserDto);
  }
}
