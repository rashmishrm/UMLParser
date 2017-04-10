#!/bin/bash


INPUT_FOLDER=$1
ASPECT_LIB=$2
ASPECT_FOLDER=$3
ASPECT_FILE=$4

echo

echo $INPUT_FOLDER
echo $ASPECT_LIB
echo $ASPECT_FOLDER
echo $ASPECT_FILE

for i in 'aspectjtools.jar' 'aspectjrt.jar'
do
    CLASSPATH=$CLASSPATH:$ASPECT_LIB/$i
done

#JAR_DIR=$INPUT_FOLDER/compile-time

CLASSES_DIR=$INPUT_FOLDER/compile-time

rm -rf $CLASSES_DIR
mkdir $CLASSES_DIR

cp $ASPECT_FOLDER/$ASPECT_FILE $INPUT_FOLDER

# Compile the sources
echo "Compiling..."

java -cp $CLASSPATH org.aspectj.tools.ajc.Main -source 1.5 -d $CLASSES_DIR $INPUT_FOLDER/*.java $INPUT_FOLDER/$ASPECT_FILE

# Run the example and check that aspect logic is applied
echo "Running the sample..."
java -cp $CLASSPATH:$CLASSES_DIR Main
