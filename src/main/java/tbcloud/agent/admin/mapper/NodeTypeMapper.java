package tbcloud.agent.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tbcloud.agent.admin.entity.NodeType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hello
 * @since 2019-04-17
 */
@Mapper
@Component
public interface NodeTypeMapper extends BaseMapper<NodeType> {

}
