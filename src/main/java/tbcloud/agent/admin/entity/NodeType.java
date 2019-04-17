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
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NodeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工厂的id
     */
    private Long factoryId;

    /**
     * 路由器类型，wi_router.routerType参考
     */
    private String routerType;

    /**
     * 路由器类型别名
     */
    private String aliasName;

    /**
     * 产品：（路由/探针/AP：router/probe/ap）
     */
    private String product;

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
