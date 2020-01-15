package com.octo.reminderservice.notification.dev;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.octo.reminderservice.notification.SnsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevSnsClient {

    private static String awsRegion;

    private static String accessKey;

    private static String secretKey;

    private static AmazonSNSClient amazonSNSClient;

    @Value("${sns.region}")
    public void setRegion(String region) {
        awsRegion = region;
    }

    @Value("${access.key}")
    public void setAccessKey(String key) {
        accessKey = key;
    }

    @Value("${secret.key}")
    public void setSecretKey(String key) {
        secretKey = key;
    }

    public static AmazonSNSClient getSNSClientInstance() {

        if (amazonSNSClient == null) {

            synchronized (DevSnsClient.class) {
                if (amazonSNSClient == null) {
                    BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
                    amazonSNSClient = (AmazonSNSClient) AmazonSNSClientBuilder
                            .standard()
                            .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                            .withRegion(Regions.fromName(awsRegion))
                            .build();
                }
            }
        }
        return amazonSNSClient;
    }

    public static void sendSms(String message) {

        AmazonSNSClient amazonSNSClient = getSNSClientInstance();

        //TODO uncomment to send sms
        //SnsUtils.sendSMSMessage(amazonSNSClient, message);

    }


}
