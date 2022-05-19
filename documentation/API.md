# HungryTales API Documentation

# Authentication

## POST /auth/register

| Field | Type | Mandatory |
|-------|------|-----------|
|Username|String|Yes|
|Password|String|Yes|
|Name|String|No|
|Surname|String|No|

## Example Data

### Request
```json
{
  "username": "cooldude",
  "password": "MySuperSecretPassword",
  "name": "Cool",
  "surname": "Dude"
}
```

### Response
