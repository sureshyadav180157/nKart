# Kubernetes Deployment: MySQL Database and API Service
## Table of Contents
- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Configuration Files](#configuration-files)
  - [ConfigMaps and Secrets](#configmaps-and-secrets)
  - [PersistentVolume and PersistentVolumeClaim](#persistentvolume-and-persistentvolumeclaim)
  - [StatefulSet and Headless Service for MySQL](#statefulset-and-headless-service-for-mysql)
  - [ReplicaSet and Service for API](#replicaset-and-service-for-api)
  - [Horizontal Pod Autoscaler](#horizontal-pod-autoscaler)
- [Deployment Steps](#deployment-steps)
- [Testing Horizontal Pod Autoscaler](#testing-horizontal-pod-autoscaler)
- [Additional Resources](#additional-resources)

## Overview\
This guide provides steps to deploy a MySQL database and an API service in a Kubernetes cluster. The MySQL database is deployed using StatefulSets, ensuring data persistence, while the API service is deployed using ReplicaSets with Horizontal Pod Autoscaler (HPA) to manage load.\
## Prerequisites\
- A running Kubernetes cluster\
- `kubectl` command-line tool configured to interact with your cluster\
- A Docker image for your API service\

**Additional Resources**\

Code Repository: https://github.com/sureshyadav180157/nKart \
Docker Hub URL: https://hub.docker.com/repositories/amitkumar01 [Username:amitkumar01, Password:amitkumar@01]\
Service API URL: [API Service URL](http://35.229.218.193/nKart/products) \
Screen Recording: Screen Recording Video \
API Docker File Path: https://github.com/sureshyadav180157/nKart/tree/main/NKart-App \
MySQL Docker File Path: https://github.com/sureshyadav180157/nKart/tree/main/NKart-App/db \
kubernetes YAML File Path: https://github.com/sureshyadav180157/nKart/tree/main/NKart-App/k8s \
  
## Configuration Files\

**MySQL Secret.yaml**\

apiVersion: v1\
kind: Secret\
metadata:\
  name: mysql-secret\
type: Opaque\
data:\
  MYSQL_ROOT_PASSWORD: cGFzc3dvcmQ= # base64 encoded 'password'\
  MYSQL_USER: bXl1c2Vy # base64 encoded 'myuser'\
  MYSQL_PASSWORD: bXlwYXNzd29yZA== # base64 encoded 'mypassword'\

**API ConfigMap.yaml**\

apiVersion: v1\
kind: ConfigMap\
metadata:\
  name: api-config\
data:\
  DB_HOST: mysql\
  DB_PORT: "3306"\
  DB_NAME: mydatabase\

 **API Secret**\

 apiVersion: v1\
kind: Secret\
metadata:\
  name: api-secret\
type: Opaque\
data:\
  DB_USER: bXl1c2Vy # base64 encoded 'myuser'\
  DB_PASSWORD: bXlwYXNzd29yZA== # base64 encoded 'mypassword'\

**PersistentVolume and PersistentVolumeClaim\
PersistentVolume**\

apiVersion: v1\
kind: PersistentVolume\
metadata:\
  name: mysql-pv\
spec:\
  capacity:\
    storage: 1Gi\
  accessModes:\
    - ReadWriteOnce\
  hostPath:\
    path: /mnt/data/mysql\

**PersistentVolumeClaim**\

apiVersion: v1\
kind: PersistentVolumeClaim\
metadata:\
  name: mysql-pvc\
spec:\
  accessModes:\
    - ReadWriteOnce\
  resources:\
 requests:\
 storage: 1Gi\

**StatefulSet and Headless Service for MySQL\
MySQL StatefulSet**/

apiVersion: apps/v1\
kind: StatefulSet\
metadata:\
  name: mysql\
spec:\
  serviceName: "mysql"\
  replicas: 1\
  selector:\
    matchLabels:\
app: mysql\
  template:\
    metadata:\
      labels:\
        app: mysql\
    spec:\
      containers:\
      - name: mysql\
        image: mysql:5.7\
        env:\
        - name: MYSQL_ROOT_PASSWORD\
          valueFrom:\
            secretKeyRef:\
              name: mysql-secret\
              key: MYSQL_ROOT_PASSWORD\
        - name: MYSQL_DATABASE\
          valueFrom:\
            configMapKeyRef:\
              name: mysql-config\
              key: MYSQL_DATABASE\
        - name: MYSQL_USER\
          valueFrom:\
            secretKeyRef:\
              name: mysql-secret\
              key: MYSQL_USER\
        - name: MYSQL_PASSWORD\
          valueFrom:\
            secretKeyRef:\
              name: mysql-secret\
              key: MYSQL_PASSWORD\
        ports:\
        - containerPort: 3306\
          name: mysql\
        volumeMounts:\
        - name: mysql-persistent-storage\
          mountPath: /var/lib/mysql\
  volumeClaimTemplates:\
  - metadata:\
      name: mysql-persistent-storage\
    spec:\
      accessModes: [ "ReadWriteOnce" ]\
      resources:\
        requests:\
          storage: 1Gi\

**MySQL Headless Service**\

apiVersion: v1\
kind: Service\
metadata:\
  name: mysql\
spec:\
  ports:\
  - port: 3306\
    name: mysql\
  clusterIP: None\
  selector:\
    app: mysql\

**ReplicaSet and Service for API\
API ReplicaSet**\

apiVersion: apps/v1\
kind: ReplicaSet\
metadata:\
  name: api\
spec:\
  replicas: 3\
  selector:\
    matchLabels:\
      app: api\
  template:\
    metadata:\
      labels:\
        app: api\
    spec:\
      containers:\
      - name: api\
        image: your-api-image\
        env:\
        - name: DB_HOST\
          valueFrom:\
            configMapKeyRef:\
              name: api-config\
              key: DB_HOST\
        - name: DB_PORT\
          valueFrom:\
            configMapKeyRef:\
              name: api-config\
              key: DB_PORT\
        - name: DB_NAME\
          valueFrom:\
            configMapKeyRef:\
              name: api-config\
              key: DB_NAME\
        - name: DB_USER\
          valueFrom:\
            secretKeyRef:\
              name: api-secret\
              key: DB_USER\
        - name: DB_PASSWORD\
          valueFrom:\
            secretKeyRef:\
              name: api-secret\
              key: DB_PASSWORD\
        ports:\
        - containerPort: 8080\
          name: http\

**Horizontal Pod Autoscaler\
API HPA**\

apiVersion: autoscaling/v1\
kind: HorizontalPodAutoscaler\
metadata:\
  name: api-hpa\
spec:\
  scaleTargetRef:\
    apiVersion: apps/v1\
    kind: ReplicaSet\
    name: api\
  minReplicas: 3\
  maxReplicas: 10\
  targetCPUUtilizationPercentage: 50\

**Deployment Steps\
Apply ConfigMaps and Secrets:**\

kubectl apply -f mysql-configmap.yaml\
kubectl apply -f mysql-secret.yaml\
kubectl apply -f api-configmap.yaml\
kubectl apply -f api-secret.yaml\

**Apply PersistentVolume and PersistentVolumeClaim:**\

kubectl apply -f mysql-pv.yaml\
kubectl apply -f mysql-pvc.yaml\

**Apply StatefulSet and Headless Service:**\

kubectl apply -f mysql-statefulset.yaml\
kubectl apply -f mysql-service.yaml\

**Apply ReplicaSet and Service for API:**\

kubectl apply -f api-replicaset.yaml\
kubectl apply -f api-service.yaml\

**Apply Horizontal Pod Autoscaler:**\

kubectl apply -f api-hpa.yaml\
Testing Horizontal Pod Autoscaler\

**To test the Horizontal Pod Autoscaler, generate load on the API service:\
Create a temporary load generator pod:**\

kubectl run -i --tty load-generator --image=busybox /bin/sh\
Run the following command inside the load-generator pod:\
while true; do wget -q -O- http://api-service; done\
This will continuously send requests to the API service, triggering the HPA to scale the number of API pods based on CPU utilization.\





  


