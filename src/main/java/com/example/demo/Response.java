package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"twitter",
"facebook",
"instagram"
})
public class Response {

@JsonProperty("twitter")
private List<Twitter> twitter = null;
@JsonProperty("facebook")
private List<Facebook> facebook = null;
@JsonProperty("instagram")
private List<Instagram> instagram = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("twitter")
public List<Twitter> getTwitter() {
return twitter;
}

@JsonProperty("twitter")
public void setTwitter(List<Twitter> twitter) {
this.twitter = twitter;
}

@JsonProperty("facebook")
public List<Facebook> getFacebook() {
return facebook;
}

@JsonProperty("facebook")
public void setFacebook(List<Facebook> facebook) {
this.facebook = facebook;
}

@JsonProperty("instagram")
public List<Instagram> getInstagram() {
return instagram;
}

@JsonProperty("instagram")
public void setInstagram(List<Instagram> instagram) {
this.instagram = instagram;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
