package joen.website.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import joen.website.domain.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Serializable>{

}
