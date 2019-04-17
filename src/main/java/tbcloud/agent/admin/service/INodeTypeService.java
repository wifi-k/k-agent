package tbcloud.agent.admin.service;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.NodeType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hello
 * @since 2019-04-17
 */
public interface INodeTypeService extends IService<NodeType> {

    /**
     * 设备类型
     * @return
     */
    Gather<Result<NodeType>> selectByPage();
}
