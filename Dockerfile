#阶段一 利用maven将Spring boot的工程代码打包成可执行jar包
FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8 as build-jar
MAINTAINER XiangdeDe Liu <qq313700046@icloud.com>
VOLUME /tmp
#打包的工作目录
WORKDIR /build/little_mat
# 将源代码加入到容器中
ADD / /build/little_mat
#运行maven打包命令，maven会扫描子目录下的pom.xml，识别各个模块的依赖关系，在线下载工程依赖的jar包，所需时间可能会很长
RUN mvn clean package -Dmaven.test.skip=true


#阶段二 构建jar包的运行环境，使用瘦身版的oracle SDK
FROM frolvlad/alpine-oraclejdk8:slim as build-run-env
MAINTAINER XiangdeDe Liu <qq313700046@icloud.com>
#将第一阶段生成的jar包复制到当前容器
COPY --from=build-jar /build/little_mat/controller/target/little-mat.jar app.jar
#映射端口
EXPOSE 8080
#加入容器启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]