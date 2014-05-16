package com.wx.solr;

import com.wx.pojo.Book;
import com.wx.server.SolrjServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Shawn on 2014/5/16.
 */
public class SolrjTest {
    @Test
    public void deleteAll() throws IOException, SolrServerException {
        SolrjServer.server.deleteByQuery("*:*");// CAUTION: deletes everything!
    }
    @Test
    public void addDocs() throws IOException, SolrServerException {
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField( "id", "id2", 1.0f );
        doc2.addField("name", "doc2", 1.0f);
        doc2.addField("price", 20);
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );
        docs.add( doc2 );
        SolrjServer.server.add( docs );
        SolrjServer.server.commit();
    }

    /**
     * immediately commit after adding documents
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void addCommit() throws IOException, SolrServerException {
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField( "id", "id2", 1.0f );
        doc2.addField("name", "doc2", 1.0f);
        doc2.addField("price", 20);
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );
        docs.add( doc2 );
        UpdateRequest req = new UpdateRequest();
        req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
        req.add( docs );
        UpdateResponse rsp = req.process(SolrjServer.server);
    }

    @Test
    public void addBean() throws IOException, SolrServerException {
        Book book = new Book("101l" ,"solr-study", "this is a  solr study book", "shawn");
        SolrjServer.server.addBean(book);
        Book book1 = new Book("102l" ,"solr-study2", "this is a  solr study book2", "shawn");
        SolrjServer.server.addBean(book1, 0);//what is commitWithinMs mean ???
        SolrjServer.server.commit();
    }
    @Test
    public void query() throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery( "*:*" );
        //query.addSortField("price", SolrQuery.ORDER.asc);
        QueryResponse rsp = SolrjServer.server.query(query);
        SolrDocumentList list  = rsp.getResults();
        //rsp.getBeans(Book.class);
        System.out.println(list);
    }
    @Test
    public void advanceQuery() throws SolrServerException {
        SolrQuery solrQuery = new  SolrQuery().
                setQuery("book2").
                setFacet(true).
                setFacetMinCount(1).
                setFacetLimit(8).
                addFacetField("description").
                addFacetField("author");

        solrQuery.setHighlight(true).setHighlightSnippets(1); //set other params as needed
        solrQuery.setParam("hl.fl", "name description");
        QueryResponse rsp = SolrjServer.server.query(solrQuery);
        System.out.println(rsp.getResults());
        Map<String, Map<String,List<String>>> map = rsp.getHighlighting();
        System.out.println(map);
    }


}
