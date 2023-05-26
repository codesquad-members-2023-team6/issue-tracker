S3_BUCKET="s3://issuetracker-bucket-web"
BUILD_FILE="deploy/web/react-build.zip"
DEST_DIR="/home/ubuntu/issueTrackerWeb"

mkdir -p ${DEST_DIR}
aws s3 cp ${S3_BUCKET}/${BUILD_FILE} .
unzip react-build.zip -d ${DEST_DIR}
rm react-build.zip

# Restart Nginx
sudo service nginx restart
