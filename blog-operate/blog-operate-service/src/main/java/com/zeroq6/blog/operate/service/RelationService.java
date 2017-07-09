package com.zeroq6.blog.operate.service;

import com.zeroq6.blog.common.domain.RelationDomain;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.base.BaseService;
import com.zeroq6.blog.operate.manager.RelationManager;
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
public class RelationService extends BaseService<RelationDomain, Long> {

    @Autowired
    private RelationManager relationManager;

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseManager<RelationDomain, Long> getManager() {
        return relationManager;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */

}
