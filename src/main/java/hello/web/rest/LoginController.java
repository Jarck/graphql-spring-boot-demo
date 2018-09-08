package hello.web.rest;

import hello.constant.SystemConstant;
import hello.dto.condition.LoginDto;
import hello.entity.User;
import hello.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api(value = "Rest登录", description = "Rest登录")
@RequestMapping("api")
public class LoginController {
    @Autowired
    private IUserService userService;

    @ApiOperation("login")
    @PostMapping("login")
    public Map<String, Object> login(LoginDto loginUser, BindingResult bindingResult) {
        String token = userService.login(loginUser);
        User user = userService.getUserByPhone(loginUser.getPhone());
        Map<String, Object> result = new HashMap<>(1);
        result.put("user", user);
        result.put(SystemConstant.TOKEN_HEADER + loginUser.getPhone(), token);

        return result;
    }
}
