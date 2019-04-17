package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.common.PageInfo;

/**
 * @author: Dmm
 * @date: 2019/4/1 19:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeQuery extends PageInfo {

    private Byte isBind;

    private String nodeId;

    private String agentId;

    private String token;

}
