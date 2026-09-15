package com.example.projet_5_safetynetspring_boot.service;

import java.util.List;

public interface CommunityEmailService {

    List<String> getEmailsByCity(String city);
}