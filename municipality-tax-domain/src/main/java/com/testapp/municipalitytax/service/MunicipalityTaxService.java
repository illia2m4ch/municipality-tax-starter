package com.testapp.municipalitytax.service;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.util.DateUtil;
import com.testapp.municipalitytax.web.TaxesService;
import com.testapp.municipalitytax.web.payload.*;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityTaxService implements TaxesService {

  private final TaxesRepository taxesRepository;
  private final ConversionService conversionService;

  public MunicipalityTaxService(
      TaxesRepository taxesRepository, ConversionService conversionService) {
    this.taxesRepository = taxesRepository;
    this.conversionService = conversionService;
  }

  @Override
  public UUIDResponse addTax(AddTaxRequest addTaxRequest) {
    var tax = conversionService.convert(addTaxRequest, MunicipalityTax.class);
    var result = taxesRepository.save(tax);
    return new UUIDResponse(result.id());
  }

  @Override
  public void updateTax(UUID taxId, UpdateTaxRequest updateTaxRequest) {
    var tax = conversionService.convert(updateTaxRequest, MunicipalityTax.class);
    assert tax != null;
    taxesRepository.update(tax.withId(taxId));
  }

  @Override
  public TaxResponse findTax(String municipality, String date) {
    var result =
        taxesRepository.findByMunicipalityAndDate(
            municipality, LocalDate.parse(date, DateUtil.formatter));
    return conversionService.convert(result, TaxResponse.class);
    //    return (TaxResponse) conversionService.convert(
    //      result,
    //      TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MunicipalityTax.class)),
    //      TypeDescriptor.valueOf(TaxResponse.class)
    //    );
  }

  @Override
  public TaxListResponse getAllMunicipalityTaxes() {
    var result =
        taxesRepository.getAllMunicipalityTaxes().stream()
            .map(item -> conversionService.convert(item, FullTaxInfo.class))
            .toList();

    return new TaxListResponse(result.size(), result);
  }
}
