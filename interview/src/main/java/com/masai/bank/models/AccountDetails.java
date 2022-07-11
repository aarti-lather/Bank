package com.masai.bank.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class AccountDetails {

    private String name;
    private ProofType proofType;

    @Column(unique = true)
    private String idProof;

    @Embedded
    private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProofType getProofType() {
		return proofType;
	}

	public void setProofType(ProofType proofType) {
		this.proofType = proofType;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
    
    
    
}
