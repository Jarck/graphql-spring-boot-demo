package hello.web.thymeleaf;

import hello.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("")
    @RequiresPermissions("user:read")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }
}
