package com.base.common.util;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2020/8/5 11:40 PM
 */
public class SqlUtil {

    public static String inStrList(List<String> list){
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append("'" + s + "'").append(",");
        }
        String str = sb.toString();
        if(str.endsWith(",")) {
            str = str.substring(0,str.length()-1);
        }
        return "(" + str + ")";
    }

    public static String inIntList(List<Integer> list){
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(Integer i : list) {
            sb.append(i).append(",");
        }
        String str = sb.toString();
        if(str.endsWith(",")) {
            str = str.substring(0,str.length()-1);
        }
        return "(" + str + ")";
    }
}
