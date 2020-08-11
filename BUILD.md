## 赤龙ERP-安装与部署

##### 1. 安装必须的组件，包括：JDK（推荐1.8及以上）、MYSQL（推荐5.7及以上）、Tomcat（推荐8.5及以上）、Redis（推荐3.0及以上）

##### 2. 构建表结构，找到目录/build/sql，执行所有SQL文件，

##### 3. 配置ERP应用，进入子项目erp-springboot，修改相关配置文件

###### （1）日志文件配置，找到src/main/resources/conf/log/logback-spring.xml，修改日志文件路径：

        <!-- dev环境文件路径 -->
        <property name="DEV_FILE_PATH" value="d:/logs/test.log" />
        <!-- test环境文件路径 -->
        <property name="TEST_FILE_PATH" value="d:/logs/test.log" />
        <!-- pro环境文件路径 -->
        <property name="PRO_FILE_PATH" value="/opt/test/log" />

###### （2）修改springboot主配置文件，找到目录src/main/resources

    修改application.properties文件的spring.profiles.active=dev
    //dev为运行环境，可改为dev，test，prod
    修改application-dev-dataBase.properties文件中的数据源配置信息，包括：IP、端口、数据库、用户名、密码

###### （3）配置全局变量，找到src/main/resources/global.properties，修改如下内容：

        #domain为自己系统的域名
        domain=www.erp.com
        #orgCode为公司的组织简写
        orgCode=erp.com

###### （4）修改第三方组件的环境（下面第5、6步前提），找到src/main/resources/redragon.properties，修改如下内容：

        #develop为运行环境，test为测试环境，produce为生产环境，如果文件为空则为默认环境
        system_environment = develop

###### （5）修改Redis配置，找到src/main/resources/redis.properties，修改相对应环境的配置

        redis.ip=127.0.0.1
        redis.port=6379
        redis.password=redis

###### （6）修改Shiro配置，找到src/main/resources/shiro.properties，修改相对应环境的配置

        #替换localhost即可
        casUrl=https://localhost/CasServer
        requestUrl=https://localhost/erp/casclient
        cookieDomain=
        successUrl=https://localhost/erp/web/main
        unauthUrl=https://localhost/erp/unauth

##### 4. 修改单点登录配置，进入CasServer项目

###### 找到文件src/main/webapp/WEB-INF/deployerConfigContext.xml，定位到如下位置，修改数据源相关内容：

        <!-- dataSource -->
        <!-- 修改IP、端口、数据库、用户名、密码 -->  
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
            <property name="driverClassName">
                <value>com.mysql.cj.jdbc.Driver</value>
            </property>
            <property name="url">
                <value>jdbc:mysql://127.0.0.1:3306/erp?useUnicode=true&amp;characterEncoding=utf8&amp;serverTimezone=GMT%2B8</value>
            </property>
            <property name="username">
                <value>root</value>
            </property>
            <property name="password">
                <value>root1234</value>
            </property>
        </bean>

##### 5. 编译项目，包括：CasServer、erp-parent，并部署到Tomcat即可正常启动

## 交流与分享

> 如对上述配置有任何疑问，可直接与开发者交流

![赤龙ERP交流群](https://raw.githubusercontent.com/redragon1985/redragon-erp/master/docs/images/redragon.png "赤龙ERP交流群")

