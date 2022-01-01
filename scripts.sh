
#개발용 DB
docker run -p 5432:5432 --name study-db -e POSTGRES_USER=study -e POSTGRES_PASSWORD=study -e POSTGRES_DB=study -d postgres

#테스트용 DB
docker run -p 15432:5432 --name study-testdb -e POSTGRES_USER=studytest -e POSTGRES_PASSWORD=studytest -e POSTGRES_DB=studytest -d postgres

#httpie 사용 GET
http http://localhost:8080/study/1

#httpie 사용 POST
http post http://localhost:8080/study limitCount=20 name=test

#apach bench 사용
ab -n 100 -c 10 http://localhost:8080/study/1
