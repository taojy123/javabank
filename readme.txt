1、项目结构
	annotation 自定义注解 主要用于controller层获取当前登录对象 已入参方式注入
	common 一些公共的类
	config 配置类 - xml bean
	constant 枚举类
	entity 实体类-对应数据模型
	excetion 自定义异常类
	handler 拦截器 - 目前只有异常拦截器
	mapper 也就是dao
	security 对应shiro
	service service层
	utils 工具包
	viewresolver json视图解析类
	web controller层
		vo 与前端交互时所用到的封装对象 
		controller 
		editor 类转换器 如时间类型 只能接收string 通过类转换器可以转换并绑定到date类型上 固可以在controller中用date接收擦书
	application 启动类
	application.properties 主要用于boot集成的相关框架的配置中心
	logback 日志配置
	
2、资源结构
	mybatis  xmls
	static 静态文件 如css image
	templates htmls
	
3、如果有图片上传的功能，需要配置一个图片服务器，下载一个tomcat版本随便，然后在webapps下创建images文件夹，
	在application.properties中配置image.url 例：D:/apache/apache-tomcat-8.5.37/webapps，然后启动tomcat 端口默认8080即可 项目端口不要冲突，项目端口默认是8081
	
	
环境信息
	jdk8   下载地址https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html  教程https://jingyan.baidu.com/article/6dad5075d1dc40a123e36ea3.html
	apache tomcat 8 下载地址https://tomcat.apache.org/download-80.cgi
	数据库mysql 5.6+ 教程https://jingyan.baidu.com/article/eb9f7b6da17ccc869364e8d9.html 
部署-
	通过maven 进行项目打包，打包后 项目下的 target 文件下 会出现war包
	打包的时候注意配置自己的数据源信息，数据库ip 账号密码等信息
	然后将war包放在tomcat  webapps下 在启动tomcat 如果tomcat不会 请百度搜索
	
	注意点：配置数据源 配置成自己本地的！！！！
	
		
	