# MyProducts API

## Описание

MyProducts API предоставляет интерфейсы для управления продуктами, отгрузками и продажами в интернет-магазине. Это API позволяет создавать, получать, обновлять и удалять информацию о продуктах, отгрузках и продажах.

## Эндпоинты

### Продукты

#### Получить все продукты

- **URL:** `/products`
- **Метод:** `GET`
- **Описание:** Возвращает список всех продуктов.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/products
    ```
- **Пример ответа:**
    ```json
    [
        {
            "id": 1,
            "name": "Product 1",
            "description": "Description of Product 1",
            "price": 100.0,
            "available": true,
            "amount": 50
        },
        ...
    ]
    ```

#### Получить продукт по ID

- **URL:** `/products/{id}`
- **Метод:** `GET`
- **Описание:** Возвращает продукт по его ID.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/products/1
    ```
- **Пример ответа:**
    ```json
    {
        "id": 1,
        "name": "Product 1",
        "description": "Description of Product 1",
        "price": 100.0,
        "available": true,
        "amount": 50
    }
    ```

#### Создать новый продукт

- **URL:** `/products`
- **Метод:** `POST`
- **Описание:** Создает новый продукт.
- **Пример запроса:**
    ```bash
    curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{
        "name": "New Product",
        "description": "Description of new product",
        "price": 150.0,
        "available": true,
        "amount": 100
    }'
    ```
- **Пример ответа:**
    ```json
    {
        "id": 2,
        "name": "New Product",
        "description": "Description of new product",
        "price": 150.0,
        "available": true,
        "amount": 100
    }
    ```

#### Обновить продукт

- **URL:** `/products/{id}`
- **Метод:** `PUT`
- **Описание:** Обновляет информацию о продукте по его ID.
- **Пример запроса:**
    ```bash
    curl -X PUT http://localhost:8080/products/1 -H "Content-Type: application/json" -d '{
        "name": "Updated Product",
        "description": "Updated description",
        "price": 120.0,
        "available": false,
        "amount": 30
    }'
    ```
- **Пример ответа:**
    ```json
    {
        "id": 1,
        "name": "Updated Product",
        "description": "Updated description",
        "price": 120.0,
        "available": false,
        "amount": 30
    }
    ```

#### Удалить продукт

- **URL:** `/products/{id}`
- **Метод:** `DELETE`
- **Описание:** Удаляет продукт по его ID.
- **Пример запроса:**
    ```bash
    curl -X DELETE http://localhost:8080/products/1
    ```

### Отгрузки (Shipments)

#### Получить все отгрузки

- **URL:** `/shipments`
- **Метод:** `GET`
- **Описание:** Возвращает список всех отгрузок.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/shipments
    ```

#### Получить отгрузку по ID

- **URL:** `/shipments/{id}`
- **Метод:** `GET`
- **Описание:** Возвращает отгрузку по её ID.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/shipments/1
    ```

#### Создать новую отгрузку

- **URL:** `/shipments`
- **Метод:** `POST`
- **Описание:** Создает новую отгрузку.
- **Пример запроса:**
    ```bash
    curl -X POST http://localhost:8080/shipments -H "Content-Type: application/json" -d '{
        "name": "New Shipment",
        "amount": 100,
        "product": {
            "id": 1,
            "name": "Product Name",
            "description": "Description",
            "price": 100.0,
            "available": true,
            "amount": 100
        }
    }'
    ```

#### Обновить отгрузку

- **URL:** `/shipments/{id}`
- **Метод:** `PUT`
- **Описание:** Обновляет информацию об отгрузке по её ID.
- **Пример запроса:**
    ```bash
    curl -X PUT http://localhost:8080/shipments/1 -H "Content-Type: application/json" -d '{
        "name": "Updated Shipment",
        "amount": 200,
        "product": {
            "id": 1,
            "name": "Product Name",
            "description": "Description",
            "price": 100.0,
            "available": true,
            "amount": 100
        }
    }'
    ```

#### Удалить отгрузку

- **URL:** `/shipments/{id}`
- **Метод:** `DELETE`
- **Описание:** Удаляет отгрузку по её ID.
- **Пример запроса:**
    ```bash
    curl -X DELETE http://localhost:8080/shipments/1
    ```

### Продажи (Sellings)

#### Получить все продажи

- **URL:** `/sellings`
- **Метод:** `GET`
- **Описание:** Возвращает список всех продаж.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/sellings
    ```

#### Получить продажу по ID

- **URL:** `/sellings/{id}`
- **Метод:** `GET`
- **Описание:** Возвращает продажу по её ID.
- **Пример запроса:**
    ```bash
    curl -X GET http://localhost:8080/sellings/1
    ```

#### Создать новую продажу

- **URL:** `/sellings`
- **Метод:** `POST`
- **Описание:** Создает новую продажу.
- **Пример запроса:**
    ```bash
    curl -X POST http://localhost:8080/sellings -H "Content-Type: application/json" -d '{
        "name": "New Selling",
        "amount": 50,
        "product": {
            "id": 1,
            "name": "Product Name",
            "description": "Description",
            "price": 100.0,
            "available": true,
            "amount": 100
        }
    }'
    ```

#### Обновить продажу

- **URL:** `/sellings/{id}`
- **Метод:** `PUT`
- **Описание:** Обновляет информацию о продаже по её ID.
- **Пример запроса:**
    ```bash
    curl -X PUT http://localhost:8080/sellings/1 -H "Content-Type: application/json" -d '{
        "name": "Updated Selling",
        "amount": 100,
        "product": {
            "id": 1,
            "name": "Product Name",
            "description": "Description",
            "price": 100.0,
            "available": true,
            "amount": 100
        }
    }'
    ```

#### Удалить продажу

- **URL:** `/sellings/{id}`
- **Метод:** `DELETE`
- **Описание:** Удаляет продажу по её ID.
- **Пример запроса:**
    ```bash
    curl -X DELETE http://localhost:8080/sellings/1
    ```

## Запуск тестов

Для запуска тестов используйте следующую команду:
```bash
mvn test
