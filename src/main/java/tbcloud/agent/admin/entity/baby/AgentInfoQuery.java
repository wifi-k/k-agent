package tbcloud.agent.admin.entity.baby;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.parameter.PublicParameter;

/**
 * @author: Dmm
 * @date: 2019/4/16 16:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentInfoQuery extends PublicParameter {


    private String agentName;

    private String province;

    private String city;

    private String agentType;

    private String agentStatus;

    private String parentAgentId;

    private long currentTime=System.currentTimeMillis();

    /**
     * todo 删除
     */
    private String areaCode;

}
