package tbcloud.agent.admin.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Dmm
 * @date: 2019/4/2 19:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginToken {

    private String token;

}
