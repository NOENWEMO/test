package org.example.ai_test.service.impl;

import org.example.ai_test.commen.json.JsonParser;
import org.example.ai_test.repository.AiTestRepository;
import org.example.ai_test.service.DeekSeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AiTestService implements DeekSeepService {

    @Autowired // 依赖注入
    private AiTestRepository aiTestRepository;

    /**
     * 处理普通 AI 聊天请求
     * @param message 用户输入
     * @return AI 回复
     */
    @Override
    public String getAiResponse(String message) {
        return aiTestRepository.callDeepSeekApi(message);
    }

    /**
     * 处理 DeepSeek 响应并提取代码
     * @param requestPayload 请求体
     * @return 仅返回代码部分
     */
    @Override
    public String generatePage(Map<String, Object> requestPayload) {
        String response = aiTestRepository.callDeepSeekApi(requestPayload.toString());

        // **打印 API 返回的 JSON，看看结构**
        System.out.println("DeepSeek API Response: " + response);

        String extractedCode = JsonParser.extractCodeFromJson(response);
        System.out.println("===== 提取的代码 =====");
        System.out.println(extractedCode);

        return extractedCode;
    }


}
