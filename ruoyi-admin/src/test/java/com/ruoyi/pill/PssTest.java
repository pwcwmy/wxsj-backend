package com.ruoyi.pill;

import com.ruoyi.pill.domain.PillDrug;
import com.ruoyi.pill.domain.PillFactory;
import com.ruoyi.pill.service.IPillDrugService;
import com.ruoyi.pill.service.IPillFactoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PssTest {
    @Autowired
    private IPillFactoryService pillFactoryService;

    @Autowired
    private IPillDrugService pillDrugService;

    @Test
    public void testSelectFactory() {
        PillFactory pillFactory = new PillFactory();
        pillFactory.setFactoryName("云南");
        System.out.println(pillFactoryService.selectPillFactoryList(pillFactory));
    }

    @Test
    public void testSelectDrug() {
        PillDrug pillDrug = new PillDrug();
        PillFactory pillFactory = new PillFactory();
        pillFactory.setFactoryName("哈药");
        pillDrug.setPillFactory(pillFactory);
        List<PillDrug> pillDrugs = pillDrugService.selectPillDrugList(pillDrug);
        System.out.println(pillDrugs);
    }
}
