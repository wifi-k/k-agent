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
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String mobile;

    private String email;

    /**
     * 1-email
     */
    private Integer authStatus;

    private String passwd;

    private String avatar;

    private String inviteCode;

    private Long inviteUser;

    /**
     * 1-man 2-woman
     */
    private Integer sex;

    /**
     * 1-email
     */
    private String apikey;

    /**
     * 1-user 2-developer 4-company 8-admin 16-channel
     */
    private Integer role;

    private Long createTime;

    private Long updateTime;

    private Integer isDelete;


}
