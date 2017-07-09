package com.zeroq6.blog.common.enums.field;

import com.zeroq6.blog.common.enums.EnumApi;

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
public enum EmMetaDataType implements EnumApi{

    /**
     *  元数据 => meta
     */
    WENZHANG(1, "文章"),
;

    private final int value;
    private final String title;

    private EmMetaDataType(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public String getTitle(){
        return title;
    }

    public int value() {
        return getValue();
    }

    public String title() {
        return getTitle();
    }

    public static EmMetaDataType of(final int value) {
        for (EmMetaDataType em : EmMetaDataType.values()) {
            if (em.value == value) {
                return em;
            }
        }
        throw new RuntimeException("无法查找枚举值: " + EmMetaDataType.class.getSimpleName() + ", " + value);
    }

    public static String getTitle(final int value){
        EmMetaDataType em = of(value);
        return em.title();
    }

    public static String title(final int value){
        return getTitle(value);
    }

    @Override
    public String toString() {
        return this.title;
    }

    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */
}
