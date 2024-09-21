# Company-micro

Microservice responsible for company + company employees

## Requests
| Method | HOST                         | Params | Action                                            |
|:------:|:-----------------------------|:-------|:--------------------------------------------------|
|  GET   | localhost:8082/api/companies | ?ids=  | show all companies whose ids are specified in ids |

## Entity

### Tables

#### Company

|  Field | Type    | Size | Nullable | Unique | Primary key |
|-------:|:--------|:----:|:--------:|:------:|:-----------:|
|     id | BIGINT  |      |    —     |   ☺    |      ☺      |
|   name | VARCHAR | 255  |    —     |   —    |             |
| budget | VARCHAR | 255  |    —     |   —    |             |

#### Employees of the company

|       Field | Type   | Size | Nullable | Unique | Foreign key |
|------------:|:-------|:----:|:--------:|:------:|:-----------:|
|  company_id | BIGINT |      |    —     |   —    | company(id) |
| employee_id | BIGINT |      |    —     |   —    |             |

## Gifts from spring-boot-data-rest

| Method | HOST                                              | Params               | Action                                                 |
|-------:|:--------------------------------------------------|:---------------------|:-------------------------------------------------------|
|    GET | localhost:8082/companies                          |                      | Get list all objects                                   |
|    GET | localhost:8082/companies/**_id_**                 |                      | Get objects with this **id**                           |
| DELETE | localhost:8082/companies/**_id_**                 |                      | Remove object with this **id**                         |
|    PUT | localhost:8082/companies/**_id_**                 |                      | Replace object with this **id**                        |
|  PATCH | localhost:8082/companies/**_id_**                 |                      | Update object with this **id**                         |
|    GET | localhost:8082/companies/search/findBy***FIELD*** | ?***FIELD***=*value* | Search for objects whose fields are equal to the value |


