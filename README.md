1.导包
2.controller
    Config Client向Config Server提交REST请求后，Config Server将访问GIT服务器，并将取得的配置项hello的值返回给client( @Value("${hello}")).
3.配置文件
    这个配置文件是bootstrap.yml
    config-client:应用名称
    profile采用dev, GIT分支用master,url是config server的地址。
    此处讲的配置管理， 只有在应用启动时会读取到GIT的内容， 之后只要客户端应用不重启，GIT中文件的修改，应用无法感知， 
    即使重启Config Server也不行。
配置自动刷新：
    1.在pom.xml中添加以下依赖。spring-boot-starter-actuator是一套监控的功能，可以监控程序在运行时状态，其中就包括/refresh的功能。
    2.开启refresh机制， 需要给加载变量的类上面加载@RefreshScope注解。
    3.启动应用， 查看http://localhost:8080/hello
    4.再次修改config-client-dev.properties的内容
    5.用chome的postman发送POST请求：http://localhost:8080/actuator/refresh（
    management.endpoints.web.exposure.include=*  开启actuator的refresh功能）
    6.再次访问http://localhost:8080/hello，可见到配置已经被刷新
    
 4. 我们似乎没定义配置文件名， 那配置文件名是什么呢？ 这点又体现了约定优于配置的思路， 这里Spring Cloud约定， 
    应用的配置文件名以如下方式组成：{application}-{profile}.properties（或者{application}-{profile}.yml）。
    比如我们这个应用的配置文件就是config-client-dev.properties. 
    所以只需要在GIT的中创建配置文件spring-cloud/helloworldConfig/config-client-dev.properties就可以了  
    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties
    / { 应用名 } / { 环境名 } [ / { GIT分支 } ]
    / { 应用名 } - { 环境名 }.yml
    / { 应用名 } - { 环境名 }.properties
    / { GIT分支 } / { 应用名 } - { 环境名 }.yml
    / { GIT分支 } / { 应用名 } - { 环境名 }.properties