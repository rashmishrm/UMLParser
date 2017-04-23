#!/bin/bash


INPUT_FOLDER=$1
OUPUT_FILE_NAME=$2

OPTION=1

if [[ $INPUT_FOLDER == *"sequence"* ]];
then
OPTION=2
fi
echo $OPTION
# Compile the sources
echo "Creating diagram"

java -jar umlgenerator.jar -d $INPUT_FOLDER -f $OUPUT_FILE_NAME -t $OPTION
