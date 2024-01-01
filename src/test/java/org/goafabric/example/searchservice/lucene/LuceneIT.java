package org.goafabric.example.searchservice.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


//https://www.baeldung.com/lucene
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


    @Test
    public void fuzzySearch() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex(new ByteBuffersDirectory(), new StandardAnalyzer());

        inMemoryLuceneIndex.indexDocument("article", "Halloween Festival");
        inMemoryLuceneIndex.indexDocument("decoration", "Decorations for Halloween");

        var query = new FuzzyQuery(new Term("body", "hallowen"));

        var documents = inMemoryLuceneIndex.searchIndex(query);
        assertThat(documents).isNotEmpty();
        documents.forEach(System.err::println);
    }

    //only works with single search terms, not complete name
    @Test
    public void fuzzyMueller() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex(new ByteBuffersDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("article", "hans müller");

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "müller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "mueller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "miller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "meyer")))).hasSize(0);
    }

    @Test
    public void fuzzyMeyer() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex(new ByteBuffersDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("article", "erich meyer");

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "meyer")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "meier")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "mayor")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("body", "müller")))).hasSize(0);
    }

}
