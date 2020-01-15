package com.octo.reminderservice.notification.prod;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.octo.reminderservice.notification.SnsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class SnsClient {

    private static String awsRegion;

    private static AmazonSNSClient amazonSNSClient;

    @Value("${sns.region}")
    public void setRegion(String region) {
        awsRegion = region;
    }

    public static AmazonSNSClient getSNSClientInstance() {

        if (amazonSNSClient == null) {

            synchronized (SnsClient.class) {
                if (amazonSNSClient == null) {
                    amazonSNSClient = (AmazonSNSClient) AmazonSNSClientBuilder
                            .standard()
                            .withCredentials(new InstanceProfileCredentialsProvider(false))
                            .withRegion(Regions.fromName(awsRegion))
                            .build();
                }
            }
        }
        return amazonSNSClient;
    }

    public static void sendSms(String message) {

        AmazonSNSClient amazonSNSClient = getSNSClientInstance();

        SnsUtils.sendSMSMessage(amazonSNSClient, message);

    }

}
