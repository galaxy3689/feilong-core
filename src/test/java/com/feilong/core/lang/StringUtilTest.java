/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.lang;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.DatePattern;
import com.feilong.core.date.DateUtil;
import com.feilong.test.User;
import com.feilong.tools.jsonlib.JsonUtil;

/**
 * The Class StringUtilTest.
 * 
 * @author feilong
 * @version 1.0 2011-1-7 下午02:41:08
 */
public class StringUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilTest.class);

    /** <code>{@value}</code>. */
    private static final String TEXT   = "jinxin.feilong";

    /**
     * String add int.
     */
    @Test
    public void stringAddInt(){
        assertEquals("004", StringUtil.stringAddInt("002", 2));
        assertEquals("001202", StringUtil.stringAddInt("000002", 1200));
    }

    /**
     * Test contains.
     */
    @Test
    public void testContains(){
        assertEquals(false, StringUtils.contains(null, ""));
        assertEquals(true, StringUtils.contains("", ""));
        assertEquals(true, StringUtil.contains("jiiiiiinxin.feilong", "xin"));
    }

    /**
     * Search count.
     */
    @Test
    public void testReplace(){
        String source = "jiiiiiinxin.feilong";
        LOGGER.info(StringUtil.replace(source, null) + "");

        Map<String, Object> valuesMap = new HashMap<String, Object>();
        valuesMap.put("today", DateUtil.date2String(new Date(), DatePattern.COMMON_DATE));
        valuesMap.put("user", new User(1L));
        LOGGER.info(StringUtil.replace("${today}${today1}${user.id}${user}", valuesMap) + "");
    }

    /**
     * Search count.
     */
    @Test
    public void testSearchCount(){
        String source = "jiiiiiinxin.feilong";
        assertEquals(8, StringUtil.searchTimes(source, "i"));
        assertEquals(2, StringUtil.searchTimes(source, "in"));
        assertEquals(3, StringUtil.searchTimes(source, "ii"));
        assertEquals(1, StringUtil.searchTimes(source, "xin"));
        assertEquals(1, StringUtil.searchTimes("xin", "xin"));
        assertEquals(1, StringUtil.searchTimes("xin", "i"));
        assertEquals(2, StringUtil.searchTimes("xiin", "i"));
        assertEquals(2, StringUtil.searchTimes("xiiiin", "ii"));
    }

    /**
     * Compare to.
     */
    @Test
    public void compareTo(){
        LOGGER.info("" + "8".compareTo("13"));
        Integer parseInt = Integer.parseInt("8");
        LOGGER.info("" + parseInt.compareTo(Integer.parseInt("13")));
        LOGGER.info("" + "12".compareTo("13"));
    }

    /**
     * Adds the double quotes.
     */
    @Test
    public void addDoubleQuotes(){
        assertEquals("\"" + "jinxin.feilong" + "\"", StringUtil.addDoubleQuotes(TEXT));
    }

    /**
     * Checks if is contain ignore case.
     */
    @Test
    public void testContainsIgnoreCase(){
        LOGGER.info(StringUtil.containsIgnoreCase(null, "") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, null) + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feilong") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feilong1") + "");
        LOGGER.info(StringUtil.containsIgnoreCase(TEXT, "feiLong") + "");
        assertEquals(true, StringUtil.containsIgnoreCase("jiiiiiinxin.feilong", "Xin"));
    }

    /**
     * Format.
     */
    @Test
    public void format(){
        LOGGER.info(StringUtil.format("%03d", 1));
        LOGGER.info(StringUtil.format("%s%n%s%h", 1.2d, 2, "feilong"));
        LOGGER.info(StringUtil.format("%+d", -5));
        LOGGER.info(StringUtil.format("%-5d", -5));
        LOGGER.info(StringUtil.format("%05d", -5));
        LOGGER.info(StringUtil.format("% 5d", -5));
        LOGGER.info(StringUtil.format("%,f", 5585458.254f));
        LOGGER.info(StringUtil.format("%(f", -5585458.254f));
        LOGGER.info(StringUtil.format("%#f", -5585458.254f));
        LOGGER.info(StringUtil.format("%f和%<3.3f", 9.45));
        LOGGER.info(StringUtil.format("%2$s,%1$s", 99, "abc"));
        LOGGER.info(StringUtil.format("%1$s,%1$s", 99));
    }

    /**
     * Replace.
     */
    @Test
    public void replace(){
        assertEquals("黑色_黄色_蓝色", StringUtil.replace("黑色/黄色/蓝色", "/", "_"));
    }

    /**
     * Replace all.
     */
    @Test
    public void replaceAll(){
        assertEquals("黑色_黄色_蓝色", StringUtil.replaceAll("黑色/黄色/蓝色", "/", "_"));
        LOGGER.info(StringUtil.replaceAll("SH1265,SH5951", "([a-zA-Z]+[0-9]+)", "'$1'"));
        LOGGER.info(StringUtil.replace("SH1265,SH5951", "([a-zA-Z]+[0-9]+)", "'$1'"));
        LOGGER.info("SH1265,SH5951".replaceFirst("([a-zA-Z]+[0-9]+)", "'$1'"));
    }

    /**
     * 分隔字符串并添加引号.
     */
    @Test
    public void splitAndAddYinHao(){
        String a = "12345,56789,1123456";
        String[] aStrings = a.split(",");
        StringBuilder sb = new StringBuilder();
        int size = aStrings.length;
        for (int i = 0; i < size; i++){
            sb.append("'" + aStrings[i] + "'");
            if (i != size - 1){
                sb.append(",");
            }
        }
        LOGGER.info(sb.toString());
    }

    /**
     * Test sub string.
     */
    @Test
    public void testSubString(){
        LOGGER.info(StringUtil.substring(TEXT, "i", "g"));
        LOGGER.info(StringUtils.substringBetween(TEXT, "i", "g"));
    }

    /**
     * Substring2.
     */
    @Test
    public void substring2(){
        LOGGER.info(StringUtil.substring(null, 6, 8));
        LOGGER.info(StringUtil.substring(TEXT, TEXT.length(), 8));
        LOGGER.info(StringUtil.substring(TEXT, TEXT.length() - 1, 8));
        LOGGER.info(StringUtil.substring(TEXT, 1, 0));
        LOGGER.info(StringUtil.substring(TEXT, 0, 5));
        Assert.assertEquals(".f", StringUtil.substring(TEXT, 6, 2));
        LOGGER.info(StringUtil.substring(TEXT, 6, 20));
    }

    /**
     * Substring3.
     */
    @Test
    public void substring3(){
        LOGGER.info(StringUtil.substring(null, "in", 8));
        LOGGER.info(StringUtil.substring(TEXT, null, 8));
        LOGGER.info(StringUtil.substring(TEXT, "sin", 8));
        LOGGER.info(StringUtil.substring(TEXT, "in", 0));
        LOGGER.info(StringUtil.substring(TEXT, "in", 5));
        // LOGGER.info(StringUtil.substring(text, "in", -2));
        LOGGER.info(StringUtil.substring(TEXT, "in", 20));
        LOGGER.info(StringUtil.substring(TEXT, "j", TEXT.length() - 1));
        LOGGER.info(StringUtil.substring(TEXT, "jinxin.", 1));
    }

    /**
     * Test substring3.
     */
    @Test
    public void testSubstring3(){
        LOGGER.info(StringUtil.substring(null, "in"));
        LOGGER.info(StringUtil.substring(TEXT, null));
        LOGGER.info(StringUtil.substring(TEXT, "sin"));
        LOGGER.info(StringUtil.substring(TEXT, "."));
        LOGGER.info(StringUtil.substring(TEXT, "j"));
        LOGGER.info(StringUtil.substring(TEXT, "jinxin."));
    }

    /**
     * Substring.
     */
    @Test
    public void substring(){
        assertEquals(
                        "src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java",
                        StringUtil.substring("Index: src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java", "Index: ".length()));
        assertEquals(".feilong", StringUtil.substring(TEXT, "jinxin".length()));
        assertEquals(".feilong", StringUtil.substring(TEXT, 6));
        assertEquals("ng", StringUtil.substring(TEXT, -2));
    }

    /**
     * Test substring last.
     */
    @Test
    public void testSubstringLast(){
        assertEquals("ilong", StringUtil.substringLast(TEXT, 5));
    }

    /**
     * Test substring without last.
     */
    @Test
    public void testSubstringWithoutLast(){
        assertEquals("jinxin.fe", StringUtil.substringWithoutLast(TEXT, 5));
    }

    /**
     * Test substring without last.
     */
    @Test
    public void testSubstringWithoutLast2(){
        assertEquals("jinxin.feilo", StringUtil.substringWithoutLast(TEXT, "ng"));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, ""));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, null));
        assertEquals("", StringUtil.substringWithoutLast(null, ""));
    }

    /**
     * Tokenize to string array2.
     */
    @Test
    public void tokenizeToStringArray2(){
        String str = "jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong";
        String delimiters = "h";
        String[] tokenizeToStringArray = StringUtil.tokenizeToStringArray(str, delimiters, false, false);
        LOGGER.info(JsonUtil.format(tokenizeToStringArray));
    }

    /**
     * Tokenize to string array1.
     */
    @Test
    public void tokenizeToStringArray1(){
        String str = "jin.xin  feilong ,jinxin;venusdrogon;jim ";
        String delimiters = ";, .";
        String[] tokenizeToStringArray = StringUtil.tokenizeToStringArray(str, delimiters);
        LOGGER.info(JsonUtil.format(tokenizeToStringArray));
    }
}