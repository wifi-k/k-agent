package tbcloud.agent.admin.service;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import tbcloud.agent.admin.entity.baby.AgentAccountQuery;
import tbcloud.agent.admin.entity.baby.AgentAccountUpdate;
import tbcloud.agent.admin.entity.vo.LoginToken;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hello
 * @since 2019-04-02
 */

public interface IAgentAccountService extends IService<AgentAccount> {

    /**
     * 增加用户
     * @param agentAccountUpdate
     * @return
     */
    Gather<Void> addAgentAccount(AgentAccountUpdate agentAccountUpdate);

    /**
     * 修改
     * @param agentAccountUpdate
     * @return
     */
    Gather<Void> updateAgentAccount(AgentAccountUpdate agentAccountUpdate);

    /**
     *  删除  伪删除
     * @param agentAccountUpdate
     * @return
     */
    Gather<Void> deleteAgentAccount(AgentAccountUpdate agentAccountUpdate);

    /**
     * 分页查询
     * @param agentAccountQuery
     * @return
     */
    Gather<Result<AgentAccount>> findByPage(AgentAccountQuery agentAccountQuery);

    /**
     * 登录查询
     * @param agentAccount
     * @return
     */
    Gather<LoginToken> agentAccountLogin(AgentAccount agentAccount);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Gather<Void> agentAccountLogout(String token);

    /**
     * 查询代理商名称
     * @param agentAccount
     * @return
     */
    Gather<AgentAccount> agentAccountIsName(AgentAccount agentAccount);

    /**
     * 通过token-->account-->agent_id todo 提出来
     * @param token
     * @return
     */
    String findAgentIdToAccountId(String token);

    /**
     * 查询登录名 todo ok
     * @return
     */
    Gather<AgentAccount> findAccountLoginName(AgentAccount agentAccount);

}
