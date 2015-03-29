#!/bin/sh
while read line
do 
	wget $line
done < imageList.txt
