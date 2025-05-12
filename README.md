# Stream Processing Service

Система для потоковой обработки JSON-сообщений с использованием Apache Kafka, Redis, PostgreSQL, MongoDB.

## 🏗 Архитектура

### Основные этапы обработки:
1. **Фильтрация**  
   Отсеивание сообщений по заданным правилам из PostgreSQL.

2. **Дедубликация**  
   Использование Redis с TTL для исключения дубликатов сообщений.

3. **Обогащение**  
   Добавление дополнительных данных в JSON-сообщения с помощью MongoDB.

### Системные компоненты:
| Компонент               | Технологии                | Назначение                          |
|-------------------------|---------------------------|-------------------------------------|
| `JavaServiceManagement` | Spring Boot, PostgreSQL   | Управление правилами обработки      |
| `JavaServiceBFF`        | Spring Security, JWT      | Аутентификация и авторизация        |
| Обрабатывающие сервисы  | Kafka, Redis              | Потоковая обработка данных          |

## 🔑 Особенности
- **Управление правилами** через REST API
- **JWT-аутентификация** для доступа к API
- Готовые Docker-образы на DockerHub (в docker-compose.yml)
- Интеграция с Prometheus/Grafana для мониторинга


## 🚀 Быстрый старт

### Запуск сервисов (нужно из JavaMonitoringService)
```bash
docker-compose up --build
```

**Если возникли проблемы с именем файла:**
```bash
docker-compose -p sasha up
```

### Проверка работы сервисов
Дождитесь, пока все сервисы перейдут в состояние `UP`:
- [Метрики Prometheus](http://localhost:9090/targets)

## 🔌 API Endpoints

### Kafka Endpoints
- **Отправка сообщения**  
  `POST http://localhost:8080/kafka/send`  
  (Использует готовый JSON-шаблон с "Джоном")

- **Получение обработанных сообщений**  
  `GET http://localhost:8080/kafka/messages`  
  *Важно:* Для получения данных должно быть хотя бы одно правило фильтрации.

## 🔐 Аутентификация
- Все запросы (кроме регистрации) требуют JWT-токена
- **Не забудьте:**  
  Добавить глобальную переменную `jwt_token` в Postman (`Environments -> Globals`)

## 📊 Мониторинг (Grafana)

1. Откройте [Grafana](http://localhost:3000)
    - Логин: `admin`
    - Пароль: `admin`
    - При запросе обновления → нажмите **Skip**

2. Настройте источник данных:
    - `Data sources` → `Add data source` → `Prometheus`
    - URL: `http://prometheus:9090`
    - `Save & Test`

3. Импортируйте дашборд:
    - `Dashboards` → `New` → `Import`
    - Загрузите `grafana.json`
    - Выберите созданный источник данных Prometheus

## 🛠 Инструменты
- Готовые запросы Postman в [файле](JavaMonitoringService/lab8%20test.postman_collection.json)
- Docker-ориентированная архитектура
- Prometheus + Grafana для мониторинга

## ⚠️ Важные заметки
- Для работы Kafka-эндпоинтов необходимы активные правила фильтрации
- При переименовании `docker-compose.yaml` используйте флаг `-p`
- JWT-токен обязателен для всех защищенных endpoint'ов