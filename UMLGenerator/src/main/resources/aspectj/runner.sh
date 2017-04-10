#!/bin/bash

export CLASSPATH=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib/aspectjrt.jar:.
export ASPECTJ_HOME=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8
export ASPECTJ_RT=/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/aspectj1.8/lib/aspectjrt.jar
export PATH=$PATH:$ASPECTJ_HOME/bin:$JAVA_HOME/bin



# Compile the sources
echo "Compiling..."
javac -classpath $CLASSPATH -g -d /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test MethodInterceptorjAJ.java

echo "Weaving aspect..."
java -cp $CLASSPATH org.aspectj.tools.ajc.Main -source 1.5 -inpath /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test -aspectpath ./src/main/java -outjar /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test/test.jar

# Run the example and check that aspect logic is applied
echo "Running the sample..."
java -cp $CLASSPATH:/Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/rashmi/test-cases/umlparser/uml-sequence-test/test.jar com.aspectj.Main
