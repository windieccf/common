package com.nus.adqs.dataaccess.scalar;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SystemParameter implements Serializable{

	
	private String batchCrawlerSavePath = "C:/workbench/project/development/nus/sample/temp.pdf";
	public String getBatchCrawlerSavePath() {return batchCrawlerSavePath;}
	public void setBatchCrawlerSavePath(String batchCrawlerSavePath) {this.batchCrawlerSavePath = batchCrawlerSavePath;}
	
	private String batchCrawlerDumpPath = "C:/workbench/project/development/nus/sample/dump/";
	public String getBatchCrawlerDumpPath() {return batchCrawlerDumpPath;}
	public void setBatchCrawlerDumpPath(String batchCrawlerDumpPath) {this.batchCrawlerDumpPath = batchCrawlerDumpPath;}
	
	private String batchCrawlerDocumentConvertPath = "C:/Program Files (x86)/LibreOffice 4/";
	public String getBatchCrawlerDocumentConvertPath() {return batchCrawlerDocumentConvertPath;}
	public void setBatchCrawlerDocumentConvertPath(	String batchCrawlerDocumentConvertPath) {this.batchCrawlerDocumentConvertPath = batchCrawlerDocumentConvertPath;}
	
	private String batchCrawlerImageConvertPath = "C:/workbench/project/development/nus/mupdf-1.3-windows/mudraw.exe";
	public String getBatchCrawlerImageConvertPath() {return batchCrawlerImageConvertPath;}
	public void setBatchCrawlerImageConvertPath(String batchCrawlerImageConvertPath) {this.batchCrawlerImageConvertPath = batchCrawlerImageConvertPath;}
	
	private String batchCrawlerImageOutputPath = "C:/workbench/project/development/nus/sample/dump/dump.png";
	public String getBatchCrawlerImageOutputPath() {return batchCrawlerImageOutputPath;}
	public void setBatchCrawlerImageOutputPath(String batchCrawlerImageOutputPath) {this.batchCrawlerImageOutputPath = batchCrawlerImageOutputPath;}
	
	
	private String emailHost = "smtp.gmail.com";
	public String getEmailHost() {return emailHost;}
	public void setEmailHost(String emailHost) {this.emailHost = emailHost;}

	private String emailPort = "587";
	public String getEmailPort() {return emailPort;}
	public void setEmailPort(String emailPort) {this.emailPort = emailPort;}

	private String emailAuth = "true";
	public String getEmailAuth() {return emailAuth;}
	public void setEmailAuth(String emailAuth) {this.emailAuth = emailAuth;}
	
	private String emailSender = "simadqs@gmail.com";
	public String getEmailSender() {return emailSender;}
	public void setEmailSender(String emailSender) {this.emailSender = emailSender;}
	
	private String emailUsername = "simadqs@gmail.com";
	public String getEmailUsername() {return emailUsername;}
	public void setEmailUsername(String emailUsername) {this.emailUsername = emailUsername;}
	
	private String emailPassword = "simadqspassword";
	public String getEmailPassword() {return emailPassword;}
	public void setEmailPassword(String emailPassword) {this.emailPassword = emailPassword;}
	
	private String taskNotificationDue = "7";
	public String getTaskNotificationDue() {return taskNotificationDue;}
	public void setTaskNotificationDue(String taskNotificationDue) {this.taskNotificationDue = taskNotificationDue;}
	public long getTaskNotificationDueAsLong() {return Long.parseLong(taskNotificationDue);}
	
	
	private String elasticHost = "localhost";
	public String getElasticHost() {return elasticHost;}
	public void setElasticHost(String elasticHost) {this.elasticHost = elasticHost;}
	
	private String elasticMainPort = "9200";
	public String getElasticMainPort() {return elasticMainPort;}
	public void setElasticMainPort(String elasticMainPort) {this.elasticMainPort = elasticMainPort;}
	
	private String elasticApiPort = "9300";
	public String getElasticApiPort() {return elasticApiPort;}
	public void setElasticApiPort(String elasticApiPort) {this.elasticApiPort = elasticApiPort;}
	public int getElasticApiPortAsInteger(){return Integer.parseInt(this.elasticApiPort);}
	
	private String batchCrawlerWebDumpPath = "/data/crawl/root";
	public String getBatchCrawlerWebDumpPath() {return batchCrawlerWebDumpPath;}
	public void setBatchCrawlerWebDumpPath(String batchCrawlerWebDumpPath) {this.batchCrawlerWebDumpPath = batchCrawlerWebDumpPath;}
	
	private String batchCrawlerWebDocDumpPath = "C:/data/crawl/doc";
	public String getBatchCrawlerWebDocDumpPath() {return batchCrawlerWebDocDumpPath;}
	public void setBatchCrawlerWebDocDumpPath(String batchCrawlerWebDocDumpPath) {this.batchCrawlerWebDocDumpPath = batchCrawlerWebDocDumpPath;}
	
	private String rowPerPage = "30";
	public String getRowPerPage() {return rowPerPage;}
	public void setRowPerPage(String rowPerPage) {this.rowPerPage = rowPerPage;}
	public int getRowPerPageasInt(){return (Integer.parseInt(this.rowPerPage));}
	
	private String systemAddress="http://10.250.31.45:9000";
	public String getSystemAddress() {return systemAddress;}
	public void setSystemAddress(String systemAddress) {this.systemAddress = systemAddress;}
	
	
	
	
	
	
	
	/*private static final String SAVE_PATH = "C:/workbench/project/development/nus/sample/temp.pdf";
	private static final String DUMP_PATH = "C:/workbench/project/development/nus/sample/dump/";
	private static final String DOC_CONVERTER_PATH = "C:/Program Files (x86)/LibreOffice 4/";
	private static final String IMAGE_CONVERTER_PATH = "C:/workbench/project/development/nus/mupdf-1.3-windows/mudraw.exe";
	private static final String IMAGE_OUTPUT_PATH = DUMP_PATH + "dump.png";*/
	
}
