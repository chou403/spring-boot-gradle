package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysLog;
import io.chou401.common.query.sys.SysLogQuery;
import io.chou401.common.vo.sys.SysLogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统日志 Mapper 接口
 * {@code @author}  chou401
 * {@code @date} 2023-02-16
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id id
     * @return 详情
     */
    SysLogVo getSysLogById(Long id);

    /**
     * 系统日志分页列表
     *
     * @param query 参数
     * @return 列表
     */
    List<SysLogVo> getSysLogPage(SysLogQuery query);

}
