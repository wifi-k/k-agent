package tbcloud.agent.admin.entity.parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.common.PageInfo;

/**
 * @author: Dmm
 * @date: 2019/4/16 15:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PublicParameter extends PageInfo {

    private String agentId;

    private String token;

}
