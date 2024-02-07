Build and Tag Docker Image
docker build -t player-service:v1 .

Push Docker Image to a Registry
docker tag player-service:v1 <your-registry>/player-service:v1
docker push <your-registry>/player-service:v1

Deploy to Kubernetes Cluster
kubectl apply -f deployment/deployment.yaml

Expose Your Service
kubectl apply -f deployment/service.yaml