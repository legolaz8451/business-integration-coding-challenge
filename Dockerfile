FROM amazoncorretto:11

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

EXPOSE 8088
EXPOSE 8082

CMD ["java", "-jar", "target/blog-1.0.0.war"]
