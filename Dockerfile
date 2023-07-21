FROM clojure:openjdk-19-lein-2.9.8-bullseye AS builder
WORKDIR /root
COPY . .
RUN lein uberjar

FROM openjdk:19-slim-bullseye AS runtime
COPY --from=builder /root/target/uberjar/redbreast.jar /redbreast.jar
ARG PORT=8090
ARG TELEGRAM_TOKEN=""
EXPOSE ${PORT}
ENV MALLOC_ARENA_MAX=2
ENTRYPOINT ["java", "-server", "-cp", "/redbreast.jar", "clojure.main", "-m", "redbreast.core"]
