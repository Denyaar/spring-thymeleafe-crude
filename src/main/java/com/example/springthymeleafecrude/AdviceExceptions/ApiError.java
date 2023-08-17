/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 8/3/23
 * Time: 10:23 PM
 */

package com.example.springthymeleafecrude.AdviceExceptions;

import javax.persistence.Entity;
import java.util.Date;

public class ApiError {
    private  int errorCode;
    private  String errorDescription;

    private Date date;

    public ApiError(int errorCode, String errorDescription, Date date) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.date = date;
    }

    public int errorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
