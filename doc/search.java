# Elastic
PUT favorite_candy

PUT favorite_candy/_doc/1
{
    "first_name" : "Hans",
    "last_name" : "Müller"
}

PUT favorite_candy/_doc/2
{
    "first_name" : "Erich",
    "last_name" : "Meyer"
}

GET favorite_candy/_doc/1

GET favorite_candy/_search
{
    "query": {
    "fuzzy": {
        "last_name": {
            "value": "mueller"
        }
    }
}
}

GET favorite_candy/_search
{
    "query": {
    "prefix": {
        "last_name": {
            "value": "m"
        }
    }
}
}

             
# links
http://localhost:9200/favorite_candy/_doc/1
https://github.com/LisaHJung/Beginners-Crash-Course-to-Elastic-Stack-Series-Table-of-Contents

https://www.baeldung.com/lucene
https://www.springcloud.io/post/2022-04/spring-boot-hibernate-search/#gsc.tab=0

