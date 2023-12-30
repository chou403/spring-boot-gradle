package io.chou401.common.constant;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/7/3
 **/
public interface AopConstant {

    /**
     * 项目下所有controller切点
     */
    String PROJECT_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*.*(..))";

    /**
     * APP切点
     */
    String APP_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*AppController*.*(..))";

    /**
     * 管理后台切点
     */
    String ADMIN_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*.*(..)) && !" + APP_POINTCUT;

}
