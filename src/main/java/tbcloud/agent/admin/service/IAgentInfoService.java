package tbcloud.agent.admin.service;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import tbcloud.agent.admin.entity.baby.AgentInfoQuery;
import tbcloud.agent.admin.entity.query.AgentInfoUpdate;
import tbcloud.agent.admin.entity.vo.AgentInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
public interface IAgentInfoService extends IService<AgentInfo> {

    /**
     * 代理商分页查询
     * @param agentInfoQuery
     * @return
     */
    Gather<Result<AgentInfoVo>> findByPage(AgentInfoQuery agentInfoQuery);

    /**
     * 返回代理商的agent_id
     * @param token
     * @return
     */
    String selectByAgentId(String token);

    /**
     * 修改代理商
     * @param agentInfo
     * @return
     */
    Gather<Void> updateAgentInfo(AgentInfo agentInfo);

    /**
     * 查询详情  待续 todo 废弃 缺少loginName
     * @param agentInfo
     * @return
     */
    Gather<AgentInfo> selectAgentInfo(AgentInfo agentInfo);

    /**
     * 查询详情  待续
     * @param agentInfoQuery
     * @return
     */
    Gather<AgentInfoVo> selectAgentInfo(AgentInfoQuery agentInfoQuery);


    /**
     * 查询详情  待续 todo OK
     * @param agentInfoQuery
     * @return
     */
//    Gather<AgentInfoVo> selectAgentInfo(AgentInfoQuery agentInfoQuery);


}
