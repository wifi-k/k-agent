package tbcloud.agent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tbcloud.admin.api.ApiCodeCollection;
import tbcloud.admin.api.ApiConstCollection;
import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.RedisClient;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentAccount;
import tbcloud.agent.admin.entity.AgentInfo;
import tbcloud.agent.admin.entity.baby.AgentAccountQuery;
import tbcloud.agent.admin.entity.baby.AgentAccountUpdate;
import tbcloud.agent.admin.entity.vo.LoginToken;
import tbcloud.agent.admin.mapper.AgentAccountMapper;
import tbcloud.agent.admin.service.IAgentAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tbcloud.agent.admin.service.IAgentInfoService;
import tbcloud.lib.api.ApiCode;
import tbcloud.lib.api.ApiConst;
import tbcloud.lib.api.util.IDUtil;
import tbcloud.lib.api.util.StringUtil;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hello
 * @since 2019-04-02
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
public class AgentAccountServiceImpl extends ServiceImpl<AgentAccountMapper, AgentAccount> implements IAgentAccountService {


    @Autowired
    AgentAccountMapper agentAccountMapper;

    @Autowired
    IAgentInfoService iAgentInfoService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public Gather<Void> addAgentAccount(AgentAccountUpdate agentAccountUpdate) {


        Gather<Void> gather=new Gather<>();

        try {
            //1.查询用户名是否重名
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("login_name",agentAccountUpdate.getLoginName());

            List<AgentAccount> agentAccountList= agentAccountMapper.selectList(queryWrapper);

            if(!agentAccountList.isEmpty()){
                gather.setCode(ApiConstCollection.AGENT_LOGIN_NAME_NO);
                return gather;
            }

            //2.密码和确认密码比对
            if(!agentAccountUpdate.getPasswd().equals(agentAccountUpdate.getConfirmPassWd())){
                gather.setCode(ApiConstCollection.AGENT_LOGIN_PASSWORD_NO);
                return gather;
            }

            //2.1密码加密
            String rePassword=agentAccountUpdate.getPasswd()+ ApiConst.USER_PASSWD_SALT_1;

            String password= StringUtil.MD5Encode(rePassword);
            agentAccountUpdate.setPasswd(password);

            //3.查询agentId
            String agentId=findAgentIdToAccountId(agentAccountUpdate.getToken());

            agentAccountUpdate.setCreateTime(System.currentTimeMillis());
            agentAccountUpdate.setUpdateTime(System.currentTimeMillis());
            agentAccountUpdate.setAccountSource("treebear");
            agentAccountUpdate.setAgentId(agentId);
            agentAccountUpdate.setType(ApiConstCollection.AGENT_TYPE_OPERATION_CODE);
            agentAccountUpdate.setIsAdmin(ApiConstCollection.AGENT_TYPE_CODE);

            int i=agentAccountMapper.insert(agentAccountUpdate);

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

    @Override
    public Gather<Void> updateAgentAccount(AgentAccountUpdate agentAccountUpdate) {

        Gather<Void> gather=new Gather<>();

        try {
            String agentId=findAgentIdToAccountId(agentAccountUpdate.getToken());

            //2.密码和确认密码比对
            if(!agentAccountUpdate.getPasswd().equals(agentAccountUpdate.getConfirmPassWd())){
                gather.setCode(ApiConstCollection.AGENT_LOGIN_PASSWORD_NO);
                return gather;
            }

            String rePassword=agentAccountUpdate.getPasswd()+ ApiConst.USER_PASSWD_SALT_1;
            String password= StringUtil.MD5Encode(rePassword);
            agentAccountUpdate.setPasswd(password);
            agentAccountUpdate.setUpdateTime(System.currentTimeMillis());

            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("login_name",agentAccountUpdate.getLoginName());
            queryWrapper.eq("agent_id",agentId);

            int i=agentAccountMapper.update(agentAccountUpdate,queryWrapper);

            if(i==1){
                return gather;
            }
        } catch (Exception e) {
            e.printStackTrace();
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            log.error(e.getMessage(),e);
        }
        gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
        return gather;
    }

    @Override
    public Gather<Void> deleteAgentAccount(AgentAccountUpdate agentAccountUpdate) {

        Gather<Void> gather=new Gather<>();
        try {
            String agentId=findAgentIdToAccountId(agentAccountUpdate.getToken());

            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("login_name",agentAccountUpdate.getLoginName());
            queryWrapper.eq("agent_id",agentId);

            AgentAccount agentAccount=new AgentAccount();
            agentAccount.setIsDelete(ApiConstCollection.UPDATE_FAIL_CODE);
            agentAccount.setUpdateTime(System.currentTimeMillis());

            int i=agentAccountMapper.update(agentAccount,queryWrapper);

            if(i==1){
                return gather;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            return gather;
        }
        gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
        return gather;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Gather<Result<AgentAccount>> findByPage(AgentAccountQuery agentAccountQuery) {

        Gather<Result<AgentAccount>> gather=new Gather<>();

        Result<AgentAccount> agentAccountResult=new Result<>();

        try {
            String agentId=iAgentInfoService.selectByAgentId(agentAccountQuery.getToken());

            //分页插件  构建page
            Page<AgentAccount> page=new Page<>(agentAccountQuery.getPage(),agentAccountQuery.getLimit());
            //构建查询条件
            QueryWrapper queryWrapper=new QueryWrapper();

            if(!"".equals(agentAccountQuery.getLoginName())){
                queryWrapper.eq("login_name",agentAccountQuery.getLoginName());
            }

            if(!"".equals(agentAccountQuery.getUserPhone())){
                queryWrapper.eq("user_phone",agentAccountQuery.getUserPhone());
            }

            if(!"".equals(agentAccountQuery.getUserNickName())){
                queryWrapper.likeRight("user_nick_name",agentAccountQuery.getUserNickName());
            }

            queryWrapper.eq("agent_id",agentId);
            queryWrapper.eq("is_delete",ApiConstCollection.IS_DELETE_CODE);
            queryWrapper.orderByDesc("create_time");

            IPage<AgentAccount> userIPage=agentAccountMapper.selectPage(page,queryWrapper);
            // todo assertThat
            long count=userIPage.getTotal();

            List<AgentAccount> agentAccountList=userIPage.getRecords();

            agentAccountResult.setCount(count);

            agentAccountResult.setPage(agentAccountList);

            gather.setData(agentAccountResult);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            gather.setData(null);
            return gather;
        }

        return gather;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Gather<LoginToken> agentAccountLogin(AgentAccount agentAccount) {

        Gather<LoginToken> gather=new Gather<>();


        try {
            LoginToken loginToken=new LoginToken();

            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("login_name",agentAccount.getLoginName());
            queryWrapper.eq("is_delete", ApiConstCollection.IS_DELETE_CODE);

            List<AgentAccount> agentAccountList=agentAccountMapper.selectList(queryWrapper);

            if(agentAccountList.isEmpty() || agentAccountList.size()>1){

                gather.setCode(ApiConstCollection.AGENT_LOGIN_NAME_NO);
                gather.setData(null);
                return gather;
            }

            String pw=agentAccount.getPasswd()+ApiConst.USER_PASSWD_SALT_1;

            boolean b =StringUtil.MD5Encode(pw).equals(agentAccountList.get(0).getPasswd());

            if(b) {
                //允许登录
                String token = IDUtil.genToken(agentAccountList.get(0).getId());
                redisClient.set(ApiConstCollection.REDIS_KEY_AGENT_TOKEN +agentAccountList.get(0).getId(), token);
                redisClient.expire(ApiConstCollection.REDIS_KEY_AGENT_TOKEN + agentAccountList.get(0).getId(), ApiConstCollection.REDIS_ADMIN_TOKEN_EXPIRED);
                log.info("login token {} ", token);
                loginToken.setToken(token);
                gather.setData(loginToken);
                return gather;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.AGENT_UPDATE_FAIL_CODE);
            gather.setData(null);
            return gather;
        }
        gather.setCode(ApiConstCollection.AGENT_LOGIN_PASSWORD_NO);
        gather.setData(null);
        return gather;
    }

    @Override
    public Gather<Void> agentAccountLogout(String token) {

        Gather<Void> gather=new Gather<>();

        try {
            long l=redisClient.del(ApiConstCollection.REDIS_KEY_AGENT_TOKEN+IDUtil.decodeUserIDFromToken(token));

            if(l==1){
                return gather;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            return gather;
        }
        gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
        return gather;
    }

    @Override
    public Gather<AgentAccount> agentAccountIsName(AgentAccount agentAccount) {

        Gather<AgentAccount> gather=new Gather<>();

        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("agent_id",agentAccount.getAgentId());
            queryWrapper.eq("is_delete",ApiConstCollection.IS_DELETE_CODE);
            queryWrapper.eq("type",ApiConstCollection.AGENT_TYPE_CODE);


            List<AgentAccount> agentAccountList=agentAccountMapper.selectList(queryWrapper);

            if(agentAccountList.isEmpty()){
                return null;
            }
            gather.setData(agentAccountList.get(0));

        } catch (Exception e) {
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            gather.setData(null);
            e.printStackTrace();
            return gather;
        }

        return gather;
    }

    @Override
    public String findAgentIdToAccountId(String token) {

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
    public Gather<AgentAccount> findAccountLoginName(AgentAccount agentAccount) {

        Gather<AgentAccount> gather=new Gather<>();

        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("agent_id",agentAccount.getAgentId());
            queryWrapper.eq("is_delete",ApiConstCollection.IS_DELETE_CODE);
            queryWrapper.eq("type",agentAccount.getType());

            List<AgentAccount> agentAccountList=agentAccountMapper.selectList(queryWrapper);

            if(agentAccountList.isEmpty()){
                return null;
            }
            gather.setData(agentAccountList.get(0));

        } catch (Exception e) {
            gather.setCode(ApiConstCollection.UPDATE_FAIL_CODE);
            gather.setData(null);
            e.printStackTrace();
            return gather;
        }

        return gather;
    }

    @Override
    public Gather<AgentAccount> findAgentIdToAccount(String token) {

        Gather<AgentAccount> gather=new Gather<>();

        try {
            long id=IDUtil.decodeUserIDFromEmailToken(token);
            AgentAccount agentAccount=agentAccountMapper.selectById(id);

            gather.setData(agentAccount);

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
