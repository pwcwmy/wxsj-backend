package com.ruoyi.oss.service.impl;

import com.ruoyi.oss.Repository.AdminRepository;
import com.ruoyi.oss.Repository.ConfigRepository;
import com.ruoyi.oss.model.Config;
import com.ruoyi.oss.model.AdminEntity;
import com.ruoyi.oss.model.UserInfo;
import com.ruoyi.oss.model.vo.OssSet;
import com.ruoyi.oss.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ConfigRepository configRepository;
    @Autowired
    com.tangcheng.face_warehouse.Repository.UserInfoRepository userinfoReponsitory;
    @Override
    public List<AdminEntity> finAllAdmin() {
        List<AdminEntity> all = adminRepository.findAll();
        return all;
    }

    @Override
    public AdminEntity login(Integer adminId, String password) {
        AdminEntity adminEntity = adminRepository.findByAdminIdAndAdminPassword(adminId, password);
        return adminEntity;
    }

    @Override
    public AdminEntity changeRoly(Integer adminId) {
        return null;
    }

    @Override
    public List<Config> getOssConfig(String ConfigTeam) {
        List<Config> distinctByConfigTeam = configRepository.findDistinctByConfigTeam(ConfigTeam);
        return distinctByConfigTeam;
    }

    @Override
    public void saveOssConfig(OssSet ossSet) {
        configRepository.updatevalue(ossSet.getEndpoint(),"endpoint");
        configRepository.updatevalue(ossSet.getAccessKeyId(),"accessKeyId");
        configRepository.updatevalue(ossSet.getAccessKeySecret(),"accessKeySecret");
        configRepository.updatevalue(ossSet.getBucket(),"bucket");

    }

    @Override
    public Config getConfigValueByName(String ConfigName) {
        Config configByConfigName = configRepository.findConfigByConfigName(ConfigName);
        return configByConfigName;
    }

    @Override
    public void insertUser(UserInfo userInfo) {
        userinfoReponsitory.addUserinfo(userInfo.getUserName(),userInfo.getUserSex(),"0");
    }

    @Override
    public List<UserInfo> getUserList(String userflag) {
        List<UserInfo> allByUserFlag = userinfoReponsitory.findAllByUserFlag("0");
        return allByUserFlag;
    }

    @Override
    public int delUser(String userName) {
        int i = userinfoReponsitory.deluser(userName);
        return i;
    }

    @Override
    public List<String> findUsernameList() {
        List<String> usernameList = userinfoReponsitory.findUsernameList();
        return usernameList;
    }
}
