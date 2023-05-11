package org.testcloud.component.api.IService;

import org.testcloud.component.api.model.SysUser;

public interface ApiService {

    public SysUser queryUserInfo();

    public String getUserName(Integer id);

}
