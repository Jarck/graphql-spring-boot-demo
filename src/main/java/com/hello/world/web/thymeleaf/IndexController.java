package com.hello.world.web.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Controller
public class IndexController {
  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
