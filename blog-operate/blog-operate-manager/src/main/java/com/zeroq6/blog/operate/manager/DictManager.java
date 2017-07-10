package com.zeroq6.blog.operate.manager;

import com.zeroq6.blog.common.dao.DictDao;
import com.zeroq6.blog.common.base.BaseDao;
import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.domain.DictDomain;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class DictManager extends BaseManager<DictDomain, Long> implements InitializingBean{

    @Autowired
    private DictDao dictDao;

    /**自定义开始 */

    private List<DictDomain> dictDomainCacheList;

    /**自定义结束 */

    @Override
    public BaseDao<DictDomain, Long> getDao() {
        return dictDao;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    public void flushDictList(){
        try{
            dictDomainCacheList = selectList(null);
            logger.info("刷新字典成功");
        }catch (Exception e){
            logger.error("刷新字典失败", e);
        }
    }

    public List<DictDomain> getDictByType(int type){
        return getDictByType(type, null);
    }

    public List<DictDomain> getDictByType(List<Integer> typeList){
        List<DictDomain> list = new ArrayList<DictDomain>();
        for(Integer type : typeList){
            list.addAll(getDictByType(type));
        }
        return list;
    }

    public List<DictDomain> getDictByType(int type, List<String> dictKeyList){
        List<DictDomain> result = new ArrayList<DictDomain>();
        for(DictDomain dictDomain : dictDomainCacheList){
            if(dictDomain.getDictType() == type){
                if(null == dictKeyList){
                    result.add(dictDomain);
                }else{
                    if(dictKeyList.contains(dictDomain.getDictKey())){
                        result.add(dictDomain);
                    }
                }
            }
        }
        return result;
    }

    public DictDomain getDictByTypeAndKey(int type, String key){
        List<DictDomain> result = new ArrayList<DictDomain>();
        for(DictDomain dictDomain : dictDomainCacheList){
            if(dictDomain.getDictType() == type && dictDomain.getDictKey().equals(key)){
                result.add(dictDomain);
            }
        }
        if(result.size() != 1){
            throw new RuntimeException("字典表, type: " + type + ", key: " + key + "在字典中记录条数不为1");
        }
        return result.get(0);
    }

    public List<String> getValueListByType(int type){
        List<String> valueList = new ArrayList<String>();
        List<DictDomain> dictDomainList = getDictByType(type);
        for(DictDomain dictDomain : dictDomainList){
            valueList.add(dictDomain.getDictValue());
        }
        return valueList;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        flushDictList();
    }


    /**自定义结束 */
}
