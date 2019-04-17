package tbcloud.agent.admin.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.NodeInfo;

/**
 * @author: Dmm
 * @date: 2019/4/1 19:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeVo extends NodeInfo {

    private String agentName;

    private String agentId;

}
