package com.kd.aws.s3.demo;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

public class ListAllS3Buckets {

	public static void main(String[] args) {
		AmazonS3 s3 = AWSUtility.getS3CredentialObj();
		List<Bucket> list = s3.listBuckets();

		System.out.println("No of buckets : "+list.size());
		list.stream().forEach(i -> System.out.println(i));
		
		
	}

}
