package tbcloud.agent.admin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Dmm
 * @date: 2019/4/3 11:56
 */
@Data
public class PublicParameter implements Serializable {

    private String agentId;

    private String token;

}
