package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysConfig;
import io.chou401.common.mapper.sys.SysConfigMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysConfigQuery;
import io.chou401.common.vo.sys.SysConfigVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.system.dto.SysConfigDto;
import io.chou401.system.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统配置 服务实现类
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-27
 */
@Slf4j
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    private final SysConfigMapper sysConfigMapper;

    public SysConfigServiceImpl(SysConfigMapper sysConfigMapper) {
        this.sysConfigMapper = sysConfigMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysConfig(SysConfigDto dto) throws Exception {
        checkConfigKeyExists(dto.getConfigKey());
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(dto, sysConfig);
        return save(sysConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysConfig(SysConfigDto dto) throws Exception {
        Long id = dto.getId();
        SysConfig sysConfig = getById(id);
        if (sysConfig == null) {
            throw new BusinessException("系统配置不存在");
        }
        BeanUtils.copyProperties(dto, sysConfig);
        sysConfig.setUpdateTime(new Date());
        return updateById(sysConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysConfig(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysConfigVo getSysConfigById(Long id) throws Exception {
        return sysConfigMapper.getSysConfigById(id);
    }

    @Override
    public Paging<SysConfigVo> getSysConfigPage(SysConfigQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysConfigVo> list = sysConfigMapper.getSysConfigPage(query);
        Paging<SysConfigVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public void checkConfigKeyExists(String configKey) throws Exception {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getConfigKey, configKey);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(configKey + "配置key已经存在");
        }
    }

}
