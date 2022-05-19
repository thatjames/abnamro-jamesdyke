# HungryTales API Document

All payloads between client and the server are wrapped in a basic JSON envelope consisting
of 3 fields

1. Result: Boolean indicator for success or false
2. Data: Interface containing result payload
3. Error Message: Brief error message if transaction failed

```json
{
  "result": true,
  "data": {
    "foo": "bar"
  },
  "errorMessage": ""
}
```

1. [Authentication API](Authentication.md)
2. [Recipe API](Recipe.md)