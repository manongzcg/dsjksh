package cn.epi.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.sys.dao.MsgReceiveDao;
import cn.epi.sys.entity.MsgReceive;

import java.util.List;

/**
* 消息接收表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class MsgReceiveService extends CrudService<MsgReceiveDao, MsgReceive> {

    /**
     * 批量添加记录
     * @param msgReceives
     * @return
     */
    @Transactional(readOnly = false)
    public int saveBatch(List<MsgReceive> msgReceives){
        return dao.saveBatch(msgReceives);
    }

    /**
     * 根据消息ID删除记录
     * @param msgId
     * @return
     */
    public int deleteByMsgId(String msgId){
        return dao.deleteByMsgId(msgId);
    }

}
