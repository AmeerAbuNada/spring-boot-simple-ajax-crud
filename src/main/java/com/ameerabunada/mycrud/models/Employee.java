package com.ameerabunada.mycrud.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.ameerabunada.mycrud.helpers.DateHelper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  @NotBlank(message = "Please Enter the Employee's Full Name.")
  private String name;

  @Column(name = "email")
  @NotBlank(message = "Please Enter the Employee's Email Address.")
  @Email(message = "Please Enter a Valid Email Address.")
  private String email;

  @Column(name = "birthdate")
  @DateTimeFormat(pattern = "yyyy-dd-MM")
  @NotNull(message = "Please enter the Employee's Birthdate.")
  @PastOrPresent(message = "The Birthdate must be in the past or present.")
  private Date birthdate;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  public Employee() {

  }

  public Employee(Long id, String name, Date birthdate) {
    this.id = id;
    this.name = name;
    this.birthdate = birthdate;
  }

  public Employee(Long id, String name, Date birthdate, Date createdAt) {
    this.id = id;
    this.name = name;
    this.birthdate = birthdate;
    this.createdAt = createdAt;
  }

  public String getParsedBirthdate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(this.birthdate);
  }

  public int getAge() {
    return DateHelper.calculateAge(this.birthdate);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", birthdate=" + birthdate + ", createdAt="
        + createdAt + "]";
  }

}
