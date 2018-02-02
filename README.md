# Spring Cloud Config
spring-cloud-config的demo
  ▪️注册中心eureka：kyle-eureka-server
  ▪️配置中心config：kyle-config-server
  ▪️配置中心调用端：kyle-cofig-client
 配置中心服务端详解：
 
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
Config Server巧妙地通过git clone将配置信息存于本地，起到缓存的作用，即使当Git服务端无法访问的时候，依赖可以取Config Server中的缓存内容进行使用。
Git配置仓库
 在Spring Cloud Config的服务端，对于配置仓库的默认实现采用了Git。Git非常适用于存储配置内容，它可以非常方便地使用各种第三方工具来对其进行管理更新和版本化，同时Git仓库仓库的Hook功能还可以帮助我们实时监控配置内容的修改。其中，Git自身的版本控制功能正是其他一些配置中心所欠缺的，通过Git进行存储意味着，一个应用的不同部署实例可以从Spring Cloud Config的服务端获取不同的版本配置，从而支持一些特殊的应用场景。
由于Spring Cloud Config中默认使用Git，所以对于Git的配置非常简单，只需在Config Server的application.properties中设置spring.cloud.config.server.git.uri属性，为其指定Git仓库的网络地址和账户信息即可，比如在快速入门一节中的例子：

如果我们将该值通过file://前缀来设置为一个文件地址(在Windows系统中，需要使用file:///来定位文件内容)，那么它将以本地仓库的方式运行，这样我们就可以脱离Git服务端来进行调试与开发，比如：

其中，${user.home}代表当前用户的所属目录。file://配置的本地文件系统方式虽然对于本地开发调试时使用非常方便，但是该房事也仅用于开发与测试，在生产环境中，请务必大家自己的Git仓库来存储配置资源。
占位符配置URI
{application}、{profile}、{label}这些占位符除了用于标识配置文件的规则之外，还可以用于Config Server中对Git仓库地址的URI配置。比如，我们可以通过{application}占位符来实现一个应用对应一个Git仓库目录的配置效果，具体配置实现如下：

其中，{application}代表了应用名，所以当客户端应用向Config Server发起获取配置的请求时，Config Server会根据客户端spring.application.name信息来填充{application}占位符以定位配置资源的存储位置，从而实现根据微服务应用的属性动态获取不同的位置的配置。另外，这些占位符中，{label}参数较为特别，如果Git的分支和标签名包含"/"，那么{label}参数在HTTP的URL中应该使用"(_)"来替代，以避免改变了URI含义，指向到其他的URI资源。
当我们使用Git作为配置中心来存储各个微服务应用配置文件的时候，该功能会变得非常有用，通过在URI中使用占位符可以帮助我们规划和实现通用的仓库配置。例如，我们可以对微服务应用做如下规划。
▪️代码库：使用服务名作为Git仓库名称，比如会员服务的代码库https://github.com/erghjmncq6643981/demo/respo/member-service。
▪️配置库：使用服务名加上-config后缀作为Git仓库名称，比如上面会员服务对应的配置库地址位置https://github.com/erghjmncq6643981/demo/respo/member-service-config。
这时，我们就可以通过使用spring.cloud.config.server.git.rui=https://github.com/erghjmncq6643981/demo/respo/{application}-config配置，来同时匹配多个不同服务的配置仓库。
配置多个仓库
Config Server除了可以通过application和profile模式来匹配配置仓库之外，还支持通过带有通配符的表达式来匹配，以实现更为复杂的配置需求。并且当我们有多个匹配规则的时候，还可以用逗号来分割多个{application}/{profile}配置规则，比如：

上述配置内容通过spring.cloud.config.server.git.uri属性，指定了一个默认的仓库位置，当使用{application}/{profile}模式未能匹配到合适的仓库时，就将在该默认仓库位置下获取配置信息。除此之外，还配置了三个仓库，分别是dev、test、prod。其中dev仓库匹配dev/*的模式，所以无论profile是什么，它都能匹配application名称为dev的应用。并且我们可以注意到，它存储的配置文件位置还采用了Config Server的本地文件系统中的内容。对于此配置，我们可以通过访问http://localhost8888/dev/profile的请求来验证到该仓库的配置内容，其中profile可以为任意值。而test和prod仓库均使用了Git仓库的存储，并且test仓库未配置匹配规则，所以它只匹配application名为test的应用；prod仓库则需要匹配application为prod并且profile为pp开头，或者application为online并且profile为oo开头的应用和环境。
当配置多个仓库的时候，Config Server在启动时会直接克隆第一个仓库的配置库，其他的配置库只有在请求时才会克隆到本地，所以对于仓库排列可以根据配置内容的重要程度有所区分。另外，如果表达式是以通配符开始的，那么需要使用引号将配置内容引起来。
子目录存储
除了支持占位符配置、多仓库配置之外，Config Server还可以将配置文件定位到Git仓库等子目录中。有心的读者，或许还能记得在快速入门中，我们除了配置spring.cloud.config.server.git.uri属性之外，还配置了另外一个参数：spring.cloud.config.server.git.searchPaths，通过该参数我们实现了在https://github.com/erghjmncq6643981/didispace/SpringCloud-Learning/仓库的spring_cloud_in_action/config-repo的子目录下实现配置的存储。
对于spring.cloud.config.server.git.searchPaths参数的配置也支持使用{application}、{profile}和{label}占位符，比如

通过上面的配置，我们可以实现在https://github.com/erghjmncq6643981/didispace/SpringCloud-Learning/仓库中，一个应用一个目录的效果。
访问权限
Config Server在访问Git仓库的时候，若采用HTTP的方式进行认证，那么我们需要增加username和password属性来配置账户(快速入门中也是如此实现)，比如：

若不使用HTTP的认证方式，我们也可采用SSH的方式，通过生成Key并在Git仓库中进行配置匹配以实现访问。
svn配置仓库
省略...，需要的话看书去。
健康监测
省略一些内容...
根据默认配置规则，我们可以直接在Git仓库中创建一个名为app-config的配置库，让健康检测器能够访问到它。另外，也可以配置一个实际存在的仓库来进行连通检测，比如下面的配置，它实现了通过连接check-repo-config仓库来进行健康监测：

由于健康检测的repositories是个Map对象，所以实际使用时我们可以配置多个。而每个配置中包含了与定位仓库地址时类似的三个元素。
▪️name：应用名
▪️label：分支名
▪️profiles：环境名
如果我们不想使用该健康检测器，也可以通过使用spring.cloud.config.server.health.enabled=false参数设置来关闭它。
属性覆盖
Config Server还有一个“属性覆盖”的特性，它可以让开发人员为所有的应用提供配置属性，只需要通过spring.cloud.config.server.overrides属性来设置键值对的参数，这些参数会以Map的方式加载到客户端的配置中。比如：

通过该属性配置的参数，不会被Spring Cloud的客户端修改，并且Spring Cloud客户端从Config Server中获取配置信息时，都会取得这些配置信息时，都会取得这些配置信息。利用该特性可以方便地为Spring Cloud应用配置一些公共属性或是默认属性。当然，这些属性并非强制的，我们可以通过改变客户端中更高优先级的配置方式（比如，配置环境变量或是系统属性），来选择是否使用Config server提供的默认值。
安全保护
由于配置中心存储的内容比较敏感，做一定的安全处理是必需的。为配置中心实现安全保护的方式有很多，比如物理网络限制、OAuth2授权等。不过，由于我们的微服务应用和配置中心都构建于Spring Boot基础上，所以与Spring Security结合使用会更加方便。
我们只需要在配置中心的pom.xml中加入spring-boot-starter-security依赖，不需要做任何其他改动就能实现对配置中心访问的安全保护。

默认情况下，我们可以获得一个名为user的用户，并且在配置中心启动的时候，在日志中打印该用户的随机密码，具体如下：

大多数情况下，我们并不会使用随机生成密码的机制。我们可以在配置文件中指定用户和密码，比如：

由于我们已经为config-server设置了安全保护，如果这时候连接到配置中心的客户端中没有设置对应的安全信息，在获取配置信息时会返回401错误。所以，需要通过配置的方式在客户端中加入安全信息来通过校验，比如：


加密解密
在微服务架构中，我们通常会采用DevOps的组织方式来降低因团队间沟通造成的巨大成本，以加速微服务应用的交付能力。这就使得原本由运维团队控制的线上信息将交由微服务所属组织的成员自行维护，其中将会包括大量的敏感信息，比如数据库的账户与密码等。显然，如果我们直接将敏感信息以明文的方式存储于微服务应用的配置文件中是非常危险的。针对这个问题，Spring Cloud Config提供了对属性进行加密解密的功能，以保护配置文件中的信息安全。比如下面的例子：

在Spring Cloud Config中通过在属性值前使用{cipher}前缀来标注该内容是一个加密值，当微服务客户端加载配置时，配置中心会自动为带有{cipher}前缀的值进行解密。通过该机制的实线，运维团队就可以放心地将线上信息的加密资源给到微服务团队，而不用担心这些敏感信息遭到泄露了。下面我们来具体介绍如何在配置中心使用该项功能。
使用前提
在使用Spring Cloud Config的加密解密功能时，有一个必要的前提需要我们注意。为了启用该功能，我们需要在配置中心的运行环境中安装不限长度的JCE版本(Unlimited Strength Java Cryptography Extension)。虽然，JCE功能在JRE中自带，但是默认使用的是长度限制的版本。我们可以从Oracle的官方网站下载到它，它是一个压缩包，解压后可以看到下面三个文件：

我们需要将local_policy.jar和US_export_policy.jar两个文件复制到$JAVA_HOME/jre/security目录下，覆盖原来的默认内容。到这里，加密解密的准备工作就完成了。


相关端点
在完成了JCE的安装后，可以尝试启动配置中心。在控制台中，将会输出一些配置中心持有的端点，主要包括如下几个。
▪️/encrypt/status：查看加密功能状态的端点。
▪️/key：查看密钥的端点。
▪️/encrypt：对请求的body内容进行加密的端点。
▪️/decrypt：对请求的body内容进行解密的端点。
可以尝试通过GET请求访问/encrypt/status端点，我们将得到如下内容；

该返回信息说明当前配置中心的加密功能还不能使用，因为没有为加密服务配置对应的密钥。
重置密钥
我们可以通过encrpt.key属性在配置文件中直接指定密钥信息（对称性密钥），比如：

加入上述配置信息后，重启配置中心，再访问/encrpt/status端点，我们将得到如下内容：

此时，我们配置中心的加密解密功能就已经可以使用了，不妨尝试访问一下/encrypt和/decrypt端点来使用加密和解密的功能。注意，这两个端点都说POST请求，加密和解密信息需要通过请求体来发送。比如，以curl命令为例，我们可以通过下面的方式调用加密与解密端点：

这里，我们通过配置encrypt.key参数来指定密钥的实现方式采用了对称性加密。这种方式实现起来比较简单，只需要配置一个参数即可。另外，我们也可以使用环境变量ENCRYPT_KEY来进行配置，让密钥信息外部化存储。





