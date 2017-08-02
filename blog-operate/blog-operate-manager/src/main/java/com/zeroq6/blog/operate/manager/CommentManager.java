package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.CommentDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.CommentDomain;
import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**自定义开始 */


/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class CommentManager extends BaseManager<CommentDomain, Long> {

    @Autowired
    private CommentDao commentDao;

    /**自定义开始 */

    @Autowired
    private PostManager postManager;

    /**自定义结束 */

    @Override
    public BaseDao<CommentDomain, Long> getDao() {
        return commentDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    @Transactional(value = "tx", rollbackFor = Exception.class)
    public BaseResponse<String> post(CommentDomain commentDomain, PostDomain postDomain) {
        insertFillingId(commentDomain);
        return new BaseResponse<String>(true, "成功", commentDomain.getId() + "");
    }

    /**自定义结束 */
}
