package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.PostDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.DictDomain;
import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.blog.common.domain.RelationDomain;
import com.zeroq6.blog.common.enums.field.EmRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class PostManager extends BaseManager<PostDomain, Long> {

    @Autowired
    private PostDao postDao;

    @Autowired
    private RelationManager relationManager;

    @Autowired
    private DictManager dictManager;

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseDao<PostDomain, Long> getDao() {
        return postDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */


    public List<DictDomain> getTagsById(Long id){
        Assert.notNull(id, "id不能为空");
        List<RelationDomain> relationTagList = relationManager.selectList(new RelationDomain().setType(EmRelationType.WEN_ZHANG_BIAOQIAN.value()).setParentId(id + ""));
        List<DictDomain> tags = new ArrayList<DictDomain>();
        for (RelationDomain relationTag : relationTagList) {
            tags.add(dictManager.selectByKey(Long.valueOf(relationTag.getChildId())));
        }
        return tags;
    }

    public DictDomain getCategoryById(Long id){
        Assert.notNull(id, "id不能为空");
        RelationDomain relationCategory = relationManager.selectOne(new RelationDomain().setType(EmRelationType.WEN_ZHANG_FENLEI.value()).setParentId(id + ""));
        DictDomain category = dictManager.selectByKey(Long.valueOf(relationCategory.getChildId()));
        return category;
    }

    /**自定义结束 */
}
