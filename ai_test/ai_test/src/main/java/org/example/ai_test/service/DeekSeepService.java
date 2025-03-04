package org.example.ai_test.service;

import java.util.Map;

public interface DeekSeepService {
    /**
     * string 请求
     * @param
     * @return
     */
    String getAiResponse(String message);
    String generatePage(Map<String, Object> components);
}
