package com.ruoyi.oss.service;

import com.ruoyi.oss.model.AdminEntity;
import com.ruoyi.oss.model.Config;
import com.ruoyi.oss.model.UserInfo;
import com.ruoyi.oss.model.vo.OssSet;

import java.util.List;


public interface AdminService {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<AdminEntity> finAllAdmin();

    /**
     * 登录
     * @param adminId
     * @param password
     * @return
     */
    public AdminEntity login(Integer adminId,String password );
    public AdminEntity changeRoly(Integer adminId);
    public List<Config> getOssConfig(String ConfigTeam);
    public void saveOssConfig(OssSet ossset);
    public Config getConfigValueByName(String ConfigName);
    public void insertUser(UserInfo userInfo);
    public List<UserInfo> getUserList(String userflag);
    public int delUser(String userName);
    public List<String> findUsernameList();
}
