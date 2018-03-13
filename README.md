# Spring Cloud Config
spring-cloud-config的demo
  ▪️注册中心eureka：kyle-eureka-server
  ▪️配置中心config：kyle-config-server
  ▪️配置中心调用端：kyle-cofig-client
 大家想了解更多可以看我的博客：
 
基础架构
在动手实践了上面关于Spring Cloud Config的基础入门内容之后，在这里我们深入理解一些它是如何运作起来的。下面所示的是上一节我们构建案例的基本结构。
其中，主要包含下面几个要素。
▪️远程Git仓库：用来存储配置文件的地方，上例中我们用来存储针对应用名为didispace的多环境配置文件：didispace-{profile}.properties。
▪️Config Server：这是我们上面构建的分布式配置中心，config-server工程，在该工程中指定了所要连接的Git仓库位置以及账户、密码等连接信息。
▪️本地Git仓库：在Config Server的文件系统中，每次客户端请求获取配置信息时，Config Server从Git仓库中获取最新配置到本地，然后客户端请求获取配置信息时，Config Server从Git仓库中获取最新配置到本地，然后在本地Git仓库中读取并返回。当远程仓库无法获取时，直接将本地内容返回。
▪️Service A、Service B：具体的微服务应用，它们指定了Config Server的地址，从而实现从外部获取应用自己要用的配置信息。这些应用在启动的时候，会向Config Server请求获取配置信息来进行加载。


客户端应用从配置管理中获取配置信息遵从下面的执行流程：
  ● 应用启动时，根据bootstrap.properties中配置的应用名{application}、环境名{profile}、分支名{label}，向Config Server请求获取配置信息。
  ● Config Server根据自己维护的Git仓库信息和客户端传递过来的配置定位信息去查找配置信息。
  ● 通过git clone命令将找到的配置信息下载到Config Server的文件系统中。
  ● Config Server创建Spring的Applicationcontext实例，并从Git本地仓库中加载配置文件，最后将这些配置内容读取出来返回给客户端应用。
  ● 客户端应用在获得外部配置文件后加载到客户端的ApplicationContext实例，该配置内容的优先级高于客户端Jar包内部的配置内容，所以在Jar包中重复的内容将不再被加载。

本项目涵盖的技术点：
    ▪️加密，解密
    ▪️消息总线实现热更新
    ▪️细粒度化的安全控制
    ▪️多环境配置文件





