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
public class AgentNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 代理商ID
     */
    private String agentId;

    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 厂商id
     */
    private Long factoryId;

    /**
     * 产品：路由/探针/ap（router/probe/ap）
     */
    private String product;

    /**
     * 设备版本（0.默认版、1.媒体版、2.萤石版，3. GPRS版，4. 付费版，9.天诺版）
     */
    private String edition;

    /**
     * 路由器产品类型：X1,standard,professional,premium,ultimate,sxserver
     */
    private String routerType;

    /**
     * mac
     */
    private String mac;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 是否有效
     */
    private Integer isDelete;


}
