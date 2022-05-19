# Hungry Tales API Documentation

# Recipe

Core business feature component.

All endpoints require a valid token in the `Authorization: Bearer <token>` format.

This token can be acquired from the [Authentication](Authentication.md) API's Login Endpoint.

# Get Recipe by ID

Returns a specified recipe by it's ID. Returns a 404 if no recipe is found.

## GET /recipe/{id}

### Response Payload

#### Body

| Field | Type |
|-------|------|
|id|int|
|author|string|
|createdDatetime|string|
|feeds|int|
|ingredients|List: IngredientResponse|
|instructions|List: InstructionResponse|
|vegetarian|boolean|

#### Ingredient Response
| Field | Type |
|-------|------|
|name|string|
|amount|int|
|unit|string|

#### Instruction Response
| Field | Type |
|-------|------|
|stepNumber|int|
|instruction|string|

### Example Data

#### 200 Response
```json
{
  "result": true,
  "data": {
    "id": 1,
    "author": "cooldude",
    "title": "A Silly Recipe",
    "createdDatetime": "2022-05-14 20:45",
    "feeds": 2,
    "ingredients": [
      {
        "name": "milk",
        "amount": 500,
        "unit": "ml"
      },
      {
        "name": "flour",
        "amount": 50,
        "unit": "g"
      }
    ],
    "instructions": [
      {
        "stepNumber": 1,
        "instruction": "Pour milk into pan"
      },
      {
        "stepNumber": 2,
        "instruction": "Bring milk to boil"
      },
      {
        "stepNumber": 3,
        "instruction": "Turn down heat"
      },
      {
        "stepNumber": 4,
        "instruction": "Mix flour and milk"
      },
      {
        "stepNumber": 5,
        "instruction": "Simmer for 20 minutes"
      },
      {
        "stepNumber": 6,
        "instruction": "Serve"
      }
    ],
    "vegetarian": false
  }
}
```

#### 404 Response

```json
{
  "result": false,
  "errorMessage": "no recipe found"
}
```

# Update

Updates a specified recipe with the provided body. Only the ID field is required, the recipe is copied verbatim from the
body, so not specifying a field is how a field is deleted (for example, posting an update without the author field will
essentially delete the author for that recipe).

## PUT /recipe/update

### Content-Type

application/json

### Request Payload

| Field | Type | Mandatory |
|-------|------|-----------|
|id|int|yes|
|author|string|no|
|createdDatetime|string|no|
|feeds|int|no|
|ingredients|List: IngredientResponse|no|
|instructions|List: InstructionResponse|no|
|vegetarian|boolean|no|

#### Ingredient Request
| Field | Type |
|-------|------|
|name|string|
|amount|int|
|unit|string|

#### Instruction Request
| Field | Type |
|-------|------|
|stepNumber|int|
|instruction|string|

### Example Data

#### Request

```json
{
  "id": 1,
  "author": "Some other cool dude",
  "title": "Test Recipe, updated",
  "createdDatetime": "2022-05-14 20:45",
  "feeds": 2,
  "ingredients": [
    {
      "name": "milk",
      "amount": 500,
      "unit": "ml"
    },
    {
      "name": "flour",
      "amount": 50,
      "unit": "g"
    },
    {
      "name": "dinosaurs",
      "amount": 5,
      "unit": "tonnes"
    }
  ],
  "instructions": [
    {
      "stepNumber": 1,
      "instruction": "Pour milk into pan"
    },
    {
      "stepNumber": 2,
      "instruction": "Bring milk to boil"
    },
    {
      "stepNumber": 3,
      "instruction": "Turn down heat"
    },
    {
      "stepNumber": 4,
      "instruction": "Mix flour and milk"
    },
    {
      "stepNumber": 5,
      "instruction": "Simmer for 20 minutes"
    },
    {
      "stepNumber": 6,
      "instruction": "Serve"
    }
  ],
  "vegetarian": false
}
```

#### 200 Response
```json
{
  "result": true
}
```

# Create Recipe

Creates a new recipe with the specified fields.

## POST /recipe/create

### Content-Type

application/json

### Request Payload

| Field | Type | Mandatory |
|-------|------|-----------|
|id|int|yes|
|author|string|no|
|createdDatetime|string|no|
|feeds|int|no|
|ingredients|List: IngredientResponse|no|
|instructions|List: InstructionResponse|no|
|vegetarian|boolean|no|

#### Ingredient Request
| Field | Type |
|-------|------|
|name|string|
|amount|int|
|unit|string|

#### Instruction Request
| Field | Type |
|-------|------|
|stepNumber|int|
|instruction|string|

### Example Data

#### Request

```json
{
  "author": "Some other cool dude",
  "title": "Test Recipe, updated",
  "createdDatetime": "2022-05-14 20:45",
  "feeds": 2,
  "ingredients": [
    {
      "name": "milk",
      "amount": 500,
      "unit": "ml"
    },
    {
      "name": "flour",
      "amount": 50,
      "unit": "g"
    },
    {
      "name": "dinosaurs",
      "amount": 5,
      "unit": "tonnes"
    }
  ],
  "instructions": [
    {
      "stepNumber": 1,
      "instruction": "Pour milk into pan"
    },
    {
      "stepNumber": 2,
      "instruction": "Bring milk to boil"
    },
    {
      "stepNumber": 3,
      "instruction": "Turn down heat"
    },
    {
      "stepNumber": 4,
      "instruction": "Mix flour and milk"
    },
    {
      "stepNumber": 5,
      "instruction": "Simmer for 20 minutes"
    },
    {
      "stepNumber": 6,
      "instruction": "Serve"
    }
  ],
  "vegetarian": false
}
```

#### 200 Response
```json
{
  "result": true
}
```

# Delete Recipe

Deletes a specified recipe from the database. Returns successful responses regardless of whether a record was found or not.

## DELETE /recipe/{id}

# List recipes

Returns a list of all stored recipes

## GET /list

## Response Payload

### Example Data

#### 200 Response

```json
{
  "result": true,
  "data": [
    {
      "id": 14,
      "author": "Kurt Kukumber",
      "title": "Shingle Soup",
      "createdDatetime": "2022-05-15 20:57",
      "feeds": 3,
      "ingredients": [
        {
          "name": "Water",
          "amount": 500,
          "unit": "ml"
        }
      ],
      "instructions": [
        {
          "stepNumber": 1,
          "instruction": "Boil water"
        },
        {
          "stepNumber": 2,
          "instruction": "Drink water"
        }
      ],
      "vegetarian": false
    },
    {
      "id": 1,
      "author": "Cool Dude",
      "title": "Test Recipe",
      "createdDatetime": "2022-05-14 20:45",
      "feeds": 2,
      "ingredients": [
        {
          "name": "milk",
          "amount": 500,
          "unit": "ml"
        },
        {
          "name": "flour",
          "amount": 50,
          "unit": "g"
        }
      ],
      "instructions": [
        {
          "stepNumber": 1,
          "instruction": "Pour milk into pan"
        },
        {
          "stepNumber": 2,
          "instruction": "Bring milk to boil"
        },
        {
          "stepNumber": 3,
          "instruction": "Turn down heat"
        },
        {
          "stepNumber": 4,
          "instruction": "Mix flour and milk"
        },
        {
          "stepNumber": 5,
          "instruction": "Simmer for 20 minutes"
        },
        {
          "stepNumber": 6,
          "instruction": "Serve"
        }
      ],
      "vegetarian": false
    }
  ]
}
```
