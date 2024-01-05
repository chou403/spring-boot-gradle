package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysDictType;
import io.chou401.common.mapper.sys.SysDictTypeMapper;
import io.chou401.common.query.sys.SysDictTypeQuery;
import io.chou401.common.vo.sys.SysDictTypeVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.system.dto.SysDictTypeDto;
import io.chou401.system.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 字典类型 服务实现类
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    public SysDictTypeServiceImpl(SysDictTypeMapper sysDictTypeMapper) {
        this.sysDictTypeMapper = sysDictTypeMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysDictType(SysDictTypeDto dto) throws Exception {
        checkCodeExists(dto.getCode());
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(dto, sysDictType);
        return save(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDictType(SysDictTypeDto dto) throws Exception {
        Long id = dto.getId();
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("字典类型不存在");
        }
        sysDictType.setIsSystem(dto.getIsSystem());
        sysDictType.setName(dto.getName());
        sysDictType.setRemark(dto.getRemark());
        sysDictType.setUpdateTime(new Date());
        return updateById(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDictType(Long id) throws Exception {
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("系统字典类型不存在");
        }
        Boolean isSystem = sysDictType.getIsSystem();
        if (isSystem) {
            throw new BusinessException("系统类型不能删除");
        }
        return removeById(id);
    }

    @Override
    public SysDictTypeVo getSysDictTypeById(Long id) throws Exception {
        return sysDictTypeMapper.getSysDictTypeById(id);
    }

    @Override
    public List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query) throws Exception {
        return sysDictTypeMapper.getSysDictTypeList(query);
    }

    @Override
    public void checkCodeExists(String code) throws Exception {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictType::getCode, code);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(code + "类型编码已经存在");
        }
    }

}
