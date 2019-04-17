package tbcloud.agent.admin.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentInfo;

/**
 * @author: Dmm
 * @date: 2019/4/1 16:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentInfoVo extends AgentInfo {

    private String managerName;

    private String loginName;
}
