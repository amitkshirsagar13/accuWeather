#!/bin/sh

while read icon
do
	wget $icon
done < icon.txt
