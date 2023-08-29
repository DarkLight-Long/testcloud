package org.testcloud.modules.consumer.DemoConsumer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.testcloud.modules.consumer.DemoConsumer.domain.SysUser;

import java.util.List;

@Mapper
public interface SysUserMapper {

    public List<SysUser> selectAll(SysUser sysUser);

    public int insert(SysUser sysUser);

    public int insertBatch(List<SysUser> userList);

}
