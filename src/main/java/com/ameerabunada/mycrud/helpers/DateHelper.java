package com.ameerabunada.mycrud.helpers;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {

  private DateHelper() {

  }

  public static int calculateAge(Date date) {
    LocalDate birthLocalDate = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
    LocalDate currentDate = LocalDate.now();
    return Period.between(birthLocalDate, currentDate).getYears();
  }
}
