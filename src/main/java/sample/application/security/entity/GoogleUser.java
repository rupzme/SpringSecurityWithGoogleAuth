package sample.application.security.entity;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;

public class GoogleUser {

	@JsonProperty("id")
	private String id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("verified_email")
	private Boolean verified_email;
	@JsonProperty("name")
	private String name;
	@JsonProperty("given_name")
	private String given_name;
	@JsonProperty("family_name")
	private String family_name;
	@JsonProperty("link")
	private String link;
	@JsonProperty("picture")
	private String picture;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("locale")
	private String locale;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("verified_email")
	public Boolean getVerified_email() {
		return verified_email;
	}

	@JsonProperty("verified_email")
	public void setVerified_email(Boolean verified_email) {
		this.verified_email = verified_email;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("given_name")
	public String getGiven_name() {
		return given_name;
	}

	@JsonProperty("given_name")
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	@JsonProperty("family_name")
	public String getFamily_name() {
		return family_name;
	}

	@JsonProperty("family_name")
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	@JsonProperty("link")
	public String getLink() {
		return link;
	}

	@JsonProperty("link")
	public void setLink(String link) {
		this.link = link;
	}

	@JsonProperty("picture")
	public String getPicture() {
		return picture;
	}

	@JsonProperty("picture")
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("locale")
	public String getLocale() {
		return locale;
	}

	@JsonProperty("locale")
	public void setLocale(String locale) {
		this.locale = locale;
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
