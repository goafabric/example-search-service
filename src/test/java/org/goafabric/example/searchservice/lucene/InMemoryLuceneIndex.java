package org.goafabric.example.searchservice.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryLuceneIndex {

    private final Directory memoryIndex;
    private final Analyzer analyzer;


    public InMemoryLuceneIndex() {
        super();
        this.memoryIndex = new ByteBuffersDirectory();
        this.analyzer = new StandardAnalyzer(); //new NIOFSDirectory(Path.of("/Users/andreas/Downloads/lucene")
    }

    /**
     *
     * @param title
     * @param body
     */
    public void indexDocument(String title, String body) {
        try (var writer = new IndexWriter(memoryIndex, new IndexWriterConfig(analyzer))) {
            var document = new Document();
            document.add(new TextField("title", title, Field.Store.YES));
            document.add(new TextField("body", body, Field.Store.YES));
            document.add(new SortedDocValuesField("title", new BytesRef(title)));

            writer.addDocument(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Document> searchIndex(String inField, String queryString) {
        Query query = null;
        try {
            query = new QueryParser(inField, analyzer).parse(queryString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return searchIndex(query);
    }


    public List<Document> searchIndex(Query query) {
        try {
            var indexReader = DirectoryReader.open(memoryIndex);
            var searcher = new IndexSearcher(indexReader);
            var topDocs = searcher.search(query, 10);

            List<Document> documents = new ArrayList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }

            return documents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        public void deleteDocument(Term term) {
        try {
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            IndexWriter writter = new IndexWriter(memoryIndex, indexWriterConfig);
            writter.deleteDocuments(term);
            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> searchIndex(Query query, Sort sort) {
        try {
            IndexReader indexReader = DirectoryReader.open(memoryIndex);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            TopDocs topDocs = searcher.search(query, 10, sort);
            List<Document> documents = new ArrayList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }

            return documents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

     */



}
