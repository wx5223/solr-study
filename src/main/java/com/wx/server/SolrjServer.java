package com.wx.server;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * Created by Shawn on 2014/5/16.
 */
public class SolrjServer {
    private static String url = "http://localhost:8983/solr";
    /*
      HttpSolrServer is thread-safe and if you are using the following constructor,
      you *MUST* re-use the same instance for all requests.  If instances are created on
      the fly, it can cause a connection leak. The recommended practice is to keep a
      static instance of HttpSolrServer per solr server url and share it for all requests.
      See https://issues.apache.org/jira/browse/SOLR-861 for more details
    */
    public static final SolrServer server = new HttpSolrServer( url );
    /*private static String solrHome = SolrjServer.class.getClassLoader().getResource("solr").getPath();//"D:\\solr-4.7.1\\solr";
    private static File configFile = new File( solrHome, "solr.xml" );
    private static CoreContainer coreContainer = CoreContainer.createAndLoad(solrHome, configFile);
    public static final org.apache.solr.client.solrj.SolrjServer server = new EmbeddedSolrServer(coreContainer, "collection1");*/

}
