package com.kd.aws.s3.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class ReadS3File {

	public static void main(String[] args) {
		AmazonS3 s3 = AWSUtility.getS3CredentialObj();

		String bucketName = "kd-march19";
		String keyName = "kt.txt";

		S3Object object = s3.getObject(new GetObjectRequest(bucketName, keyName));
		InputStream inputStream = object.getObjectContent();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
