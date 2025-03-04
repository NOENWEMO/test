package org.example.ai_test.controller;

import org.example.ai_test.commen.result.Result;
import org.example.ai_test.service.impl.AiTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiTestController {

    @Autowired
    private AiTestService aiTestService;

    /**
     * 普通 AI 聊天请求
     */
    @GetMapping("/chat")
    public Result<String> getAiResponse(@RequestParam String message) {
        String result = aiTestService.getAiResponse(message);
        return Result.success(result);
    }

    /**
     * 让 DeepSeek 生成 Vue3 页面
     */
    @PostMapping("/generate")
    public Result<String> generatePage(@RequestBody Map<String, Object> requestPayload) {
        String result = aiTestService.generatePage(requestPayload);
        return Result.success(result);
    }
}
