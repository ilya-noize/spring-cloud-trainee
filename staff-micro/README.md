# Staff-micro

Microservice responsible for managing the staff of companies

## Requests
| Method | HOST                         | Params | Action                                                           |
|:------:|:-----------------------------|:-------|:-----------------------------------------------------------------|
|  GET   | localhost:8800/company/staff | ?ids=  | show all companies with employees whose ids are specified in ids |

## Dependencies

To work with this microservice, the following microservices are needed:
- users-micro,
- company-micro,
- discovery-micro.