package tbcloud.agent.admin.service;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentNode;
import com.baomidou.mybatisplus.extension.service.IService;
import tbcloud.agent.admin.entity.baby.AgentNodeQuery;
import tbcloud.agent.admin.entity.baby.AgentNodeVo;
import tbcloud.agent.admin.entity.query.AgentNodeQueryIs;
import tbcloud.agent.admin.entity.query.ShiftNodeQuery;
import tbcloud.agent.admin.entity.vo.AgentNodeVo2;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
public interface IAgentNodeService extends IService<AgentNode> {

//    /**
//     * 查询本级代理商设备
//     * @param agentNodeQuery
//     * @return
//     */
//    Gather<Result<AgentNodeVo>> findByPage(AgentNodeQuery agentNodeQuery);
//
//
//    /**
//     * 查询下级代理商设备
//     * @param agentNodeQuery
//     * @return
//     */
//
//    Gather<Result<AgentNodeVo>> findByPageJoin(AgentNodeQuery agentNodeQuery);
//
    /**
     * 转移设备
     * @param shiftNodeQuery
     * @return
     */
    Gather<Void> shiftNodeJoin(ShiftNodeQuery shiftNodeQuery);
//
//
//    /**
//     *  todo 询本级代理商设备
//     * @param agentNodeQueryIs
//     * @return
//     */
//    Gather<Result<AgentNode>> findByPageIs(AgentNodeQueryIs agentNodeQueryIs);
//
//    /**
//     *  todo 询本级代理商设备 下级
//     * @param agentNodeQueryIs
//     * @return
//     */
//    Gather<Result<AgentNode>> findByPageIsJoinIs(AgentNodeQueryIs agentNodeQueryIs);

    /**
     *  todo 询本级代理商设备 联查
     * @param agentNodeQuery
     * @return
     */
    Gather<Result<AgentNodeVo>> findByPage(AgentNodeQuery agentNodeQuery);

    /**
     *  todo 询本级代理商设备 下级 联查
     * @param agentNodeQuery
     * @return
     */
    Gather<Result<AgentNodeVo>> findByPageIsJoin(AgentNodeQuery agentNodeQuery);


}
