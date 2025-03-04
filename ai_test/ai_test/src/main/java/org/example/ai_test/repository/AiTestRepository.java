package org.example.ai_test.repository;

import org.example.ai_test.commen.config.DeekSeepApiConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Repository
public class AiTestRepository {

    private final RestTemplate restTemplate;
    private final DeekSeepApiConfig deekSeepApiConfig;

    public AiTestRepository(RestTemplate restTemplate, DeekSeepApiConfig deekSeepApiConfig) {
        this.restTemplate = restTemplate;
        this.deekSeepApiConfig = deekSeepApiConfig;
    }

    /**
     * 发送普通 AI 聊天请求
     */
    public String callDeepSeekApi(String userMessage) {
        Map<String, Object> requestBody = Map.of(
                "model", "deepseek-chat",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are a helpful assistant."),
                        Map.of("role", "user", "content", userMessage)
                }
        );

        return sendPostRequest(requestBody);
    }

    /**
     * 发送 DeepSeek 生成 Vue3 页面请求
     */
    public String callDeepSeekForPage(Map<String, Object> components) {
        Map<String, Object> requestBody = Map.of(
                "model", "deepseek-chat",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "你是一个前端专家，你需要使用 Vue3 组件库完成页面。"),
                        Map.of("role", "user", "content", "请使用以下组件库构建一个用户登录页面：" + components.get("components"))
                }
        );

        return sendPostRequest(requestBody);
    }

    /**
     * 统一封装 HTTP POST 请求
     */
    private String sendPostRequest(Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + deekSeepApiConfig.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForObject(deekSeepApiConfig.getApiUrl(), entity, String.class);
    }
}
