package com.kd.aws.s3.demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWSUtility {

	public static AmazonS3 getS3CredentialObj() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIWTS3NV7F23VRCBQ",
				"6hO4bVcjOhaffahWB0sWhxfh+cSxcf9pB5vhOMM9");
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
		return s3;
	}

}
