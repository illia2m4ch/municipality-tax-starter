package com.testapp.municipalitytax.web.payload;

import com.testapp.municipalitytax.web.validation.date.DatePattern;
import com.testapp.municipalitytax.web.validation.enumvalue.EnumValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public record AddTaxRequest(
    @NotEmpty(message = "Specify municipality")
    String municipality,
    @Positive(message = "Tax must be positive")
    Double tax,
    @DatePattern(pattern = "yyyy.MM.dd")
    String startDate,
    @NotBlank(message = "Specify schedule")
    @EnumValue(anyOf = {"YEARLY", "MONTHLY", "WEEKLY", "DAILY"}, message = "Invalid schedule value")
    String schedule
) {}
