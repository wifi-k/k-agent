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
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AgentAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 代理商表
     */
    private String agentId;

    /**
     * 代理商后台登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 0为操作员，1代理商
     */
    private Integer type;

    /**
     * 代理商操作员phone
     */
    private String userPhone;

    /**
     * 代理商操作员email
     */
    private String userEmail;

    /**
     * 代理商操作员昵称
     */
    private String userNickName;

    /**
     * 账号来源 treebear 树熊，ys 萤石
     */
    private String accountSource;

    /**
     * 0/y,1/n
     */
    private Integer serviceType;

    /**
     * 0/y,1/n
     */
    private Integer isAdmin;

    /**
     * 0/y,1/n
     */
    private Integer isChannelManager;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    private Integer isDelete;


}
