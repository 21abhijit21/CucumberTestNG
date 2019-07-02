package com.prudential.common.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * Class for Zip methods
 * @author Abhijit Bhattacharyya
 *
 */
public class FileZipper{	  	 	 

	/**
	 * To Zip the Directory.
	 * @param srcFolder
	 * @param destZipFile
	 * @throws Exception
	 */
	 public static void zipDir(String srcFolder, String destZipFile) {
	    ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;
	
	    try {
			fileWriter = new FileOutputStream(destZipFile);
		} catch (FileNotFoundException e) {
			Logger.logConsoleMessage("Failed to open file: " + destZipFile);
			e.printStackTrace();
		}
	    zip = new ZipOutputStream(fileWriter);
	
	    try {
			addFolderToZip("", srcFolder, zip);
		} catch (Exception e) {
			Logger.logConsoleMessage("Failed to zip folder: " + srcFolder);
			e.printStackTrace();
		}
	    try {
			zip.flush();
		} catch (IOException e) {
			Logger.logConsoleMessage("Failed to flush the stream for zip file.");
			e.printStackTrace();
		}
	    try {
			zip.close();
		} catch (IOException e) {
			Logger.logConsoleMessage("Failed to close the stream for zip file.");
			e.printStackTrace();
		}
	  }
	  
	 
	  /**
	   * To add File to the Directory.
	   * @param path
	   * @param srcFile
	   * @param zip
	   * @throws Exception
	   */
	  private static void addFileToZip(String path, String srcFile, ZipOutputStream zip) {

	    File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	      try {
			addFolderToZip(path, srcFile, zip);
		} catch (Exception e) {
			Logger.logConsoleMessage("Failed to add folder to zip file.");
			e.printStackTrace();
		}
	    } 
	    else 
	    {
	      byte[] buf = new byte[1024];
	      int len;
	      FileInputStream in = null;
		try {
			in = new FileInputStream(srcFile);
		} catch (FileNotFoundException e1) {
			Logger.logConsoleMessage("Failed to open the " + srcFile +" file.");
			e1.printStackTrace();
		}
	      try {
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
		} catch (IOException e) {
			Logger.logConsoleMessage("Failed to create zip file.");
			e.printStackTrace();
		}
	      try {
			while ((len = in.read(buf)) > 0) 
			  {
			    zip.write(buf, 0, len);	        
			  }
		} catch (IOException e) {
			Logger.logConsoleMessage("Failed to create zip file.");
			e.printStackTrace();
		}
	      try {
			in.close();
		} catch (IOException e) {
			Logger.logConsoleMessage("Failed to close file.");
			e.printStackTrace();
		}
	    }
	  }
	  
	  
	  /**
	   * To add Folder to the Directory.
	   * @param path
	   * @param srcFolder
	   * @param zip
	   * @throws Exception
	   */
	  private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) {
	    File folder = new File(srcFolder);

	    for (String fileName : folder.list()) {
	      if (path.equals("")) 
	      {
	        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
	      } 
	      else 
	      {
	        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
	      }
	    }
	  }	  

	}