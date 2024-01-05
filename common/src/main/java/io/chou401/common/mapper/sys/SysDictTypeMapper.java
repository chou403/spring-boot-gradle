package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysDictType;
import io.chou401.common.query.sys.SysDictTypeQuery;
import io.chou401.common.vo.sys.SysDictTypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型 Mapper 接口
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 字典类型详情
     *
     * @param id 字典类型 id
     * @return 字典类型信息
     */
    SysDictTypeVo getSysDictTypeById(Long id);

    /**
     * 字典类型列表
     *
     * @param query 参数
     * @return 列表
     */
    List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query);

}
