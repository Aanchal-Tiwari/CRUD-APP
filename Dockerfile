FROM docker.atl-paas.net/sox/micros-java-11:v1.1.6

# these labels help Security track down any vulnerabilities we may have received
# when we generated our service with Instant Micros
LABEL "instant-micros-template-name"="spring-boot"
LABEL "instant-micros-template-version"="c376b29f9d74647a7503bb741cf8e6133147618b"

WORKDIR /opt/service

COPY ./target/democrud-bookapplication-*.jar /opt/service/service.jar

