# rest-api-openapi-isansds
Этот проект реализует REST-приложение для ведения реестра техники (телевизоры, пылесосы, холодильники, смартфоны и ПК) с привязкой моделей к технике. Для каждого вида техники предусмотрены свои атрибуты. Проект использует Spring Boot и PostgreSQL для хранения данных. Также реализована документация через OpenAPI v3, доступная на Swagger.

# Требования
Перед тем, как запустить приложение, убедитесь, что на вашей машине установлены следующие компоненты:
* Java 8 или выше
* Maven для управления зависимостями и сборки проекта
* PostgreSQL для работы с базой данных
* Spring Boot с зависимостями:
  * Spring Boot Starter
  * Spring Web
  * Spring JPA
  * Spring Hibernate
  * springdoc-openapi для документации

# Установка
1. Клонируйте репозиторий:
```bash
git clone https://github.com/your-repository-url
cd your-project-directory
```

2. Установите зависимости проекта:
Для установки зависимостей используйте Maven:
```bash
mvn install
```

3. Настройка базы данных PostgreSQL:
   * Установите и настройте PostgreSQL на своей машине.
   * Создайте базу данных:
   ```sql
    CREATE DATABASE registry_db;
   ```
   
4. Конфигурация подключения к базе данных:
  ```properties
  spring.datasource.driver-class-name=org.postgresql.Driver
  spring.datasource.url=jdbc:postgresql://localhost:5432/isands
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.properties.hibernate.show_sql=true
  spring.jpa.hibernate.ddl-auto=update
  ```
# Структура проекта
1. Сущности:
   * Appliance (основная сущность для всех типов техники)
   * Device (сущности для различных моделей техники)
   
   Каждая модель будет иметь свои атрибуты, зависящие от типа техники.
2. API для CRUD-операций:
   * Добавление, удаление, обновление и поиск товаров и моделей.
   * Фильтрация по атрибутам.
   * Сортировка по цене и наименованию.

3. Документация через OpenAPI:
   Вся документация доступна через Swagger UI, доступное по URL:
   ```bash
    http://localhost:8080/swagger-ui.html
   ```

# Запуск приложения
1. Запуск с помощью Maven:
   После того, как вы настроили все зависимости и базу данных, вы можете запустить приложение:
   ```bash
    mvn spring-boot:run
   ```
2. Запуск через команду java -jar:
   Для запуска собранного проекта используйте команду:
   ```bash
    java -jar target/registry-app.jar
   ```
3. API документация:
   После того как приложение будет запущено, вы сможете получить доступ к API документации через Swagger UI:
   ```bash
    http://localhost:8080/swagger-ui.html
   ```
