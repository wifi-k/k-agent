package tbcloud.agent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tbcloud.admin.api.ApiConstCollection;
import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.NodeType;
import tbcloud.agent.admin.mapper.NodeTypeMapper;
import tbcloud.agent.admin.service.INodeTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tbcloud.lib.api.ApiCode;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hello
 * @since 2019-04-17
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
public class NodeTypeServiceImpl extends ServiceImpl<NodeTypeMapper, NodeType> implements INodeTypeService {


    @Autowired
    private NodeTypeMapper nodeTypeMapper;


    @Override
    public Gather<Result<NodeType>> selectByPage() {
        Gather<Result<NodeType>> gather=new Gather<>();
        Result<NodeType> result=new Result<>();

        try {
            QueryWrapper<NodeType> nodeTypeQueryWrapper=new QueryWrapper<NodeType>();
            nodeTypeQueryWrapper.eq("is_delete", ApiConstCollection.IS_DELETE_CODE);
            List<NodeType> nodeTypeList=nodeTypeMapper.selectList(nodeTypeQueryWrapper);
            result.setPage(nodeTypeList);

            gather.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            gather.setData(null);
            return gather;
        }
        return gather;
    }

}
