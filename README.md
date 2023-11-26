 [![Gitter](https://img.shields.io/badge/Available%20on-Intersystems%20Open%20Exchange-00b2a9.svg)](https://openexchange.intersystems.com/package/imageaicreator)
 [![Quality Gate Status](https://community.objectscriptquality.com/api/project_badges/measure?project=intersystems_iris_community%2Fjirisreport&metric=alert_status)](https://community.objectscriptquality.com/dashboard?id=intersystems_iris_community%2Fimageaicreator)
 [![Reliability Rating](https://community.objectscriptquality.com/api/project_badges/measure?project=intersystems_iris_community%2Fjirisreport&metric=reliability_rating)](https://community.objectscriptquality.com/dashboard?id=intersystems_iris_community%2Fimageaicreator)

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat&logo=AdGuard)](LICENSE)
# Image AI Creator
This is a tool to generate images from a text sentence using AI

## Description
The Image AI Creator allows you:
* Set a text and get a PNG image

## Prerequisites
* Create your key on https://platform.imagine.art/

## Installation with Docker

Clone/git pull the repo into any local directory

```
$ git clone https://github.com/yurimarx/imageaicreator.git
```

Open the terminal in this directory and call the command to build and run InterSystems IRIS in container:
*Note: Users running containers on a Linux CLI, should use "docker compose" instead of "docker-compose"*
*See [Install the Compose plugin](https://docs.docker.com/compose/install/linux/)*


```
$ docker-compose build
$ docker-compose up -d
```

## Installation with ZPM

```
USER> zpm install imageaicreator
```

Set your key:

```

```

## Testing
1. Open http://localhost:<WebServerPort>/swagger-ui/index.html
2. Run the method /generate passing in the body a text with instructions to generate the image (use enghish)
3. Download the png file on the link Download file
