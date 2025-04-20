docker-compose down

docker rmi microservice-api-gateway:latest
docker rmi microservice-config-server:latest
docker rmi microservice-eureka:latest
docker rmi microservice-inventory:latest
docker rmi microservice-product:latest

mvn clean install -DskipTests -f ./microservice-eureka/pom.xml
mvn clean install -DskipTests -f ./microservice-config-server/pom.xml
mvn clean install -DskipTests -f ./microservice-api-gateway/pom.xml
mvn clean install -DskipTests -f ./microservice-inventory/pom.xml
mvn clean install -DskipTests -f ./microservice-product/pom.xml

docker build -t microservice-api-gateway:latest -f ./microservice-api-gateway/Dockerfile ./microservice-api-gateway
docker build -t microservice-config-server:latest -f ./microservice-config-server/Dockerfile ./microservice-config-server
docker build -t microservice-eureka:latest -f ./microservice-eureka/Dockerfile ./microservice-eureka
docker build -t microservice-inventory:latest -f ./microservice-inventory/Dockerfile ./microservice-inventory
docker build -t microservice-product:latest -f ./microservice-product/Dockerfile ./microservice-product

docker-compose up -d

echo "Docker compose up. Waiting to run migrations"