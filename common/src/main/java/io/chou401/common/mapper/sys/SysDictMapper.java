package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysDict;
import io.chou401.common.query.sys.SysDictAppQuery;
import io.chou401.common.query.sys.SysDictQuery;
import io.chou401.common.vo.sys.SysDictAppVo;
import io.chou401.common.vo.sys.SysDictVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据 Mapper 接口
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典数据详情
     *
     * @param id 字典 id
     * @return 字典信息
     */
    SysDictVo getSysDictById(Long id);

    /**
     * 字典数据分页列表
     *
     * @param query 查询参数
     * @return 查询列表
     */
    List<SysDictVo> getSysDictPage(SysDictQuery query);

    /**
     * App字典数据列表
     *
     * @param query 查询参数
     * @return 查询列表
     */
    List<SysDictAppVo> getAppSysDictList(SysDictAppQuery query);

}
