package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysConfig;
import io.chou401.common.query.sys.SysConfigQuery;
import io.chou401.common.vo.sys.SysConfigVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统配置 Mapper 接口
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-27
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    /**
     * 系统配置详情
     *
     * @param id
     * @return
     */
    SysConfigVo getSysConfigById(Long id);

    /**
     * 系统配置分页列表
     *
     * @param query
     * @return
     */
    List<SysConfigVo> getSysConfigPage(SysConfigQuery query);

}
