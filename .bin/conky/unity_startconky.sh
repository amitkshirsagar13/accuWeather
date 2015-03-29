#!/bin/bash
sleep 60
~/.Conky/images/accuImage/accuWeatherCollectInformation.sh &
conky -c ~/.Conky/unity_cpu &
sleep 1
conky -c ~/.Conky/unity_mem &
sleep 1
conky -c ~/.Conky/unity_temp &
sleep 5
conky -c ~/.Conky/unity_rings &
sleep 30
conky -c ~/.Conky/accutest &
sleep 1
#conky -c ~/.Conky/unity_radar &
sleep 1
conky -c ~/.Conky/mail &
#sleep 1
#conky  -c ~/.bin/conky/.conkyrc
