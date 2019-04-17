package tbcloud.agent.admin.entity.baby;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentAccount;

/**
 * @author: Dmm
 * @date: 2019/4/16 18:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentAccountUpdate extends AgentAccount {

    private String confirmPassWd;

    private String token;
}
