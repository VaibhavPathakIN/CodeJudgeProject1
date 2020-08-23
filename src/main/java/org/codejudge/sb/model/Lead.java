package org.codejudge.sb.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "lead_info")
public class Lead {

	@Id
	@JsonProperty("id")
	private long _id;

	@JsonProperty("first_name")
	@NotNull(message = "First Name is mandatory")
	private String firstName;

	@JsonProperty("last_name")
	@NotNull(message = "Last Name is mandatory")
	private String lastName;

	@JsonProperty("mobile")
	@NotNull(message = "Mobile Number is mandatory")
	@Length(max = 10)
	@Indexed(unique = true)
	private String mobile;

	@JsonProperty("email")
	@NotNull(message = "Email is mandatory")
	@Indexed(unique = true)
	private String email;

	@JsonProperty("location_type")
	@NotNull(message = "Location type is mandatory")
	private LocationType locationType;

	@JsonProperty("location_string")
	@NotNull(message = "Location String is mandatory")
	private String locationString;

	@JsonProperty("status")
	@NotNull(message = "Status should be provided")
	private Status status;

	@JsonProperty("communication")
	private String communication;

	@JsonIgnore
	@LastModifiedDate
	private Date dateModified;
	@JsonIgnore
	private boolean isDeleted = false;

	public long get_id() {
		return _id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public String getLocationString() {
		return locationString;
	}

	public Status getStatus() {
		return status;
	}

	public String getCommunication() {
		return communication;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Lead [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
				+ ", email=" + email + ", locationType=" + locationType + ", locationString=" + locationString
				+ ", status=" + status + ", communication=" + communication + ", dateModified=" + dateModified
				+ ", isDeleted=" + isDeleted + "]";
	}

}
