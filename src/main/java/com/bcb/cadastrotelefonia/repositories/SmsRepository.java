package com.bcb.cadastrotelefonia.repositories;

import com.bcb.cadastrotelefonia.domain.sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    List<Sms> findBySender(String telefoneSender);
    List<Sms> findByReceiver (String telefoneReceiver);
}
