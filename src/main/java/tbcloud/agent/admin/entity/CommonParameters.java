package tbcloud.agent.admin.entity;

import tbcloud.agent.admin.common.PageInfo;

/**
 * 分页查询的基本参数  待续
 * @author: Dmm
 * @date: 2019/4/1 15:40
 */
public class CommonParameters extends PageInfo {

    private String agentId;

    private String token;

    private String parentAgentId;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getParentAgentId() {
        return parentAgentId;
    }

    public void setParentAgentId(String parentAgentId) {
        this.parentAgentId = parentAgentId;
    }
}
