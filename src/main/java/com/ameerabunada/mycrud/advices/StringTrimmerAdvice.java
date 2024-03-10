package com.ameerabunada.mycrud.advices;

import java.beans.PropertyEditorSupport;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class StringTrimmerAdvice {
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

  private static class StringTrimmerEditor extends PropertyEditorSupport {
    private final boolean emptyAsNull;

    public StringTrimmerEditor(boolean emptyAsNull) {
      this.emptyAsNull = emptyAsNull;
    }

    @Override
    public void setAsText(String text) {
      if (text == null) {
        setValue(null);
      } else {
        String trimmed = text.trim().replaceAll("\\s+", " ");
        setValue((emptyAsNull && trimmed.isEmpty()) ? null : trimmed);
      }
    }
  }
}
