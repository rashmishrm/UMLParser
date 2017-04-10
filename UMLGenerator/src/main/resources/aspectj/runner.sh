#!/bin/bash

export CLASSPATH=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib/aspectjrt.jar:.
export ASPECTJ_HOME=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8
export ASPECTJ_RT=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib/aspectjrt.jar
export ASPECT_LIB=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib
export PATH=$PATH:$ASPECTJ_HOME/bin:$JAVA_HOME/bin
#CLASSPATH=$CLASSPATH:$ASPECTJ_HOME:$ASPECTJ_RT:$ASPECT_LIB

INPUT_FOLDER=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test

for i in 'aspectjtools.jar' 'aspectjrt.jar'
do
    CLASSPATH=$CLASSPATH:/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib/$i
done

JAR_DIR=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test/compile-time

CLASSES_DIR=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test/compile-time

rm -rf $CLASSES_DIR
mkdir $CLASSES_DIR

cp /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/UMLGenerator/src/main/java/com/sjsu/umlgenerator/aspect/MethodInterceptorjAJ $INPUT_FOLDER

# Compile the sources
echo "Compiling..."
javac -classpath $CLASSPATH -g -d $CLASSES_DIR $INPUT_FOLDER/*.java

#echo "Weaving aspect..."
#java -cp $CLASSPATH org.aspectj.tools.ajc.Main -source 1.5 -inpath $CLASSES_DIR -aspectpath ./src/main/java -outjar $JAR_DIR/test.jar

# Run the example and check that aspect logic is applied
#echo "Running the sample..."
#java -cp $CLASSPATH:$JAR_DIR/test.jar com.aspectj.Main
