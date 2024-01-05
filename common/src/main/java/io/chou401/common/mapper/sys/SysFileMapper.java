package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysFile;
import io.chou401.common.query.sys.SysFileQuery;
import io.chou401.common.vo.sys.SysFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统文件 Mapper 接口
 * {@code @author}  chou401
 * {@code @date} 2023-11-26
 */
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

    /**
     * 系统文件详情
     *
     * @param id id
     * @return 详情
     */
    SysFileVo getSysFileById(Long id);

    /**
     * 系统文件分页列表
     *
     * @param query 参数
     * @return 列表
     */
    List<SysFileVo> getSysFilePage(SysFileQuery query);

}
