package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentAccount;

/**
 * @author: Dmm
 * @date: 2019/4/2 15:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentAccountUpdate extends AgentAccountQuery {

    private String confirmPassWd;

}
