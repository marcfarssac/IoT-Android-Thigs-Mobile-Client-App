# IoT Android App client displaying remote Android Things sensor values in Realtime

This is the client of an Android Things IoT Project that reads temperature data from linked Bluetooth Low Energy sensors.

## Introduction

- [Read more about this project](https://github.com/marcfarssac/IoT-Android-Things-Multiple-Sensor-Remote-Temperature-Monitor)

## Project description

Using a Mobile phone, a Raspberry Pi 3B runing the Android Things OS and three Texas Instruments Sensors Tags connected with the board over Bluetooth Low Energy, the project shows how to monitor the heating temperature of a flat with sensors located at different rooms. Tempearature measurements are uploaded to the Cloud and displayed in a remote Android App client in real time (actually sensor readings are only uploaded every five seconds and that's why the readings seem not in real time, but they are almost).

### Version 0.1

This initial version worked by recieving Firebase Notifications from the backend. This was a very good approach to start off, as they are free of charge. However since they are not sent in realtime, there were differences among the values displayed on the LCDs and those on the mobile phone. This could be a little bit annoying an although it is not critical to recieve the temperature on the mobile in real time, it was not the optimal solution.

### Version 0.5

This version connects to a Firebase realtime database and displays the values in real time. In order for this App to work, some changes had to be done on the backend. Instead of sending Firebase notifications, a Firebase Cloud Function has been deployed to store the values in the database.

### Youtube: working prototype v. 0.5

![iot-real-time](https://user-images.githubusercontent.com/18221570/48318536-bbce9080-e602-11e8-951c-29af91aa3dd7.PNG)(https://www.youtube.com/watch?v=9z9KXS_KiRE&feature=youtu.be)

YouTube video link: [https://www.youtube.com/watch?v=9z9KXS_KiRE&feature=youtu.be](https://www.youtube.com/watch?v=9z9KXS_KiRE&feature=youtu.be)

### Getting Started

In order to see the values changing you must be connected to the Android Things IoT App.


License
-------

Copyright 2018 Marc Farssac

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
