package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysFile;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysFileQuery;
import io.chou401.common.vo.sys.SysFileVo;
import io.chou401.system.dto.SysFileDto;

/**
 * 系统文件 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-26
 */
public interface SysFileService extends IService<SysFile> {

    /**
     * 修改系统文件
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysFile(SysFileDto dto) throws Exception;

    /**
     * 删除系统文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysFile(Long id) throws Exception;

    /**
     * 系统文件详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysFileVo getSysFileById(Long id) throws Exception;

    /**
     * 系统文件分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysFileVo> getSysFilePage(SysFileQuery query) throws Exception;

}
