package com.kd.aws.s3.demo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWSUtility {

	private static Properties properties = new Properties();
	static {
		try {
			properties.load(new FileReader("D:/lara-soft/installers/windows/aws/credentials.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AmazonS3 getS3CredentialObj() {
		AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("accessKey"),
				properties.getProperty("secretKey"));

		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
		return s3;
	}

}
