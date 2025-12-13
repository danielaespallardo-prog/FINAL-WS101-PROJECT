How to run Artisan Spring Boot server locally

Prerequisites
- Java 17 or newer installed
- Internet connection to download dependencies on first build (Maven)

Run commands (Windows PowerShell)
- Build and run with Maven wrapper:
  - `./mvnw.cmd spring-boot:run`
- Or build and run jar:
  - `./mvnw.cmd -DskipTests package`
  - `java -jar target/artisan-0.0.1-SNAPSHOT.jar`

Verify
- Static UI: http://localhost:8080/
- API: http://localhost:8080/api/products

Notes
- If backend is not running, the static UI will fall back to pre-packaged sample products in script.js.
- Product images are served from `/images/*` under the static assets directory.