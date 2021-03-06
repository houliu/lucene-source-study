package com.lg.lucene.analysis.standard;

import com.lg.lucene.analysis.Analyzer;
import com.lg.lucene.analysis.LowerCaseFilter;
import com.lg.lucene.analysis.StopFilter;
import com.lg.lucene.analysis.TokenStream;

import java.io.Reader;
import java.util.Hashtable;

public class StandardAnalyzer extends Analyzer {
    private Hashtable stopTable;

    public static final String[] STOP_WORDS = {
            "a", "and", "are", "as", "at", "be", "but", "by",
            "for", "if", "in", "into", "is", "it",
            "no", "not", "of", "on", "or", "s", "such",
            "t", "that", "the", "their", "then", "there", "these",
            "they", "this", "to", "was", "will", "with"
    };

    public StandardAnalyzer(){
        this(STOP_WORDS);
    }

    public StandardAnalyzer(String[] stopWords){
        stopTable = StopFilter.makeStopTable(stopWords);
    }

    public final TokenStream tokenStream(String fieldName,Reader reader){
        TokenStream result = new StandardTokenizer(reader);
        //链式构造
        result = new LowerCaseFilter(result);
        result = new StopFilter(result,stopTable);
        return result;
    }


}
