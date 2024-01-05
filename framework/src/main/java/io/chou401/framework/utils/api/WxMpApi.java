package io.chou401.framework.utils.api;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import io.chou401.framework.config.properties.WxMpProperties;
import io.chou401.framework.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序接口调用工具类
 * {@code @author}  chou401
 * {@code @date} 2023/11/26
 **/
@Slf4j
@Component
public class WxMpApi {

    private static WxMpProperties wxMpProperties;

    public WxMpApi(WxMpProperties wxMpProperties) {
        log.info("wxMpProperties:" + wxMpProperties);
        WxMpApi.wxMpProperties = wxMpProperties;
    }

    /**
     * 微信小程序登录
     *
     * @param code
     * @return
     * @throws Exception
     */
    public static String getOpenid(String code) throws Exception {
        log.info("微信小程序code：" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", wxMpProperties.getAppid());
        paramMap.put("secret", wxMpProperties.getSecret());
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        String result = HttpUtil.get(url, paramMap);
        log.info("微信小程序登录结果：" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = jsonObject.getString("openid");
        log.info("微信小程序登录openid：" + openid);
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        if (StringUtils.isBlank(openid)) {
            log.error("微信小程序登录失败,errcode:{},errmsg:{}", errcode, errmsg);
            throw new BusinessException("微信小程序登录失败");
        }
        return openid;
    }

}
