package learn.catch_ride.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import learn.catch_ride.config.AwsProperties;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AwsService {

    private final AwsProperties awsProperties;
    private final AmazonS3 amazonS3;

    public AwsService(AwsProperties awsProperties, AmazonS3 amazonS3) {
        this.awsProperties = awsProperties;
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(String key, File file) {
        PutObjectRequest request = new PutObjectRequest(
                awsProperties.getS3Bucket(),
                key,
                file
        );

        amazonS3.putObject(request);
    }

    public String getPublicUrl(String key) {
        return "https://" + awsProperties.getS3Bucket() + ".s3.amazonaws.com/" + key;
    }
}
