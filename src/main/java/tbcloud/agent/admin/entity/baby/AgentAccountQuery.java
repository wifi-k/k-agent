package tbcloud.agent.admin.entity.baby;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.parameter.PublicParameter;

/**
 * @author: Dmm
 * @date: 2019/4/16 18:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentAccountQuery extends PublicParameter {

    private String loginName;

    private String userPhone;

    private String userNickName;
}
