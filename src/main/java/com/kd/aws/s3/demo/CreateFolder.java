package com.kd.aws.s3.demo;

import java.io.File;

import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;

public class CreateFolder {

	public static void main(String[] args) {
		
		String dirPath = "E:/AWS-s3/demo-files";
		String bucketName = "kd-march19";
		String keyPrefix = "kk101";
		boolean recursive = false;
		
		createFolderUsingTransferManager(bucketName, keyPrefix, dirPath, recursive);
		
	}

	public static void createFolderUsingTransferManager(String bucketName, String keyPrefix, String dirPath, boolean recursive) {
		TransferManager xferMgr = new TransferManager(AWSUtility.getS3CredentialObj());
		

		MultipleFileUpload xfer = xferMgr.uploadDirectory(bucketName, keyPrefix, new File(dirPath), recursive);

		try {
			xfer.waitForCompletion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		xferMgr.shutdownNow();
	}
	
}
