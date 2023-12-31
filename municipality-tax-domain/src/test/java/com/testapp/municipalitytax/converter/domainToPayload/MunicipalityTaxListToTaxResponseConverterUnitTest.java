package com.testapp.municipalitytax.converter.domainToPayload;

import static org.assertj.core.api.Assertions.assertThat;

import com.testapp.municipalitytax.TestDataFactory;
import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.web.payload.TaxResponse;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MunicipalityTaxListToTaxResponseConverterUnitTest {

  private MunicipalityTaxListToTaxResponseConverter converter;

  @Before
  public void setUp() {
    converter = new MunicipalityTaxListToTaxResponseConverter();
  }

  @Test
  public void shouldReturnTaxResponse_whenConvert_givenMunicipalityTaxList() {
    // given
    List<MunicipalityTax> municipalityTaxList =
        Collections.singletonList(TestDataFactory.createSavedMunicipalityTax());
    TaxResponse expected = TestDataFactory.createTaxResponse();

    // when
    TaxResponse result = converter.convert(municipalityTaxList);

    // then
    assertThat(result).isEqualTo(expected);
  }
}
