package com.topcom.cms.utils;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.domain.OperationLog;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.OperationLogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

/**
 * 操作日志AOP拦截工具类
 *
 * @author lism
 */
@Aspect
@Component
public class OperationLogService {
    /**
     * Logger for this class
     */

    @Resource
    OperationLogManager operationLogManager;

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.topcom.cms..service.*.save*(..))")
    private void saveMethod() {
    }


    @Pointcut("execution(* com.topcom.cms..service.*.delete*(..))")
    private void deleteMethod() {
    }

    @Pointcut("saveMethod() || deleteMethod()")
    private void anyMethod() {
    }

    @AfterReturning("anyMethod()")
    public void doBasicProfiling(JoinPoint pjp) throws Throwable {
        HttpServletRequest request = SubjectUtil.getRequest();
        Object[] args = pjp.getArgs();
        OperationLog ol = new OperationLog();
        Object obj = args[0];
        // 拦截的实体类 Object target = pjp.getTarget();
        Class<?> entityClazz = (Class<?>) ((ParameterizedType) pjp.getTarget().getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        ol.setEntity(entityClazz.getName());
        if (obj instanceof Long) {
            ol.setOperation(OperationLog.Operation.DELETE);
        } else if(obj instanceof BaseEntityModel){

            BaseEntityModel objModel = (BaseEntityModel) obj;
            if (objModel.getId() == null || objModel.getId() == 0) {
                ol.setOperation(OperationLog.Operation.CREATE);
            } else {
                ol.setOperation(OperationLog.Operation.MODIFY);
            }
            ol.setEntity(objModel.getEntityName());
        }else{
            return;
        }
        // 拦截的方法名称
        String methodName = pjp.getSignature().getName();
        /**
         * request是null，说明是系统调用save，否则是用户调用
         */
        if(request==null){
            ol.setUsername("系统操作");
        }else{
            User user = SubjectUtil.getCurrentUser();
            ol.setHost(request.getRemoteHost());
            if (user != null) {
                ol.setUserId(user.getId());
                ol.setUsername(user.getUsername());
            }
        }
        ol.setMethod(methodName);

        /**
         * 不使用save方法，更改了操作日志的保存方法，否则会造成无限循环调用
         */
        //TODO:写一个注解，在方法名上标注不被拦截的方法
        operationLogManager.persist(ol);
    }
}