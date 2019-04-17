package tbcloud.agent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tbcloud.admin.api.ApiConstCollection;
import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentInfo;
import tbcloud.agent.admin.entity.AgentNode;
import tbcloud.agent.admin.entity.baby.AgentNodeQuery;
import tbcloud.agent.admin.entity.baby.AgentNodeVo;
import tbcloud.agent.admin.entity.query.ShiftNodeQuery;
import tbcloud.agent.admin.mapper.AgentInfoMapper;
import tbcloud.agent.admin.mapper.AgentNodeMapper;
import tbcloud.agent.admin.service.IAgentAccountService;
import tbcloud.agent.admin.service.IAgentInfoService;
import tbcloud.agent.admin.service.IAgentNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tbcloud.lib.api.ApiCode;
import tbcloud.lib.api.ApiConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
public class AgentNodeServiceImpl extends ServiceImpl<AgentNodeMapper, AgentNode> implements IAgentNodeService {

    @Autowired
    private AgentNodeMapper agentNodeMapper;

    @Autowired
    private AgentInfoMapper agentInfoMapper;

    @Autowired
    IAgentInfoService iAgentInfoService;

    @Autowired
    IAgentAccountService iAgentAccountService;

    /**
     *
//     * @param agentNodeQuery
     * @return
     */
//    @Override
//    @Transactional(readOnly = true,rollbackFor = Exception.class)
//    public Gather<Result<AgentNodeVo>> findByPage(AgentNodeQuery agentNodeQuery) {
//
//        Gather<Result<AgentNodeVo>> gather=new Gather<>();
//
//        try {
//            Result<AgentNodeVo> agentNodeVoResult=selectByList(agentNodeQuery);
//            gather.setData(agentNodeVoResult);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error(e.getMessage(),e);
//            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
//            gather.setData(null);
//            return gather;
//        }
//
//        return gather;
//    }
//
//    /**
//     * 下属代理商的设备
//     * @param agentNodeQuery
//     * @return
//     */
//    @Override
//    @Transactional(readOnly = true,rollbackFor = Exception.class)
//    public Gather<Result<AgentNodeVo>> findByPageJoin(AgentNodeQuery agentNodeQuery) {
//
//        Gather<Result<AgentNodeVo>> gather=new Gather<>();
//
//        Result<AgentNodeVo> agentInfoVoResult=new Result<>();
//
//        try {
//            String agentId=iAgentInfoService.selectByAgentId(agentNodeQuery.getToken());
//
//            Map<String,String> map=new HashMap<>(2);
//            map.put("parent_agent_id",agentId);
//            map.put("is_delete","0");
//
//            QueryWrapper queryWrapper=new QueryWrapper();
//
//            queryWrapper.allEq(map);
//            //查询下级代理商
//            List<AgentInfo> agentInfoList=agentInfoMapper.selectList(queryWrapper);
//
//            if(agentInfoList.isEmpty()){
//
//                agentInfoVoResult.setCount(0);
//                agentInfoVoResult.setPage(null);
//                gather.setData(agentInfoVoResult);
//                return gather;
//            }
//
//            List<AgentNodeVo> agentNodeVoList=new ArrayList<>();
//
//            long count=0;
//
//            for(AgentInfo agentInfo:agentInfoList){
//
//                agentNodeQuery.setAgentId(agentInfo.getAgentId());
//
//                Result<AgentNodeVo> agentNodeVoResult=selectByList(agentNodeQuery);
//
//                for(AgentNodeVo agentNodeVo:agentNodeVoResult.getPage()){
//
//                    if(agentNodeVoList.size() < agentNodeQuery.getLimit()){
//                        agentNodeVoList.add(agentNodeVo);
//                    }
//                    count++;
//                }
//            }
//
//            agentInfoVoResult.setPage(agentNodeVoList);
//            agentInfoVoResult.setCount(count);
//            gather.setData(agentInfoVoResult);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error(e.getMessage(),e);
//            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
//            gather.setData(null);
//            return gather;
//        }
//
//        return gather;
//    }
//
    @Override
    /**
     * 转移设备
     */
    public Gather<Void> shiftNodeJoin(ShiftNodeQuery shiftNodeQuery) {

        Gather<Void> gather=new Gather<>();

        try {
            Map<String,String> map=new HashMap<>(2);
            map.put("agent_name",shiftNodeQuery.getAgentName());
            map.put("is_delete",ApiConstCollection.IS_DELETE_CODE+"");

            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.allEq(map);

            List<AgentInfo> agentInfoList=agentInfoMapper.selectList(queryWrapper);

            if(agentInfoList.isEmpty()|| agentInfoList.size()>1){
                gather.setCode(ApiConstCollection.AGENT_LOGIN_NAME_NO);
                return gather;
            }

            AgentNode agentNode=new AgentNode();
            agentNode.setAgentId(agentInfoList.get(0).getAgentId());
            agentNode.setUpdateTime(System.currentTimeMillis());
            agentNode.setIsDelete(0);

            QueryWrapper queryWrapper2=new QueryWrapper();
            queryWrapper2.eq("node_id",shiftNodeQuery.getNodeId());

            int i=agentNodeMapper.update(agentNode,queryWrapper2);

            if(i==1){
                return gather;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            return gather;
        }
        gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);

        return gather;
    }
//
//    @Override
//    public Gather<Result<AgentNode>> findByPageIs(AgentNodeQueryIs agentNodeQueryIs) {
//
//        Gather<Result<AgentNode>> gather=new Gather<Result<AgentNode>>();
//
//        Result<AgentNode> result=new Result<AgentNode>();
//
//        String agentId=iAgentInfoService.selectByAgentId(agentNodeQueryIs.getToken());
//
//        agentNodeQueryIs.setAgentId(agentId);
//
//        try {
//            QueryWrapper queryWrapper=new QueryWrapper();
//
//            queryWrapper.eq("agent_id",agentId);
//
//            queryWrapper.eq("is_delete", ApiCode.SUCC);
//
//            if(!"".equals(agentNodeQueryIs.getNodeId())){
//                queryWrapper.eq("node_id",agentNodeQueryIs.getNodeId());
//            }
//            if(!"".equals(agentNodeQueryIs.getRouterType())){
//                queryWrapper.eq("router_type",agentNodeQueryIs.getRouterType());
//            }
//            if(!"".equals(agentNodeQueryIs.getEdition())){
//                queryWrapper.eq("edition",agentNodeQueryIs.getEdition());
//            }
//            IPage<AgentNode> iPage=new Page<>(agentNodeQueryIs.getPage(),agentNodeQueryIs.getLimit());
//
//            IPage<AgentNode> agentNodeIPage=agentNodeMapper.selectPage(iPage,queryWrapper);
//
//            long count=agentNodeIPage.getTotal();
//            List<AgentNode> agentNodeList=agentNodeIPage.getRecords();
//
//            result.setCount(count);
//            result.setPage(agentNodeList);
//            gather.setData(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage(),e);
//            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
//            gather.setData(null);
//            return gather;
//        }
//        return gather;
//    }
//
//    @Override
//    public Gather<Result<AgentNode>> findByPageIsJoinIs(AgentNodeQueryIs agentNodeQueryIs) {
//
//        Gather<Result<AgentNode>> gather=new Gather<Result<AgentNode>>();
//
//        Result<AgentNode> result=new Result<AgentNode>();
//        try {
//        //本级代理商 agentId
//        String agentId=iAgentInfoService.selectByAgentId(agentNodeQueryIs.getToken());
//
//        //
//        QueryWrapper queryWrappers=new QueryWrapper();
//        queryWrappers.eq("parent_agent_id",agentId);
//        queryWrappers.eq("is_delete",ApiCode.SUCC);
//        List<AgentInfo> agentInfoList=agentInfoMapper.selectList(queryWrappers);
//
//
//        if(agentInfoList.isEmpty()){
//            result.setCount(0);
//            result.setPage(null);
//            gather.setData(result);
//            return gather;
//        }
//
//
//        List<AgentNode> agentNodeList2=new ArrayList<>();
//        long count2=0;
//
//        for(AgentInfo agentInfo:agentInfoList){
//
//            QueryWrapper queryWrapper=new QueryWrapper();
//
//            queryWrapper.eq("agent_id",agentInfo.getAgentId());
//
//            queryWrapper.eq("is_delete", ApiCode.SUCC);
//
//            if(!"".equals(agentNodeQueryIs.getNodeId())){
//                queryWrapper.eq("node_id",agentNodeQueryIs.getNodeId());
//            }
//            if(!"".equals(agentNodeQueryIs.getRouterType())){
//                queryWrapper.eq("router_type",agentNodeQueryIs.getRouterType());
//            }
//            if(!"".equals(agentNodeQueryIs.getEdition())){
//                queryWrapper.eq("edition",agentNodeQueryIs.getEdition());
//            }
//            IPage<AgentNode> iPage=new Page<>(agentNodeQueryIs.getPage(),agentNodeQueryIs.getLimit());
//
//            IPage<AgentNode> agentNodeIPage=agentNodeMapper.selectPage(iPage,queryWrapper);
//
//            long count=agentNodeIPage.getTotal();
//            List<AgentNode> agentNodeList=agentNodeIPage.getRecords();
//
//            for(AgentNode agentNode:agentNodeList){
//                agentNodeList2.add(agentNode);
//                count2++;
//            }
//
//        }
//        result.setPage(agentNodeList2);
//        result.setCount(count2);
//        gather.setData(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage(),e);
//            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
//            gather.setData(null);
//            return gather;
//        }
//        return gather;
//    }
//

    @Override
    public Gather<Result<AgentNodeVo>> findByPage(AgentNodeQuery agentNodeQuery) {

        Gather<Result<AgentNodeVo>> gather=new Gather<>();
        Result<AgentNodeVo> result=new Result<>();

        try {
            String agentId=iAgentAccountService.findAgentIdToAccountId(agentNodeQuery.getToken());
            agentNodeQuery.setAgentId(agentId);

            List<AgentNodeVo> agentNodeVoList=agentNodeMapper.selectByPage(agentNodeQuery);
            long count=agentNodeMapper.countAgentInfo(agentNodeQuery);

            result.setPage(agentNodeVoList);
            result.setCount(count);

            gather.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            return gather;
        }

        return gather;
    }

    @Override
    public Gather<Result<AgentNodeVo>> findByPageIsJoin(AgentNodeQuery agentNodeQuery) {
        Gather<Result<AgentNodeVo>> gather=new Gather<>();
        Result<AgentNodeVo> result=new Result<>();

        try {
            String agentId=iAgentAccountService.findAgentIdToAccountId(agentNodeQuery.getToken());
            agentNodeQuery.setParentAgentId(agentId);

            List<AgentNodeVo> agentNodeVoList=agentNodeMapper.selectByPage(agentNodeQuery);
            long count=agentNodeMapper.countAgentInfo(agentNodeQuery);

            result.setPage(agentNodeVoList);
            result.setCount(count);

            gather.setData(result);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            return gather;
        }
        return gather;
    }


    /**
     * 下级代理商和本级代理商查询的公共方法
     * @param agentNodeQuery
     * @return
     */
//    public Result<AgentNodeVo> selectByList(AgentNodeQuery agentNodeQuery){
//
//        Result<AgentNodeVo> agentNodeVoResult=new Result<>();
//
//        try{
//
//            String agentId=iAgentInfoService.selectByAgentId(agentNodeQuery.getToken());
//            agentNodeQuery.setAgentId(agentId);
//
//            long count=agentNodeMapper.countAgentInfo(agentNodeQuery);
//            List<AgentNodeVo> agentNodeVoList=agentNodeMapper.selectByPage(agentNodeQuery);
//            //代理商名称
//            for(AgentNodeVo agentNodeVo:agentNodeVoList){
//
//                Map<String,String> map=new HashMap<>(2);
//                map.put("agent_id",agentNodeVo.getAgentId());
//                map.put("is_delete","0");
//
//                QueryWrapper queryWrapper=new QueryWrapper();
//                queryWrapper.allEq(map);
//
//                List<AgentInfo> agentInfoList=agentInfoMapper.selectList(queryWrapper);
//
//                agentNodeVo.setAgentName(agentInfoList.get(0).getAgentName());
//
//            }
//
//            agentNodeVoResult.setPage(agentNodeVoList);
//            agentNodeVoResult.setCount(count);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            log.error(e.getMessage(),e);
//            return null;
//
//        }
//
//        return agentNodeVoResult;
//
//    }

}
