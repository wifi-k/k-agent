package tbcloud.agent.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tbcloud.agent.admin.entity.AgentNode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tbcloud.agent.admin.entity.baby.AgentNodeQuery;
import tbcloud.agent.admin.entity.baby.AgentNodeVo;

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
public interface AgentNodeMapper extends BaseMapper<AgentNode> {

//    /**
//     * 一表分两表  待确定
//     * @param agentNodeQuery
//     * @return
//     */
//    List<AgentNodeVo> selectByPage(@Param("agentNodeQuery") AgentNodeQuery agentNodeQuery);
//
//    /**
//     * 一表分两表 待确定
//     * @param agentNodeQuery
//     * @return
//     */
//    long countAgentInfo(@Param("agentNodeQuery")AgentNodeQuery agentNodeQuery);

    /**
     * 连表查
     * @param agentNodeQuery
     * @return
     */
    List<AgentNodeVo> selectByPage(@Param("agentNodeQuery") AgentNodeQuery agentNodeQuery);

    /**
     * 查询数量
     * @param agentNodeQuery
     * @return
     */
    long countAgentInfo(@Param("agentNodeQuery") AgentNodeQuery agentNodeQuery);

}
