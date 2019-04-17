package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Dmm
 * @date: 2019/4/2 13:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShiftNodeQuery {

    private String agentName;

    private String nodeId;

}
