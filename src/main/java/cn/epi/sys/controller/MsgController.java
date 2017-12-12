package cn.epi.sys.controller;

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.common.ResultVo;
import cn.epi.common.utils.JStringUtils;
import cn.epi.common.utils.UserUtils;
import cn.epi.sys.entity.MsgReceive;
import cn.epi.sys.service.MsgReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 邮件/通知 首页接口
 * @author iutils.cn
 * @version 1.0
 */
@RestController
@RequestMapping("${adminPath}/sys/msg")
public class MsgController extends BaseController{

    @Autowired
    private MsgReceiveService msgReceiveService;

    @ModelAttribute
    public MsgReceive get(@RequestParam(required = false) String id) {
        MsgReceive entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = msgReceiveService.get(id);
        }
        if (entity == null) {
            entity = new MsgReceive();
        }
        return entity;
    }

    /**
     * 获取站内信列表
     * @return
     */
    @RequestMapping(value = "/getMailPage", method = RequestMethod.GET)
    public @ResponseBody
    ResultVo getMailPage(MsgReceive msgReceive, Page<MsgReceive> page){
        ResultVo resultVo = null;
        try {
            msgReceive.setUser(UserUtils.getLoginUser());
            page.setEntity(msgReceive);
            page.setOrderBy("a.update_date desc,c.level desc");
            page.setList(msgReceiveService.findPage(page));
            resultVo = new ResultVo(ResultVo.SUCCESS,"1","调用成功",page);
        }catch (Exception e){
            logger.error("获取站内信列表失败",e);
            resultVo = new ResultVo(ResultVo.FAILURE,"-1","调用失败",null);
        }
        return resultVo;
    }

    /**
     * 获取站内通知列表
     * @return
     */
    @RequestMapping(value = "/getNoticePage", method = RequestMethod.GET)
    public @ResponseBody
    ResultVo getNoticePage(MsgReceive msgReceive, Page<MsgReceive> page){
        ResultVo resultVo = null;
        try {
            msgReceive.setUser(UserUtils.getLoginUser());
            page.setEntity(msgReceive);
            page.setOrderBy("a.update_date desc,c.level desc");
            page.setList(msgReceiveService.findPage(page));
            resultVo = new ResultVo(ResultVo.SUCCESS,"1","调用成功",page);
        }catch (Exception e){
            logger.error("获取站内通知列表失败",e);
            resultVo = new ResultVo(ResultVo.FAILURE,"-1","调用失败",null);
        }
        return resultVo;
    }

}
