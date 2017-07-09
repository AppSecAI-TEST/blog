package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.PostDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.PostDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseDao<PostDomain, Long> getDao() {
        return postDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */
}
