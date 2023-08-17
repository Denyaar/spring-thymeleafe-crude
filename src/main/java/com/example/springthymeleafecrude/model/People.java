/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 24/6/2023
 * Time: 00:41
 */

package com.example.springthymeleafecrude.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    @Id
    @Column(name = "Id")
    private  Integer Id;

    @Column(name = "User_Id")
    private  String User_Id;

    @Column(name = "First_Name")
    private  String First_Name;

    @Column(name = "Last_Name")
    private  String Last_Name;

    @Column(name = "Sex")
    private  String Sex;

    @Column(name = "Email")
    private  String Email;

    @Column(name = "Phone")
    private  String Phone;

    @Column(name = "Date_of_birth")
    private String Date_of_birth;

    @Column(name = "Job_Title")
    private  String Job_Title;

}
