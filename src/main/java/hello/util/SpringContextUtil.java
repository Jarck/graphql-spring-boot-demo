package hello.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {
  private static ApplicationContext context = null;

  public static String getActiveProfile() {
    return context.getEnvironment().getActiveProfiles()[0];
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }
}
