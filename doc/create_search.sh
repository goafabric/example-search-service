#!/bin/bash

function put {
    curl -X PUT "localhost:9200/person_names/_doc/"${1} -H 'Content-Type: application/json' -d "${2}"
}

function search {
json='
{
  "query":{
      "fuzzy":{
          "last_name":{
              "value":"'${1}'"
          }
      }
  }
}'
    curl -X GET "localhost:9200/person_names/_search" -H 'Content-Type: application/json' -d "$json"
}

json_data='{
    "first_name": "Hans",
    "last_name": "Müller"
}'

put 1 "$json_data"

json_data='{
    "first_name": "Erich",
    "last_name": "Meyer"
}'

put 2 "$json_data"

search "Meyer"

