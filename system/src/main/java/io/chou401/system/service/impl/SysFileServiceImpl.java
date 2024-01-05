package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysFile;
import io.chou401.common.mapper.sys.SysFileMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysFileQuery;
import io.chou401.common.vo.sys.SysFileVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.system.dto.SysFileDto;
import io.chou401.system.service.SysFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统文件 服务实现类
 * {@code @author}  chou401
 * {@code @date} 2023-11-26
 */
@Slf4j
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    private final SysFileMapper sysFileMapper;

    public SysFileServiceImpl(SysFileMapper sysFileMapper) {
        this.sysFileMapper = sysFileMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysFile(SysFileDto dto) throws Exception {
        Long id = dto.getId();
        SysFile sysFile = getById(id);
        if (sysFile == null) {
            throw new BusinessException("系统文件不存在");
        }
        BeanUtils.copyProperties(dto, sysFile);
        sysFile.setUpdateTime(new Date());
        return updateById(sysFile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysFile(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysFileVo getSysFileById(Long id) throws Exception {
        return sysFileMapper.getSysFileById(id);
    }

    @Override
    public Paging<SysFileVo> getSysFilePage(SysFileQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysFileVo> list = sysFileMapper.getSysFilePage(query);
        return new Paging<>(list);
    }

}
