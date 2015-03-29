#!/bin/bash
/usr/bin/java -jar /home/poomit/.Conky/images/accuImage/accuWeather.jar `pwd` > /home/poomit/.Conky/images/accuImage/log/accuWeatherProcessor.log
/home/poomit/.Conky/images/accuImage/imageTemp.sh &
