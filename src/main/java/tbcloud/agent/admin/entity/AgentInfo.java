package tbcloud.agent.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AgentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 代理商id
     */
    private String agentId;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 代理商简称
     */
    private String agentShortName;

    /**
     * 代理商类型 0正常,1测试,2 无效 -1 全部
     */
    private String agentType;

    /**
     * 父级代理商ID
     */
    private String parentAgentId;

    /**
     * 渠道经理ID
     */
    private Long channelManagerId;

    /**
     * 代理商地址
     */
    private String location;

    /**
     * 有效期开始时间
     */
    private Long startTime;

    /**
     * 有效期结束时间
     */
    private Long endTime;

    /**
     * 账号来源
     */
    private String sourceFrom;

    /**
     * 代理商类型代理商类型:province/省级代理,city/市级代理,district/区域代理,agency/经销商
     */
    private String agentCategory;

    /**
     * 代理商等级:gold/金牌,silver/银牌,bronze/普通
     */
    private String agentGrade;

    /**
     * 代理商状态,0正常,1停用
     */
    private Integer agentStatus;

    /**
     * 停用原因
     */
    private String blockCause;

    /**
     * 角色分类:agent:代理商, strategy:战略, business:城市商圈, register:自注册
     */
    private String agentRole;

    /**
     * 代理产品(witown,树熊,小K)
     */
    private String agentProduct;

    /**
     * 代理商邮箱
     */
    private String agentEmail;

    /**
     * 代理商服务电话
     */
    private String agentServicePhone;

    /**
     * 代理商联系电话
     */
    private String agentContactPhone;

    /**
     * 代理商联系人
     */
    private String agentContacts;

    /**
     * 设备数量
     */
    private Long routerNum;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 是否有效(0/y,1/n)
     */
    private Integer isDelete;


}
