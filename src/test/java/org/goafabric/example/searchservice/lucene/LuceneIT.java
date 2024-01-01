package org.goafabric.example.searchservice.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


//https://www.baeldung.com/lucene
public class LuceneIT {

    @Test
    public void givenSearchQueryWhenFetchedDocumentThenCorrect() throws IOException {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex();

        inMemoryLuceneIndex.indexDocument("Hello world", "Some hello world");

        List<Document> documents
                = inMemoryLuceneIndex.searchIndex("myFullText", "world");

        documents.forEach(System.err::println);

        assertEquals(
                "Hello world",
                documents.get(0).get("title"));
    }


    @Test
    public void fuzzySearch() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex();

        inMemoryLuceneIndex.indexDocument("article", "Halloween Festival");
        inMemoryLuceneIndex.indexDocument("decoration", "Decorations for Halloween");

        var query = new FuzzyQuery(new Term("myFullText", "hallowen"));

        var documents = inMemoryLuceneIndex.searchIndex(query);
        assertThat(documents).isNotEmpty();
        documents.forEach(System.err::println);
    }

    //only works with single search terms, not complete name
    @Test
    public void fuzzyMueller() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex();
        inMemoryLuceneIndex.indexDocument("article", "hans müller");

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "müller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "mueller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "miller")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "meyer")))).hasSize(0);
    }

    @Test
    public void fuzzyMeyer() {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex();
        inMemoryLuceneIndex.indexDocument("article", "erich meyer");

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "meyer")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "meier")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "mayor")))).hasSize(1);

        assertThat(inMemoryLuceneIndex.searchIndex(
                new FuzzyQuery(new Term("myFullText", "müller")))).hasSize(0);
    }


    /*
    @Test
    public void fuzzyMueller2() throws ParseException {
        var inMemoryLuceneIndex = new InMemoryLuceneIndex();
        inMemoryLuceneIndex.indexDocument("article", "hans müller");

        String[] fields = {"title", "myFullText"};
        QueryParser queryParser = new MultiFieldQueryParser(fields, inMemoryLuceneIndex.getAnalyzer());
        String queryString = "hans maulwurf";
        Query query = queryParser.parse(queryString);

        List<Document> results = inMemoryLuceneIndex.searchIndex(query);

        assertThat(results).hasSize(1);
    }
    
     */
}
