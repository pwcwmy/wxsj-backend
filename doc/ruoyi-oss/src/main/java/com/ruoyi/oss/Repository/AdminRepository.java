package com.ruoyi.oss.Repository;

import com.ruoyi.oss.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    /**
     * 登录
     *
     * @param adminId
     * @param password
     * @return
     */
    public AdminEntity findByAdminIdAndAdminPassword(Integer adminId, String password);

}