# User-micro

Microservice responsible for users

## Requests
| Method | HOST                     | Params | Action                                        |
|:------:|:-------------------------|:-------|:----------------------------------------------|
|  GET   | localhost:8082/api/users | ?ids=  | show all users whose ids are specified in ids |

## Entity

### Table

|     Field | Type    | Size | Nullable | Unique | Primary key |
|----------:|:--------|:----:|:--------:|:------:|:-----------:|
|        id | BIGINT  |      |    —     |   ☺    |      ☺      |
| firstname | VARCHAR | 255  |    —     |   —    |             |
|  lastname | VARCHAR | 255  |    —     |   —    |             |
|     phone | VARCHAR | 255  |    —     |   ☺    |             |

## Gifts from spring-boot-data-rest

| Method | HOST                                          | Params               | Action                                                 |
|-------:|:----------------------------------------------|:---------------------|:-------------------------------------------------------|
|    GET | localhost:8082/users                          |                      | Get list all objects                                   |
|    GET | localhost:8082/users/**_id_**                 |                      | Get objects with this **id**                           |
| DELETE | localhost:8082/users/**_id_**                 |                      | Remove object with this **id**                         |
|    PUT | localhost:8082/users/**_id_**                 |                      | Replace object with this **id**                        |
|  PATCH | localhost:8082/users/**_id_**                 |                      | Update object with this **id**                         |
|    GET | localhost:8082/users/search/findBy***FIELD*** | ?***FIELD***=*value* | Search for objects whose fields are equal to the value |


