package com.socialMention.socialMention;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialMentionController {

    @PostMapping("/socialMention")
    public ResponseEntity<String> handleSocialMention(@RequestBody SocialMention mention) {
        String platform;
        if (mention.getTweeterAccount() != null && !mention.getTweeterAccount().isEmpty()) {
            platform = "Twitter";
        } else if (mention.getFacebookAccount() != null && !mention.getFacebookAccount().isEmpty()) {
            platform = "Facebook";
        } else {
            return ResponseEntity.badRequest().body("Invalid social mention");
        }

        String analysisResult = analyzeMention(platform, mention);
        saveToDatabase(analysisResult);

        String clientMessage = mapToClientMessage(analysisResult);
        return ResponseEntity.ok(clientMessage);
    }

    private String analyzeMention(String platform, SocialMention mention) {
        // Call the respective social media analyzer based on the platform
        if ("Twitter".equals(platform)) {
            return analyzeTwitterMention(mention);
        } else {
            return analyzeFacebookMention(mention);
        }
    }

    private String analyzeTwitterMention(SocialMention mention) {
        // Placeholder for actual Twitter analysis logic
        return "Twitter analysis result";
    }

    private String analyzeFacebookMention(SocialMention mention) {
        // Placeholder for actual Facebook analysis logic
        return "Facebook analysis result";
    }

    private void saveToDatabase(String analysisResult) {
        // Placeholder for actual database save logic
    }

    private String mapToClientMessage(String analysisResult) {
        // Placeholder for mapping logic to client message
        return "Client message: " + analysisResult;
    }
}

