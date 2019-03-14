#!/usr/bin/bash
#
# This script runs a testng xml suite file for the in.automationtest project
# Much better to do this using Maven
# Instead of a bash script
#
# u Treat unset variables as an error when substituting.
# x Print commands and their arguments as they are executed`
set -ux


export SUITEDIR=C:/Users/efrain583/dev/WebService
LTARGET=${SUITEDIR}/target
#LOCALJAR="${LTARGET}/WebService_test.jar"
LOCALPKGDIR=${LTARGET}/test-classes/

# Create a jar file may be added in the CLASSPATH below
C:/'Program Files'/Java/jdk1.8.0_161/bin/jar cvf $LOCALJAR -C $LOCALPKGDIR .

sleep 2

# From running "mvn dependency:build-classpath" extract The CLASSPATH 
# For it to work you have use quotes in the CLASSPATH assignment  and do the export in the next line
CLASSPATH="${LOCALPKGDIR};C:\Users\efrain583\.m2\repository\org\testng\testng\6.10\testng-6.10.jar;C:\Users\efrain583\.m2\repository\com\beust\jcommander\1.48\jcommander-1.48.jar;C:\Users\efrain583\.m2\repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;C:\Users\efrain583\.m2\repository\org\apache\httpcomponents\httpclient\4.5.7\httpclient-4.5.7.jar;C:\Users\efrain583\.m2\repository\org\apache\httpcomponents\httpcore\4.4.11\httpcore-4.4.11.jar;C:\Users\efrain583\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\efrain583\.m2\repository\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;C:\Users\efrain583\.m2\repository\org\json\json\20180130\json-20180130.jar"
export CLASSPATH

echo
echo
echo $CLASSPATH
echo
echo

export SUITEDIR=C:/Users/efrain583/dev/WebService
echo
echo

java -cp ${CLASSPATH} org.testng.TestNG $SUITEDIR/testng.xml 

exit 0
