package tbcloud.agent.admin.common.api.version;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Dmm
 * @date: 2019/4/18 10:51
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ApiVersion {

    // 定义接口的版本号 默认
    int value() default 20190412;
}
