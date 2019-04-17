package tbcloud.agent.admin.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentNode;

/**
 * @author: Dmm
 * @date: 2019/4/16 14:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeVo2 extends AgentNode {

    private String agentName;

    private String factoryName;

    private String aliasName;

}
