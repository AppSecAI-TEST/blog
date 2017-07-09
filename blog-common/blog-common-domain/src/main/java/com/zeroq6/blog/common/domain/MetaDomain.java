package com.zeroq6.blog.common.domain;

import com.zeroq6.common.base.BaseDomain;

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
public class MetaDomain extends BaseDomain<MetaDomain> {

    private static final long serialVersionUID = 1L;

    /**
    * 元数据 => meta
    */
    public MetaDomain(){
        // 默认无参构造
    }

    /**
     * 数据类型，1，文章
     */
    private Integer dataType;
    /**
     * 数据主键
     */
    private Long dataId;
    /**
     * 数据属性
     */
    private String dataAttribute;
    /**
     * 数据值
     */
    private String dataValue;

    /**
     * 获取数据类型，1，文章 dataType
     *
     * @return
     */
    public Integer getDataType() {
        return dataType;
    }
    /**
     * 设置数据类型，1，文章 dataType
     *
     * @param dataType 数据类型，1，文章
     */
    public MetaDomain setDataType(Integer dataType) {
        this.dataType = dataType;
        return this;
    }

    /**
     * 获取数据主键 dataId
     *
     * @return
     */
    public Long getDataId() {
        return dataId;
    }
    /**
     * 设置数据主键 dataId
     *
     * @param dataId 数据主键
     */
    public MetaDomain setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    /**
     * 获取数据属性 dataAttribute
     *
     * @return
     */
    public String getDataAttribute() {
        return dataAttribute;
    }
    /**
     * 设置数据属性 dataAttribute
     *
     * @param dataAttribute 数据属性
     */
    public MetaDomain setDataAttribute(String dataAttribute) {
        this.dataAttribute = dataAttribute;
        return this;
    }

    /**
     * 获取数据值 dataValue
     *
     * @return
     */
    public String getDataValue() {
        return dataValue;
    }
    /**
     * 设置数据值 dataValue
     *
     * @param dataValue 数据值
     */
    public MetaDomain setDataValue(String dataValue) {
        this.dataValue = dataValue;
        return this;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */
}