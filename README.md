# Star Wars

Starwars is a web application based on Micronaout framework whose main purpose 
is showing Person and Starships data retrieved from an external api: 
[SWAPI](https://swapi.dev/)

## Built with

* Micronaut Framework 2.2.0
* Java SE 11
* Gradle 6.7
* Docker

## Blasting off 🚀
To compile this application, make sure that your environment variable 
*JAVA_HOME* is pointing to that **JDK 11** directory.

Once checked that point, you can compile and test the web application with:
```bash
./gradle clean build
```
To set up the server executing:
```bash
./gradlew run
```
This command 

## Run application as a Docker container

You can specify the port in which you want to run the web application as an 
argument when create the Docker image.

1. Generate a Docker image with the desired port (**APP_PORT**) executing the 
following command:
```bash
docker build --build-arg APP_PORT=6969 --tag starwars:1.0 .
```
2. Run the image as a container:
```bash
docker run --publish 6969:6969 --name starwars starwars:1.0
```

*That's it!*

Open `localhost:6969` and enjoy diving in the Star Wars web application.


## Kubernetes
To deploy the application in a kubernetes cluster, just need to apply the 
definition file `k8s-starwars.yml` executing this command inside the cluster:
```bash
kubectl apply -f k8s-starwars.yml
```
This generates a deployment with 3 replicas and a load balancer to be able to 
access from outside. The cloud service assigns automatically an external IP to 
the load balancer service.
```bash
kubectl get services
```

In case to deploy in your local machine, it is necessary to define an 
IngressController and IngressResources to be able to access to de Pods from 
outside.
