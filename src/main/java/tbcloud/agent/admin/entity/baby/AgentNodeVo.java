package tbcloud.agent.admin.entity.baby;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentNode;

/**
 * @author: Dmm
 * @date: 2019/4/16 17:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeVo extends AgentNode {

    private String agentName;

    private String factoryName;

    private String aliasName;
}
