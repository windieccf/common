package com.nus.adqs.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.nus.adqs.dataaccess.persistence.SystemSetupHome;


public class ElasticUtil {
	
	public static String ELASTIC_URL = "http://localhost:9200/";
	
	static{
		ElasticUtil.ELASTIC_URL = "http://"+SystemSetupHome.getParam().getElasticHost()+":"+SystemSetupHome.getParam().getElasticMainPort()+"/";
	}
	
	private static ElasticUtil instance;
	public static ElasticUtil getInstance(){
		
		if( ElasticUtil.instance  == null)
			ElasticUtil.instance = new ElasticUtil();
		
		return ElasticUtil.instance;
	} 
	
	
	private TransportClient esClient;
	public TransportClient getEsClient() {return esClient;}
	
	private ElasticUtil(){
		esClient = new TransportClient();//localhost , 9300
		esClient.addTransportAddress(new InetSocketTransportAddress(SystemSetupHome.getParam().getElasticHost(), SystemSetupHome.getParam().getElasticApiPortAsInteger()));
	}
	

}
