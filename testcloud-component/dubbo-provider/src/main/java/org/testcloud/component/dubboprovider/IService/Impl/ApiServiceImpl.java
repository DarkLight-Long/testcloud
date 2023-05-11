package org.testcloud.component.dubboprovider.IService.Impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.testcloud.component.api.IService.ApiService;
import org.testcloud.component.api.model.SysUser;

@DubboService
public class ApiServiceImpl implements ApiService {

    @Override
    public SysUser queryUserInfo() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("Li si");
        return sysUser;
    }

    @Override
    public String getUserName(Integer id) {
        System.out.println(id);
        return "lisi";
    }
}
