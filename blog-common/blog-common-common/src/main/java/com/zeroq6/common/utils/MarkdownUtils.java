package com.zeroq6.common.utils;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Created by yuuki asuna on 2017/5/26.
 */
public class MarkdownUtils {

    private final static Parser parser = Parser.builder().build();

    private final static HtmlRenderer renderer = HtmlRenderer.builder().build();

    public static String parse(String text){
        if(null == text){
            throw new RuntimeException("text不能为空, " + text);
        }
        return renderer.render(parser.parse(text));
    }
}
