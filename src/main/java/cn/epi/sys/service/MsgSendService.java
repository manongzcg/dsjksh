package cn.epi.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.sys.dao.MsgSendDao;
import cn.epi.sys.entity.MsgSend;

/**
* 消息发送表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class MsgSendService extends CrudService<MsgSendDao, MsgSend> {

}
