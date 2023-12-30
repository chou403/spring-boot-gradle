package io.chou401.framework.utils;

import io.chou401.common.enums.SystemType;
import io.chou401.common.query.DataRangeQuery;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.common.vo.login.LoginVo;
import io.chou401.framework.utils.login.LoginAppUtil;
import io.chou401.framework.utils.login.LoginUtil;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/1
 **/
public class DataRangeUtil {

    /**
     * 设置Admin登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleAdminLogin(DataRangeQuery dataRangeQuery) throws Exception {
        LoginVo loginVo = LoginUtil.getLoginVo();
        if (loginVo != null) {
            Long userId = loginVo.getUserId();
            dataRangeQuery.setLoginCommonUserId(userId);
            dataRangeQuery.setLoginAdmin(loginVo.isAdmin());
            dataRangeQuery.setLoginUserId(userId);
            dataRangeQuery.setLoginRoleId(loginVo.getRoleId());
            dataRangeQuery.setLoginRoleCode(loginVo.getRoleCode());
        }
    }

    /**
     * 设置App登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleAppLogin(DataRangeQuery dataRangeQuery) throws Exception {
        LoginAppVo loginAppVo = LoginAppUtil.getLoginVo();
        if (loginAppVo != null) {
            Long userId = loginAppVo.getUserId();
            dataRangeQuery.setLoginCommonUserId(userId);
            dataRangeQuery.setLoginAppUserId(userId);
            dataRangeQuery.setLoginAppVip(loginAppVo.isVip());
            dataRangeQuery.setLoginAppVipLevel(loginAppVo.getVipLevel());
        }
    }

    /**
     * 设置登录用户信息到查询参数中
     *
     * @param dataRangeQuery
     */
    public static void handleCommonLogin(DataRangeQuery dataRangeQuery) throws Exception {
        // 判断token是那个端
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken();
        if (SystemType.ADMIN == systemType) {
            LoginVo loginVo = LoginUtil.getLoginVo();
            if (loginVo != null) {
                dataRangeQuery.setLoginCommonUserId(loginVo.getUserId());
            }
        } else if (SystemType.APP == systemType) {
            LoginAppVo loginAppVo = LoginAppUtil.getLoginVo();
            if (loginAppVo != null) {
                dataRangeQuery.setLoginCommonUserId(loginAppVo.getUserId());
            }
        }
    }

}
