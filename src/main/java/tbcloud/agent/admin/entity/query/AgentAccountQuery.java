package tbcloud.agent.admin.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tbcloud.agent.admin.entity.AgentAccount;

import java.io.Serializable;

/**
 * 查询用户
 * @author: Dmm
 * @date: 2019/4/3 12:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentAccountQuery extends AgentAccount implements Serializable {

    private String token;

    private int page;
    private int limit;

}
