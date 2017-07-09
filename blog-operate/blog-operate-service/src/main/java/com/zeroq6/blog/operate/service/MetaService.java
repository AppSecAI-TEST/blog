package com.zeroq6.blog.operate.service;

import com.zeroq6.blog.common.domain.MetaDomain;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.base.BaseService;
import com.zeroq6.blog.operate.manager.MetaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
@Service
public class MetaService extends BaseService<MetaDomain, Long> {

    @Autowired
    private MetaManager metaManager;

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseManager<MetaDomain, Long> getManager() {
        return metaManager;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */

}
