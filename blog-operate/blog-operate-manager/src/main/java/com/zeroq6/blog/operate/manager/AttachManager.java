package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.AttachDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.AttachDomain;
import com.zeroq6.common.utils.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class AttachManager extends BaseManager<AttachDomain, Long> {


    @Autowired
    private AttachDao attachDao;


    @Autowired
    private AttachManager attachManager;

    /**自定义开始 */

    /**自定义结束 */

    @Override
    public BaseDao<AttachDomain, Long> getDao() {
        return attachDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    public String getUploadPath(){
        try{
            return new File(getClass().getResource("/").getPath() + File.separator + "upload").getCanonicalPath();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteById(Long id){
        // 先disable数据库记录，然后删文件，异常可回滚
        disableByKey(id);
        //
        File file = null;
        AttachDomain attachDomain = selectByKey(id);
        if (null != attachDomain) {
            file = new File(attachManager.getUploadPath() + File.separator + attachDomain.getName());
        }
        if (null == attachDomain || !file.exists() || file.isDirectory()) {
            throw new RuntimeException("删除文件失败, 文件不存在");
        }
        if(!file.delete()){
            throw new RuntimeException("删除文件失败, 访问拒绝");
        }
    }

    /**自定义结束 */
}
