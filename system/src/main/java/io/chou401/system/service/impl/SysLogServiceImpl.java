package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysLog;
import io.chou401.common.mapper.sys.SysLogMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysLogQuery;
import io.chou401.common.vo.sys.SysLogVo;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日志 服务实现类
 * {@code @author}  chou401
 * {@code @date} 2023-02-16
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    private final SysLogMapper sysLogMapper;

    public SysLogServiceImpl(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    public SysLogVo getSysLogById(Long id) throws Exception {
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogVo> getSysLogPage(SysLogQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysLogVo> list = sysLogMapper.getSysLogPage(query);
        return new Paging<>(list);
    }

}
