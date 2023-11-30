package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.util.DateUtil;
import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddTaxRequestToMunicipalityTaxConverter
    implements Converter<AddTaxRequest, MunicipalityTax> {

  @Override
  public MunicipalityTax convert(AddTaxRequest source) {
    return new MunicipalityTax(
        null,
        source.municipality(),
        source.tax(),
        LocalDate.parse(source.startDate(), DateUtil.formatter),
        Schedule.valueOf(source.schedule().toUpperCase()));
  }
}
