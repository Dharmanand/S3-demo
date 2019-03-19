package com.kd.aws.s3.demo;

import com.amazonaws.services.s3.AmazonS3;

public class CreateBucket {

	public static void main(String[] args) {

		final AmazonS3 s3 = AWSUtility.getS3CredentialObj();

		String bucketName = "kd-march19";

		try {
			s3.createBucket(bucketName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
