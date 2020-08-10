FROM openjdk:11.0-jdk
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENV DATAPATH /data
VOLUME $DATAPATH
ENTRYPOINT ["java","-cp","app:app/lib/*","com.wmware.teamone.demo.DemoApplication"]

