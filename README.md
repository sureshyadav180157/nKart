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
<img width="538" alt="image" src="https://github.com/sureshyadav180157/nKart/assets/171425668/0cb66350-ec81-4d29-a4d1-545e59c864c5">


**API ConfigMap.yaml**

apiVersion: v1
kind: ConfigMap
metadata:
  name: api-config
data:
  DB_HOST: mysql
  DB_PORT: "3306"
  DB_NAME: mydatabase

  


