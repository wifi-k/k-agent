package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentInfo;

/**
 * @author: Dmm
 * @date: 2019/4/2 17:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentInfoUpdate extends AgentInfo {

    private String loginName;

    private String token;

}
