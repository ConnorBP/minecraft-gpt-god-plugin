package net.bigyous.gptgodmc.GPT.Json;

import com.google.gson.annotations.SerializedName;

// https://ai.google.dev/api/generate-content#candidate
public class Candidate {
    @SerializedName("content")
    private Content content;

    @SerializedName("finishReason")
    private FinishReason finishReason;

    @SerializedName("safetyRatings")
    private SafetyRating[] safetyRatings;

    @SerializedName("citationMetadata")
    private CitationMetadata citationMetadata;

    @SerializedName("tokenCount")
    private int tokenCount;

    @SerializedName("avgLogprobs")
    private double avgLogprobs;

    @SerializedName("logprobsResult")
    private LogprobsResult logprobsResult;

    @SerializedName("index")
    private int index;

    // shortcut to the first model response
    public String getText() {
        return content.getParts()[0].getText();
    }

    // get the Content of the response
    public Content getContent() {
        return content;
    }

    public FinishReason getFinishReason() {
        return finishReason;
    }

    public SafetyRating[] getSafetyRatings() {
        return safetyRatings;
    }

    public CitationMetadata getCitationMetadata() {
        return citationMetadata;
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public double getAvgLogprobs() {
        return avgLogprobs;
    }

    public LogprobsResult getLogprobsResult() {
        return logprobsResult;
    }

    public int getIndex() {
        return index;
    }
}