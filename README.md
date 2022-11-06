# SampleAPIImplementation

This project sample example of API calling with MVVM Architecture. 
## Overview:
This application shows a list of items, here the application initially fetches the data from API. If network is not available, Waits until network connects, If app is in foreground or background or killed.
By using Workmanager schedule the api fetching in a network scenario.
If once fetch the data from API, It will store the data into the database. Here I used Room DB for storing and retrieving the data. 

## Libriaries used: 
1. RX Java
2. Room
3. WorkManager
4. Hilt
5. Retrofit
6. Espresso

## Architecture
MVVM

## This app has following packages

1. api : Having api services and Retrofit end point.
2. model: Having Viewmode and model information
3. offline: Room db implementation
4. repo: Fetching the data from online

## Test Cases:
1. Api validation
2. Data updating and validation in DB
3. UI validation
4. Adapter Testing
