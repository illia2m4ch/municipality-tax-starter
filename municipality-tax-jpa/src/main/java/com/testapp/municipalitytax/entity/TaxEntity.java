package com.testapp.municipalitytax.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "taxes")
public class TaxEntity {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  UUID id;

  @Column(name = "municipality", nullable = false)
  String municipality;

  @Column(name = "tax", nullable = false)
  Double tax;

  @Column(name = "start_date", nullable = false)
  LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  LocalDate endDate;

  public TaxEntity() {
    this(null, null, null, null, null);
  }

  public TaxEntity(
      UUID id, String municipality, Double tax, LocalDate startDate, LocalDate endDate) {
    this.id = id;
    this.municipality = municipality;
    this.tax = tax;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public UUID getId() {
    return id;
  }

  public String getMunicipality() {
    return municipality;
  }

  public Double getTax() {
    return tax;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaxEntity taxEntity = (TaxEntity) o;
    return Objects.equals(id, taxEntity.id)
        && Objects.equals(municipality, taxEntity.municipality)
        && Objects.equals(tax, taxEntity.tax)
        && Objects.equals(startDate, taxEntity.startDate)
        && Objects.equals(endDate, taxEntity.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, municipality, tax, startDate, endDate);
  }
}
