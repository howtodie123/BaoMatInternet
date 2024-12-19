# Stage 1: Build ứng dụng với Maven
FROM maven:3.9.8-eclipse-temurin-17 AS builder

# Tạo thư mục làm việc
WORKDIR /app

# Sao chép file cấu hình Maven và tải trước dependencies
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:resolve

# Sao chép toàn bộ mã nguồn
COPY src ./src

# Build ứng dụng
RUN ./mvnw clean package -DskipTests

# Stage 2: Chạy ứng dụng với OpenJDK
FROM openjdk:17-jdk-slim

# Tạo thư mục làm việc
WORKDIR /app

# Sao chép file JAR từ giai đoạn build
COPY --from=builder /app/target/*.jar app.jar

# Expose cổng ứng dụng Spring Boot
EXPOSE 8080

# Lệnh khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
