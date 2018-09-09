package hello.web.thymeleaf;

import hello.constant.SystemConstant;
import hello.dto.condition.LoginDto;
import hello.entity.User;
import hello.exception.LoginFailedException;
import hello.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@Controller
public class WebLoginController {
  @Autowired
  private IUserService userService;

  @PostMapping("webLogin")
  public String login(LoginDto loginUser, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
    try {
      String token = userService.login(loginUser);
      User user = userService.getUserByPhone(loginUser.getPhone());
      model.addAttribute("user", user);
      model.addAttribute("users", userService.findAll());
      session.setAttribute(SystemConstant.TOKEN_HEADER + loginUser.getPhone(), token);
    } catch (LoginFailedException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
      return "redirect:/index";
    }

    return "user/index";
  }
}
