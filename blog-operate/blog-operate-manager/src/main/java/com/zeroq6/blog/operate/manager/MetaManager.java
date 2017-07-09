package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.MetaDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.MetaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class MetaManager extends BaseManager<MetaDomain, Long> {

    @Autowired
    private MetaDao metaDao;

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseDao<MetaDomain, Long> getDao() {
        return metaDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */
}
