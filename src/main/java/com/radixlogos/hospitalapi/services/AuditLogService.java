package com.radixlogos.hospitalapi.services;

import com.radixlogos.hospitalapi.entities.AuditLog;
import com.radixlogos.hospitalapi.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAction(String actionDescription) {
        String username = getCurrentUsername();
        AuditLog log = new AuditLog(username, actionDescription, Instant.now());
        auditLogRepository.save(log);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "Anonymous";
    }
}
