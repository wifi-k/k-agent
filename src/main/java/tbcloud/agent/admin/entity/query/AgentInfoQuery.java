package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.CommonParameters;

/**
 * @author: Dmm
 * @date: 2019/4/1 15:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentInfoQuery extends CommonParameters {

    private String agentName;

    private String province;

    private String city;
    /**
     * 代理商类型 正式 测试
     */
    private String agentType;
    /**
     * 有效期的状态 正常0  1停用  2过期
     */
    private Byte agentStatus;
    /**
     * 有效期的状态 正常0 已过期 1
     */
    private Byte limitedStatus;

    private String agentGrade;
    /**
     * 当前时间
     */
    private Long currentTime;
    /**
     * 进行市区的区分
     */
    private String areaCode;

}
