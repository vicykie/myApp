package org.vicykie.framework.myApp.common.prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by vicykie on 2016/6/13.
 * 服务器相关初始化数据
 */
@Component
public class ServerConfiguration {

    @Autowired
    public ServerConfiguration(Environment environment) {
    }
}
