package com.kd.aws.s3.demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class CreateBucket {

	public static void main(String[] args) {

		AWSCredentials credentials = new BasicAWSCredentials("accessKey",
				"secretKey");

		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();

		String bucketName = "kd-march19";

		try {
			s3.createBucket(bucketName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
