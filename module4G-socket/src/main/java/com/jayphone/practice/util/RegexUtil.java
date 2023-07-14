package com.jayphone.practice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/6
 */
public class RegexUtil {
    public static final String DEVICE_ID_PATTERN = "^\r\n\\d{15}\r\n\r\nOK\r\n$";

    /**
     * 是否匹配
     *
     * @param regex
     * @param input
     * @return
     */
    private static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 正则表达式匹配判断
     *
     * @param patternStr 匹配规则
     * @param input      需要做匹配操作的字符串
     * @return true if matched, else false
     */
    public static boolean isMatched(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
