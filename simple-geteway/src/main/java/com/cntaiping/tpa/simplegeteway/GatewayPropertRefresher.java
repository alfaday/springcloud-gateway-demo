package com.cntaiping.tpa.simplegeteway;



import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description apollo路由更改监听刷新
 */
@Component
public class GatewayPropertRefresher implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(GatewayPropertRefresher.class);

    private ApplicationContext applicationContext;

    private  GatewayProperties gatewayProperties;
    private  DynamicRouteServiceImpl dynamicRouteService;

    public GatewayPropertRefresher(DynamicRouteServiceImpl dynamicRouteService, GatewayProperties gatewayProperties) {
        this.dynamicRouteService = dynamicRouteService;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * 监听路由修改
     * @param changeEvent
     */
    @ApolloConfigChangeListener()
    public void onChange(ConfigChangeEvent changeEvent) {
        boolean gatewayPropertiesChanged = false;
        for (String changedKey : changeEvent.changedKeys()) {
            if (changedKey.startsWith("spring.cloud.gateway")) {
                gatewayPropertiesChanged = true;
                break;
            }
        }

        if (gatewayPropertiesChanged) {
            GatewayPropertRefresher(changeEvent);
        }
    }

    /**
     * 刷新路由信息
     * @param changeEvent
     */
    private void GatewayPropertRefresher(ConfigChangeEvent changeEvent) {
        logger.info("Refreshing gateway properties!");
        //更新配置
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        //更新路由
        for(RouteDefinition definition : gatewayProperties.getRoutes()){
            dynamicRouteService.update(definition);
            logger.info("info=" + JSON.toJSONString(definition));
        }
        logger.info("gateway properties refreshed!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
