package org.example.ai_test.commen.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParser {
    public static String extractCodeFromJson(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 获取 content 字段
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode messageNode = choicesNode.get(0).path("message");
                String content = messageNode.path("content").asText();

                // 仅提取代码块
                return extractCodeFromContent(content);
            }

            return "错误：API 响应中未找到代码";
        } catch (Exception e) {
            return "JSON 解析错误: " + e.getMessage();
        }
    }

    private static String extractCodeFromContent(String content) {
        // 使用正则匹配代码块（``` 包裹的内容）
        String codeRegex = "```[a-zA-Z]*\\n([\\s\\S]*?)```";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(codeRegex);
        java.util.regex.Matcher matcher = pattern.matcher(content);

        StringBuilder codeBlocks = new StringBuilder();
        while (matcher.find()) {
            codeBlocks.append(matcher.group(1)).append("\n\n");
        }

        return codeBlocks.length() > 0 ? codeBlocks.toString().trim() : "未找到代码";
    }
}

