#!/bin/bash
set -xe

# Get the location for report directory from 1st param or use current directory
if [ ! -z "$1" ]; then
    report_location=$1
else
    report_location=`pwd`
fi

./gradlew --no-daemon --info clean
./gradlew --no-daemon --info checkstyle -PdisablePreDex
./gradlew --no-daemon --info build countDebugDexMethods -PdisablePreDex

# If we have a service key for the GCloud Cli then use remote testing otherwise run local tests
if [ -n "${GCLOUD_SERVICE_KEY+1}" ]; then
    ./gradlew --no-daemon --info assembleAndroidTest -PdisablePreDex
    scripts/remoteTesting.sh $report_location
else
    ./gradlew --no-daemon --info connectedAndroidTest -PdisablePreDex
    mkdir $report_location/connectedAndroidTest
    cp -r app/build/reports/androidTests/connected $report_location/connectedAndroidTest
fi
