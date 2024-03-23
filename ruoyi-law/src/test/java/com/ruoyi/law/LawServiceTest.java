package com.ruoyi.law;

import com.ruoyi.law.domain.Key;
import com.ruoyi.law.mapper.LawMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LawServiceTest {
//    @Resource
//    private LawMapper lawMapper;
//
//    @Test
//    public void test() {
//        Key keys = new Key();
//        keys.setKey1("引进释放或者丢弃");
//        System.out.println(lawMapper.selectLawByKeys(keys));
//    }

//    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//    // 从 Spring 容器中获取变量obj1
//    Key keys = context.getBean("keys");
//    keys.setKey1("引进释放或者丢弃");
//	System.out.println(keys);
}

