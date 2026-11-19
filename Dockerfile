FROM eclipse-temurin:17-jre

WORKDIR /app

# Run as a non-root user
RUN useradd --system --no-create-home appuser

COPY target/quote-api-1.0.0.jar app.jar

ENV PORT=5000
EXPOSE 5000

USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]
