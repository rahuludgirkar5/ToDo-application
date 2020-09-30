package com.allobjects;

public class User 
{
	
	int userId;
	String firstname;
	String lastname;
	String gender;
	String dob;
	String email;
	String password;
	String status;
	
	public User()
	{
		super();
		
	}
	
	public User(int userId,String firstname, String lastname, String gender, String dob, String email, String password,String status) {
		super();
		this.userId=userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.status=status;
	}
	
	
	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getFirstname()
	{
		return firstname;
	}
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	public String getLastname() 
	{
		return lastname;
	}
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getDob() 
	{
		return dob;
	}
	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString() 
	{
		return "User [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", dob=" + dob + ", email=" + email + ", password=" + password + ", status=" + status + "]";
	}
	
	
}
