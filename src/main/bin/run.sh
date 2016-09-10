#!/bin/sh
APP_HOME=`pwd`
OPTIONS="-Dclassworlds.conf=$APP_HOME/conf/application.conf -Dapp.home=$APP_HOME -Dfile.encoding=UTF-8"
CLASSPATH="$APP_HOME/lib/classworlds-1.0.jar"
${JAVA_HOME}/bin/java -classpath ${CLASSPATH} ${OPTIONS} org.codehaus.classworlds.Launcher $1 $2
