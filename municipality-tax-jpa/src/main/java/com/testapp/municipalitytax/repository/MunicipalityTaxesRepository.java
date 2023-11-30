package com.testapp.municipalitytax.repository;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.entity.QTaxEntity;
import com.testapp.municipalitytax.entity.TaxEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

@Component
public class MunicipalityTaxesRepository implements TaxesRepository {

  private final TaxesJpaRepository taxesJpaRepository;
  private final ConversionService conversionService;

  public MunicipalityTaxesRepository(
      TaxesJpaRepository taxesJpaRepository, ConversionService conversionService) {
    this.taxesJpaRepository = taxesJpaRepository;
    this.conversionService = conversionService;
  }

  @Override
  public MunicipalityTax save(MunicipalityTax municipalityTax) {
    var requestEntity = conversionService.convert(municipalityTax, TaxEntity.class);
    assert requestEntity != null;
    var entity = taxesJpaRepository.save(requestEntity);
    return conversionService.convert(entity, MunicipalityTax.class);
  }

  @Override
  public int update(MunicipalityTax municipalityTax) {
    return taxesJpaRepository
        .findById(municipalityTax.id())
        .map(
            entity -> {
              var requestEntity = conversionService.convert(municipalityTax, TaxEntity.class);
              requestEntity.setMunicipality(entity.getMunicipality());
              assert requestEntity != null;
              taxesJpaRepository.save(requestEntity);
              return 1;
            })
        .orElse(0);
  }

  @Override
  public List<MunicipalityTax> findByMunicipalityAndDate(String municipality, LocalDate date) {
    var entityIterable =
        taxesJpaRepository.findAll(
            QTaxEntity.taxEntity
                .municipality
                .eq(municipality)
                .and(QTaxEntity.taxEntity.startDate.eq(date)));
    return StreamUtils.createStreamFromIterator(entityIterable.iterator())
        .map(entity -> conversionService.convert(entity, MunicipalityTax.class))
        .toList();
  }

  @Override
  public List<MunicipalityTax> getAllMunicipalityTaxes() {
    var entityIterable = taxesJpaRepository.findAll();
    return StreamUtils.createStreamFromIterator(entityIterable.iterator())
        .map(entity -> conversionService.convert(entity, MunicipalityTax.class))
        .toList();
  }
}
