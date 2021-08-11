package com.bloom.mybatis.parsing;

public class SQLTokenParser {

    private static final String openToken = "#{";
    private static final String closeToke = "}";

    public static String parse(String text) {
        if (null == text || text.isEmpty()) {
            return "";
        }
        int start = text.indexOf(openToken);
        if (start == -1) {
            return text;
        }
        char[] src = text.toCharArray();
        int offset = 0;
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                builder.append(src, offset, start - offset - 1).append(openToken);
            } else {
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
                    expression.setLength(0);
                }
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToke, offset);
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        expression.append(src, offset, end - offset - 1).append(closeToke);
                        offset = end + closeToke.length();
                        end = text.indexOf(closeToke, offset);
                    } else {
                        expression.append(src, offset, end - offset);
                        offset = end + closeToke.length();
                        break;
                    }
                }
                if (end == -1) {
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    builder.append("?");
                    offset = end + closeToke.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String sql = "select * from blog where id=#{id,jdbcType=INTRGER} and name=#{id,jdbcType=VARCHAR}";
        System.out.println(SQLTokenParser.parse(sql));
    }
}