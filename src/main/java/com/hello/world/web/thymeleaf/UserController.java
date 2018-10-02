package com.hello.world.web.thymeleaf;

import com.hello.world.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@Controller
@RequestMapping("users")
public class UserController {
  @Autowired
  private IUserService userService;

  /**
   * 用户列表
   *
   * @param model model
   * @return 用户列表页面
   */
  @GetMapping("")
  @RequiresPermissions("user:read")
  public String index(Model model) {
    model.addAttribute("users", userService.findAll());
    return "user/index";
  }
}
