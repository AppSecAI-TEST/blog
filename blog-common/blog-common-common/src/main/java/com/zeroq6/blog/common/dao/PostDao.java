package com.zeroq6.blog.common.dao;

import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.domain.PostDomain;
import org.springframework.stereotype.Service;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public interface PostDao extends BaseDao<PostDomain, Long> {

    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    PostDomain selectPrevPost(Long id);

    PostDomain selectNextPost(Long id);

    /**自定义结束 */

}
