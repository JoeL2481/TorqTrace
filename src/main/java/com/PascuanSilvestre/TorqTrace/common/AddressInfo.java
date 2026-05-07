package com.PascuanSilvestre.TorqTrace.common;

import jakarta.persistence.Embeddable;


@Embeddable
public class AddressInfo {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
