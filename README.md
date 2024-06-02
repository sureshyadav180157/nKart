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

## Overview
This guide provides steps to deploy a MySQL database and an API service in a Kubernetes cluster. The MySQL database is deployed using StatefulSets, ensuring data persistence, while the API service is deployed using ReplicaSets with Horizontal Pod Autoscaler (HPA) to manage load.
## Prerequisites
- A running Kubernetes cluster
- `kubectl` command-line tool configured to interact with your cluster
- A Docker image for your API service
  
## Configuration Files

**MySQL Secret.yaml**

apiVersion: v1
kind: Secret
metadata:
  name: mysql-secret
type: Opaque
data:
  MYSQL_ROOT_PASSWORD: cGFzc3dvcmQ= # base64 encoded 'password'
  MYSQL_USER: bXl1c2Vy # base64 encoded 'myuser'
  MYSQL_PASSWORD: bXlwYXNzd29yZA== # base64 encoded 'mypassword'

**API ConfigMap.yaml**

apiVersion: v1
kind: ConfigMap
metadata:
  name: api-config
data:
  DB_HOST: mysql
  DB_PORT: "3306"
  DB_NAME: mydatabase

 **API Secret**

 apiVersion: v1
kind: Secret
metadata:
  name: api-secret
type: Opaque
data:
  DB_USER: bXl1c2Vy # base64 encoded 'myuser'
  DB_PASSWORD: bXlwYXNzd29yZA== # base64 encoded 'mypassword'

**PersistentVolume and PersistentVolumeClaim
PersistentVolume**

apiVersion: v1
kind: PersistentVolume
metadata:
  name: mysql-pv
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: /mnt/data/mysql

	**PersistentVolumeClaim**

 apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi

**StatefulSet and Headless Service for MySQL
MySQL StatefulSet**

apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql
spec:
  serviceName: "mysql"
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - name: mysql
        image: mysql:5.7
        env:
        - name: MYSQL_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-secret
              key: MYSQL_ROOT_PASSWORD
        - name: MYSQL_DATABASE
          valueFrom:
            configMapKeyRef:
              name: mysql-config
              key: MYSQL_DATABASE
        - name: MYSQL_USER
          valueFrom:
            secretKeyRef:
              name: mysql-secret
              key: MYSQL_USER
        - name: MYSQL_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-secret
              key: MYSQL_PASSWORD
        ports:
        - containerPort: 3306
          name: mysql
        volumeMounts:
        - name: mysql-persistent-storage
          mountPath: /var/lib/mysql
  volumeClaimTemplates:
  - metadata:
      name: mysql-persistent-storage
    spec:
      accessModes: [ "ReadWriteOnce" ]
      resources:
        requests:
          storage: 1Gi

**MySQL Headless Service**

apiVersion: v1
kind: Service
metadata:
  name: mysql
spec:
  ports:
  - port: 3306
    name: mysql
  clusterIP: None
  selector:
    app: mysql





  


