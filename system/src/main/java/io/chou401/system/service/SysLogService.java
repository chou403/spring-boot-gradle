package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysLog;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysLogQuery;
import io.chou401.common.vo.sys.SysLogVo;

/**
 * 系统日志 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-02-16
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysLogVo getSysLogById(Long id) throws Exception;

    /**
     * 系统日志分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysLogVo> getSysLogPage(SysLogQuery query) throws Exception;

}
