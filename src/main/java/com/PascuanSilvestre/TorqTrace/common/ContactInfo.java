package com.PascuanSilvestre.TorqTrace.common;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ContactInfo {
    private String phoneNumber;
    private String email;
}
