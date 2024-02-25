# BSUIR GROUP SCHEDULE RestAPI

## SONAR OVERVIEW 
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=rudemaru_garbage&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=rudemaru_garbage) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=rudemaru_garbage&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=rudemaru_garbage)

https://sonarcloud.io/summary/new_code?id=rudemaru_garbage

## What is this project for?
This project implements simple RestAPI to call bsuir schedule api.

## How to use it?
After building and runing the Laba1Application go to localhost:8080 or use any other request sending software like Postman.

## Request requirements
To get any existing groups schedule send GET request to the following endpoint

```
/api/v1/getsched?groupNumber={groupNumber}
```

groupNumber request parameter can be omitted (API will be called with default groupNumber=250503)

## Response structure
The response:
* (200) JSON representing groups schedule
* (404) GroupNumber request parameter is invalid



