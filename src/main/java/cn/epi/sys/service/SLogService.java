package cn.epi.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.Page;
import cn.epi.common.service.CrudService;
import cn.epi.sys.dao.SLogDao;
import cn.epi.sys.entity.SLog;

/**
 * 日志记录 Service层
 * 
 * @author MyCode
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SLogService extends CrudService<SLogDao, SLog> {
	
	
	
	/**
     * 查询分页数据
     *
     * @param page
     * @return
     */
    public List<SLog> findPage1(Page<SLog> page) {
        page.setTotal(dao.count1(page));
        return dao.findPage1(page);
    }
    
	/**
	 * 批量添加记录
	 * 
	 * @param logs
	 * @return
	 */
	@Transactional(readOnly = false)
	public int saveBatch(List<SLog> logs) {
		return dao.saveBatch(logs);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteAll(String[] ids){
		return dao.deleteAll(ids);
	}

}
