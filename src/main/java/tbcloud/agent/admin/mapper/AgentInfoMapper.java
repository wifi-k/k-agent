package tbcloud.agent.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tbcloud.agent.admin.entity.AgentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tbcloud.agent.admin.entity.baby.AgentInfoQuery;
import tbcloud.agent.admin.entity.vo.AgentInfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
@Mapper
@Component
public interface AgentInfoMapper extends BaseMapper<AgentInfo> {

    /**
     * 查询代理商的个人信息
     * @param agentInfoQuery
     * @return
     */
    List<AgentInfoVo> selectByPage(@Param("agentInfoQuery") AgentInfoQuery agentInfoQuery);

    /**
     * 查询代理商的个数
     * @param agentInfoQuery
     * @return
     */
    long countAgentInfo(@Param("agentInfoQuery")AgentInfoQuery agentInfoQuery);




    /**
     *  详情查询代理商
     * @param agentInfoQuery
     * @return
     */

    List<AgentInfoVo> selectByName(@Param("agentInfoQuery") AgentInfoQuery agentInfoQuery);

}
