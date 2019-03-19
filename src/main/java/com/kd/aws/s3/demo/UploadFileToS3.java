package com.kd.aws.s3.demo;

import java.io.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;

public class UploadFileToS3 {

	public static void main(String[] args) {
		AmazonS3 s3 = AWSUtility.getS3CredentialObj();
		String bucketName = "kd-march19";
		String keyName = "kt.txt";
		String filePath = "C:/Users/Kumar/Desktop/New folder (2)/aws-all/demo-files/abc.txt";

		try {
			s3.putObject(bucketName, keyName, new File(filePath));
		} catch (AmazonServiceException e) {
			System.out.println(e.getErrorMessage());
		}
	}
}
