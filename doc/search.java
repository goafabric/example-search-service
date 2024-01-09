# Elastic
PUT person_names

PUT person_names/_doc/1
{
    "first_name" : "Hans",
    "last_name" : "Müller"
}

PUT person_names/_doc/2
{
    "first_name" : "Erich",
    "last_name" : "Meyer"
}

GET person_names/_doc/1

GET person_names/_search
{
    "query":{
        "fuzzy":{
            "last_name":{
                "value":"mueller"
            }
        }
    }
}

GET person_names/_search
{
    "query": {
    "prefix": {
        "last_name": {
            "value": "m"
        }
    }
}
}


GET person_names/_search
{
    "query": {
    "bool": {
        "should": [
        {
            "wildcard": {
            "last_name": {
                "value": "mü*"
            }
        }
        },
        {
            "fuzzy": {
            "last_name": {
                "value": "mu",
                        "fuzziness": "AUTO"
            }
        }
        }
      ]
    }
}
}
             
# links
http://localhost:9200/person_names/_doc/1
https://github.com/LisaHJung/Beginners-Crash-Course-to-Elastic-Stack-Series-Table-of-Contents

https://www.baeldung.com/lucene
https://www.springcloud.io/post/2022-04/spring-boot-hibernate-search/#gsc.tab=0

