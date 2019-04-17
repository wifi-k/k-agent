package tbcloud.agent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tbcloud.admin.api.ApiCodeCollection;
import tbcloud.admin.api.ApiConstCollection;
import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentAccount;
import tbcloud.agent.admin.entity.AgentInfo;
import tbcloud.agent.admin.entity.UserInfo;
import tbcloud.agent.admin.entity.baby.AgentInfoQuery;
import tbcloud.agent.admin.entity.query.AgentInfoUpdate;
import tbcloud.agent.admin.entity.vo.AgentInfoVo;
import tbcloud.agent.admin.mapper.AgentAccountMapper;
import tbcloud.agent.admin.mapper.AgentInfoMapper;
import tbcloud.agent.admin.mapper.UserInfoMapper;
import tbcloud.agent.admin.service.IAgentAccountService;
import tbcloud.agent.admin.service.IAgentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tbcloud.lib.api.ApiCode;
import tbcloud.lib.api.util.IDUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
public class AgentInfoServiceImpl extends ServiceImpl<AgentInfoMapper, AgentInfo> implements IAgentInfoService {

    @Autowired
    AgentInfoMapper agentInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    AgentAccountMapper agentAccountMapper;

    @Autowired
    IAgentAccountService iAgentAccountService;

    @Override
    public Gather<Result<AgentInfoVo>> findByPage(AgentInfoQuery agentInfoQuery) {

        Gather<Result<AgentInfoVo>> gather = null;

        //后期再改 todo
//        if(agentInfoQuery.getProvince().equals("") && agentInfoQuery.getCity().equals("")){
//
//        }else if(!agentInfoQuery.getProvince().equals("") && agentInfoQuery.getCity().equals("")){
//            agentInfoQuery.setAreaCode(agentInfoQuery.getProvince());
//        }else{
//            agentInfoQuery.setAreaCode(agentInfoQuery.getCity());
//        }

        try {
            gather = new Gather<>();

            Result<AgentInfoVo> agentInfoVoResult = new Result<>();

            String agentId = iAgentAccountService.findAgentIdToAccountId(agentInfoQuery.getToken());

            agentInfoQuery.setAgentId(agentId);
            agentInfoQuery.setParentAgentId(agentId);

            List<AgentInfoVo> agentInfoVoList = agentInfoMapper.selectByPage(agentInfoQuery);

            long count = agentInfoMapper.countAgentInfo(agentInfoQuery);

            for (AgentInfoVo agentInfoVo : agentInfoVoList) {

                UserInfo userInfo = userInfoMapper.selectById(agentInfoVo.getChannelManagerId());

                agentInfoVo.setManagerName(userInfo.getName());
            }

            agentInfoVoResult.setCount(count);
            agentInfoVoResult.setPage(agentInfoVoList);

            gather.setData(agentInfoVoResult);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            gather.setData(null);
            return gather;
        }

        return gather;
    }

    @Override
    //todo 将错就错 agentId为null怎么弄  删除
    public String selectByAgentId(String token) {

        AgentAccount agentAccount = null;
        try {

            long id = IDUtil.decodeUserIDFromToken(token);

            agentAccount = agentAccountMapper.selectById(id);

            if (agentAccount == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
        return agentAccount.getAgentId();
    }

    @Override
    public Gather<Void> updateAgentInfo(AgentInfo agentInfo) {

        Gather<Void> gather = new Gather<>();

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            //queryWrapper.eq("agent_name", agentInfo.getAgentName());
            queryWrapper.eq("is_delete", ApiCodeCollection.SUCCESS);
            queryWrapper.eq("agent_id", agentInfo.getAgentId());

            int i = agentInfoMapper.update(agentInfo, queryWrapper);

            if (i == 1) {
                return gather;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            return gather;
        }
        gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
        return gather;
    }

    @Override
    public Gather<AgentInfo> selectAgentInfo(AgentInfo agentInfo) {

        Gather<AgentInfo> gather = new Gather<>();

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("agent_id", agentInfo.getAgentId());
            queryWrapper.eq("agent_name", agentInfo.getAgentName());
            queryWrapper.eq("is_delete", ApiCodeCollection.SUCCESS);

            List<AgentInfo> agentInfoList = agentInfoMapper.selectList(queryWrapper);


//            for(AgentInfoVo agentInfoVo:agentInfoList){
//                AgentAccount agentAccount=new AgentAccount();
//                agentAccount.setAgentId(agentInfoVo.getAgentId());
//                Gather<AgentAccount> agentAccountGather=iAgentAccountService.agentAccountIsName(agentAccount);
//
//                if(agentAccountGather.getData()==null){
//                   agentInfoVo.setLoginName("");
//                }
//                agentInfoVo.setLoginName(agentAccountGather.getData().getLoginName());
//            }

            gather.setData(agentInfoList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            gather.setData(null);
            return gather;
        }

        return gather;
    }

    /**
     * one sql
     * @param agentInfoQuery
     * @return
     */
    @Override
    public Gather<AgentInfoVo> selectAgentInfo(AgentInfoQuery agentInfoQuery) {

        Gather<AgentInfoVo> gather=new Gather<>();

        try {
//            AgentInfoQuery agentInfoQuery=new AgentInfoQuery();
//
//            agentInfoQuery.setAgentName(agentInfo.getAgentName());
//            agentInfoQuery.setAgentId(agentInfo.getAgentId());

            List<AgentInfoVo> agentInfoVoList=agentInfoMapper.selectByName(agentInfoQuery);

            if(agentInfoVoList.isEmpty()){
                gather.setData(null);
                return gather;
            }
            for(AgentInfoVo agentInfoVo:agentInfoVoList){
                AgentAccount agentAccount=new AgentAccount();
                agentAccount.setAgentId(agentInfoVo.getAgentId());
                Gather<AgentAccount> agentAccountGather=iAgentAccountService.agentAccountIsName(agentAccount);

                if(agentAccountGather.getData()==null){
                   agentInfoVo.setLoginName("");
                }
                agentInfoVo.setLoginName(agentAccountGather.getData().getLoginName());
            }

            gather.setData(agentInfoVoList.get(0));
        } catch (Exception e) {
            gather.setData(null);
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            e.printStackTrace();
            return gather;
        }
        return gather;
    }

//    @Override
//    public Gather<AgentInfoVo> selectAgentInfo(AgentInfoQuery agentInfoQuery) {
//
//        Gather<AgentInfoVo> gather2=new Gather<>();
//
//        try {
//            QueryWrapper queryWrapper=new QueryWrapper();
//            queryWrapper.eq("is_delete", ApiCode.SUCC);
//            if(!agentInfoQuery.getAgentName().equals("")){
//                queryWrapper.eq("agent_name",agentInfoQuery.getAgentName());
//            }
//            if(!agentInfoQuery.getAgentId().equals("")){
//                queryWrapper.eq("agent_id",agentInfoQuery.getAgentId());
//            }
//            List<AgentInfo> agentInfoList=agentInfoMapper.selectList(queryWrapper);
//
//            if(agentInfoList.isEmpty()){
//                return null;
//            }
//
//
//
//
//            //强转
//            AgentInfo agentInfo=agentInfoList.get(0);
//
//            Field[] fields=agentInfo.getClass().getDeclaredFields();
//
//
//            for(int i=0,len=fields.length;i<len;i++){
//                String varName = fields[i].getName();
//                try {
//                    // 获取原来的访问控制权限
//                    boolean accessFlag = fields[i].isAccessible();
//                    // 修改访问控制权限
//                    fields[i].setAccessible(true);
//                    // 获取在对象f中属性fields[i]对应的对象中的变量
//                    Object o;
//                    try {
//                        o = fields[i].get(agentInfo);
//                        System.err.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
//                    } catch (IllegalAccessException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    // 恢复访问控制权限
//                    fields[i].setAccessible(accessFlag);
//                } catch (IllegalArgumentException ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//
//            agentInfo=new AgentInfoVo();
//
//            AgentInfoVo agentInfoVo=(AgentInfoVo)agentInfo;
//
//            AgentAccount agentAccount=new AgentAccount();
//            agentAccount.setType(1);
//            agentAccount.setAgentId(agentInfoVo.getAgentId());
//            Gather<AgentAccount> gather=iAgentAccountService.findAccountLoginName(agentAccount);
//
//            if(gather.getData()==null){
//                agentInfoVo.setLoginName("");
//            }else{
//                agentInfoVo.setLoginName(gather.getData().getLoginName());
//            }
//            gather2.setData(agentInfoVo);
//        } catch (Exception e) {
//            gather2.setData(null);
//            gather2.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
//            e.printStackTrace();
//            return gather2;
//        }
//
//        return gather2;
//    }


}

