package com.ruoyi.oss.Repository;


import com.ruoyi.oss.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ConfigRepository extends JpaRepository<Config,Integer> {
    public List<Config> findDistinctByConfigTeam(String ConfigTeam);
    @Transactional
    @Modifying
    @Query(value = "update config set config_value  =?1 where config_name =?2",nativeQuery = true)
    public void updatevalue(String configValue,String configName);
    public Config findConfigByConfigName(String ConfigName);


}
