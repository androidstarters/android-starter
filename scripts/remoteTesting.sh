#!/bin/bash
set -xe

report_location=$1

if [ -n "${GCLOUD_SERVICE_KEY+1}" ]; then
    sudo pip install -U crcmod
    # Put our service key into a file for use later
    echo $GCLOUD_SERVICE_KEY > ${HOME}/client-secret.json
    
    GCLOUD=`which gcloud`

	# Activate gcloud with our key and update and install relevant components
    $GCLOUD auth activate-service-account $GCLOUD_SERVICE_ACCOUNT --key-file $HOME/client-secret.json
    sudo $GCLOUD --quiet components update
    sudo $GCLOUD --quiet components install beta

    GCLOUD_OUTPUT_LOG=gcloudLog.txt

    # Run our tests in the cloud and store the output temporarily in a file. Exit if any part errors
    $GCLOUD beta test android run --type instrumentation --uri --app app/build/outputs/apk/app-debug.apk --test app/build/outputs/apk/app-debug-androidTest.apk --orientations portrait --project $GCLOUD_PROJECT_ID 2>&1 | tee $GCLOUD_OUTPUT_LOG ; ( exit ${PIPESTATUS[0]} )

    # Grab the bucket ID for where our reports and artefacts are logged
    BUCKET_ID=`cat $GCLOUD_OUTPUT_LOG | sed -n -E 's#^.+test-lab-(.+)/.+#\1#p'`

    echo $BUCKET_ID

    TEST_OUTPUT_FOLDER=testResults
    mkdir $TEST_OUTPUT_FOLDER
    # Grab remote reports and artefacts and store locally
    gsutil -m cp -R -U gs://test-lab-$BUCKET_ID $TEST_OUTPUT_FOLDER || true

    # Copy reports and artefacts to relevant locations
    mkdir $report_location/cloudTesting
    cp -r $TEST_OUTPUT_FOLDER $report_location/cloudTesting

    # Cleanup temporary files
    rm $GCLOUD_OUTPUT_LOG
    rm -r $TEST_OUTPUT_FOLDER
else 
    echo "GCLOUD_SERVICE_KEY environment variable not set. This is required to run remote testing on Firebase."
fi
