package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysDictType;
import io.chou401.common.query.sys.SysDictTypeQuery;
import io.chou401.common.vo.sys.SysDictTypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型 Mapper 接口
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 字典类型详情
     *
     * @param id
     * @return
     */
    SysDictTypeVo getSysDictTypeById(Long id);

    /**
     * 字典类型列表
     *
     * @param query
     * @return
     */
    List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query);

}
