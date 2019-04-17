package tbcloud.agent.admin.entity.baby;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.parameter.PublicParameter;


/**
 * @author: Dmm
 * @date: 2019/4/16 17:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentNodeQuery extends PublicParameter {

    /**
     * 查询条件
     */
    private String nodeId;

    private String routerType;

    private String edition;

    private String product;

    /**
     * 过滤条件
     */
    private String parentAgentId;

}
