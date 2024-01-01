package org.goafabric.example.searchservice.lucene;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LuceneIT {

    @Test
    public void givenSearchQueryWhenFetchedDocumentThenCorrect() throws IOException {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex(new ByteBuffersDirectory(), new StandardAnalyzer());
        //var inMemoryLuceneIndex  = new InMemoryLuceneIndex(new NIOFSDirectory(Path.of("/Users/andreas/Downloads/lucene")), new StandardAnalyzer());

        inMemoryLuceneIndex.indexDocument("Hello world", "Some hello world");

        List<Document> documents
                = inMemoryLuceneIndex.searchIndex("body", "world");

        documents.forEach(System.err::println);

        assertEquals(
                "Hello world",
                documents.get(0).get("title"));
    }

}
