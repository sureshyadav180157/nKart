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
kind: ConfigMap
metadata:
  name: mysql-config
data:
  MYSQL_DATABASE: mydatabase

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


