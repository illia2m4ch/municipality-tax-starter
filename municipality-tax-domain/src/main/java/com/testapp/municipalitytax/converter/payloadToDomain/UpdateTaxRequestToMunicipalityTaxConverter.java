package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.util.DateUtil;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateTaxRequestToMunicipalityTaxConverter
    implements Converter<UpdateTaxRequest, MunicipalityTax> {

  @Override
  public MunicipalityTax convert(UpdateTaxRequest source) {
    return new MunicipalityTax(
        null,
        null,
        source.tax(),
        LocalDate.parse(source.startDate(), DateUtil.formatter),
        Schedule.valueOf(source.schedule().toUpperCase()));
  }
}
