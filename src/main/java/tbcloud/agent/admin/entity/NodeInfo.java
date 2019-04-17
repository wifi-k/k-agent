package tbcloud.agent.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class NodeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "node_id", type = IdType.INPUT)
    private String nodeId;

    private String manufactory;

    private String model;

    private String firmware;

    private Long bindTime;

    /**
     * 1-bind 0-unbind
     */
    private Integer isBind;

    private Integer isShare;

    private String comment;

    private Long createTime;

    private Long updateTime;

    private Integer isDelete;

    private String partner;

    /**
     * MB
     */
    private Integer memory;

    /**
     * MB
     */
    private Integer disk;

    /**
     * mb
     */
    private Integer upstream;

    /**
     * mb
     */
    private Integer downstream;

    private Long unbindTime;

    /**
     * join share timestamp
     */
    private Long shareTime;

    /**
     * quit 
     */
    private Long unshareTime;

    private Long userId;

    private String ip;

    private String name;

    private String inviteCode;


}
