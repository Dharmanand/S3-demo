package com.kd.aws.s3.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class ListAllS3Buckets {

	private static AmazonS3 s3 = AWSUtility.getS3CredentialObj();
	private static String bucketName = "kd-march19";

	static {
		if (new File("src/main/resources/download").exists()) {
			try {
				FileUtils.forceDeleteOnExit(new File("src/main/resources/download"));
				FileUtils.forceMkdir(new File("src/main/resources/download"));
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {

		List<Bucket> list = s3.listBuckets();

		System.out.println("No of buckets : " + list.size());
		list.stream().forEach(i -> System.out.println(i));

		int year = 2019;
		int month = 2; // January
		int date = 26;

		Calendar cal = Calendar.getInstance();
		cal.clear();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, date);

		Date date1 = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		listAllFilesOnDate(bucketName, date1, date2);
	}

	public static void listAllFilesUnderBucket(String bucketName) {
		ListObjectsV2Result result = s3.listObjectsV2(bucketName);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			System.out.println("* getLastModified * " + os.getLastModified());
			System.out.println("* getKey * " + os.getKey());
		}
	}

	public static void listAllFilesOnDate(String bucketName, Date date1, Date date2) throws Exception {
		ListObjectsV2Result result = s3.listObjectsV2(bucketName);
		List<S3ObjectSummary> objects = result.getObjectSummaries();

		S3Object fetchFile = null;
		BufferedInputStream i = null;
		InputStream objectData = null;

		for (S3ObjectSummary os : objects) {
			if (os.getLastModified().after(date1) && os.getLastModified().before(date2)) {
				System.out.println("* getLastModified * " + os.getLastModified());
				System.out.println("* getKey * " + os.getKey());

				fetchFile = s3.getObject(new GetObjectRequest(bucketName, os.getKey()));
				i = new BufferedInputStream(fetchFile.getObjectContent());
				objectData = fetchFile.getObjectContent();
				// location to local path
				Files.copy(objectData,
						new File("src/main/resources/download/" + os.getKey().split("kk101")[1]).toPath());
				i.close();
				objectData.close();
				fetchFile.close();
			}

		}
	}

}
