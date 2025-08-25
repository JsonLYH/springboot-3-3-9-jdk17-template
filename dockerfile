FROM 10.10.10.13:8898/base-image/base-jdk17:1.0.2
COPY target/mebay_339_jdk17_template.jar mebay_339_jdk17_template.jar
ENV SW_AGENT_NAME "mebay_339_jdk17_template"
EXPOSE 8455
ENTRYPOINT ["java","-javaagent:/home/skywalking-agent/skywalking-agent/skywalking-agent.jar","-jar","/mebay_339_jdk17_template.jar","--spring.profiles.active=prod"," >mebay-339-jdk17-template.log 2>&1 &"]
