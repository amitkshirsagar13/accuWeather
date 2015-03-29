#!/bin/bash

while true; do
/usr/bin/java -jar ~/.Conky/images/accuImage/accuWeather.jar `pwd` > ~/.Conky/images/accuImage/log/accuWeatherProcessor.log
echo "Sleeping for 15m at `date '+%d/%m/%Y %H:%M:%S'`...zzz" >> ~/.Conky/images/accuImage/log/accuWeatherProcessor.log
sleep 15m
done
~/.Conky/images/accuImage/imageTemp.sh &
