package org.javatop.usercenter.domian.vo.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javatop.usercenter.domian.base.BasePageQuery;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-19 22:44
 * @description :
 */

@AllArgsConstructor
@Data
public class UserPageListVO extends BasePageQuery {
    public String userAccount;


    public String email;
    public String phone;
}
