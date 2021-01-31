## 赤龙ERP-安装与部署

##### 1. 安装必须的组件，包括：JDK（推荐1.8及以上）、MYSQL（推荐5.7及以上）、Tomcat（推荐8.5及以上）、Redis（推荐3.0及以上）

##### 2. 构建表结构，找到目录/build/sql，执行所有SQL文件；初始化数据，找到目录/build/data，执行SQL文件
> 本数据库使用字符集utf8mb4，请自行检查是否兼容

##### 3. 配置ERP应用，进入子项目erp-springboot，修改相关配置文件

###### （1）日志文件配置，找到src/main/resources/conf/log/logback-spring.xml，根据你使用的环境修改日志文件路径：

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
        domain=www.redragon-erp.com
        #orgCode为公司的组织简写
        orgCode=redragon-erp

###### （4）修改第三方组件的环境（下面第5、6步前提），找到src/main/resources/redragon.properties，修改如下内容：

        #develop为运行环境，test为测试环境，produce为生产环境，如果文件为空则为默认环境
        system_environment = develop

###### （5）修改Redis配置，找到src/main/resources/redis.properties，修改相对应环境的配置

        redis.ip=127.0.0.1
        redis.port=6379
        redis.password=redis

> 注意：为了安全性考虑，上述redis密码为必填项。redis服务端如何设置密码请自行查询

###### （6）修改Shiro配置，找到src/main/resources/shiro.properties，修改相对应环境的配置

        #替换localhost即可
        casUrl=https://localhost/CasServer
        requestUrl=https://localhost/erp/casclient
        cookieDomain=
        successUrl=https://localhost/erp/web/main
        unauthUrl=https://localhost/erp/unauth

> 注意：本项目默认采用HTTPS协议，考虑到访问的安全因素，生产环境建议使用SSL，开发环境可修改上文协议

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

> 特殊说明：编译中出现问题的小伙伴可以直接使用master分支的Releases（里面包含了打好的war包）

**三种编译部署方式如下：**

`由于三种方式的POM配置不兼容，请自行修改，修改方式属MAVEN使用范畴，本文不做说明`

- springboot方式：找到erp-springboot子项目下的main启动类，此种方式简单但不方便复杂调试

> 注意：需要调整pom的packaging和plugin，POM相关配置请自行搜索解决

- maven方式：在erp-parent父项目下执行install命令，打包后部署到Tomcat

> 注意：需要调整pom的packaging和plugin，POM相关配置请自行搜索解决

- IDE方式部署到本地Tomcat，直接部署erp-webapp子项目即可，此种方式适合本地开发和调试

##### 6. 访问应用，赤龙ERP启动后，访问ERP应用根路径，例如：https://localhost/erp

##### 7. 赤龙ERP初始化，通过用户redragon登录ERP系统，并访问首页，根据页面中的提示进行ERP系统初始化

## 疑问与解决

`大家在赤龙ERP构建时如果出现问题，请移步码云的issue上提问，issue的问题我们会优先安排解决！`

issue地址：https://gitee.com/redragon/redragon-erp/issues

## 交流与分享

> 如仍存在疑问，可直接与开发者交流

![赤龙ERP开发者](http://www.redragon-erp.com/images/redragon.png "赤龙ERP开发者")

