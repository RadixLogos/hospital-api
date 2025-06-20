package com.radixlogos.hospitalapi.repositories;

import com.radixlogos.hospitalapi.entities.AuditLog;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends GeneralRepository<AuditLog,Long>{

}
