package com.ruoyi.law.domain;

import com.ruoyi.common.annotation.Excel;

public class Key {
    /** 优先级第一的关键词 */
    @Excel(name = "优先级第一的关键词")
    private String key1;

    /** 优先级第二的关键词 */
    @Excel(name = "优先级第二的关键词")
    private String key2;

    /** 优先级第三的关键词 */
    @Excel(name = "优先级第三的关键词")
    private String key3;

    /** 优先级第四的关键词 */
    @Excel(name = "优先级第四的关键词")
    private String key4;

    /** 优先级第五的关键词 */
    @Excel(name = "优先级第五的关键词")
    private String key5;

    public Key() {
    }

    public Key(String key1, String key2, String key3, String key4, String key5) {
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.key5 = key5;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getKey5() {
        return key5;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }

    @Override
    public String toString() {
        return "Key{" +
                "key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                ", key3='" + key3 + '\'' +
                ", key4='" + key4 + '\'' +
                ", key5='" + key5 + '\'' +
                '}';
    }
}
