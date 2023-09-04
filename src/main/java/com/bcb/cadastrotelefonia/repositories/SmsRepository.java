package com.bcb.cadastrotelefonia.repositories;

import com.bcb.cadastrotelefonia.domain.sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, Long> {
}
