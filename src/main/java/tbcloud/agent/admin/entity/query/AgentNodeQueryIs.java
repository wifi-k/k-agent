package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.common.PageInfo;

/**
 * 设备查询列表
 * @author: Dmm
 * @date: 2019/4/16 10:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeQueryIs extends PageInfo {

    /**
     * 查询条件
     */
    private String nodeId;

    private String routerType;

    private String edition;

    /**
     * 过滤条件
     */
    private String token;

    private String agentId;

    private String parentAgentId;

}
