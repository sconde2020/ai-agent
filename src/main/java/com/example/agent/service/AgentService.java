package com.example.agent.service;

import com.example.agent.apiclient.LlmClient;
import com.example.agent.tool.FileTool;
import com.example.agent.tool.GitTool;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final LlmClient llm;
    private final FileTool fileTool;
    private final GitTool gitTool;

    public AgentService(LlmClient llm, FileTool fileTool, GitTool gitTool) {
        this.llm = llm;
        this.fileTool = fileTool;
        this.gitTool = gitTool;
    }

    public void runTask(String task) throws Exception {

        String prompt = """
        You are a Java Spring Boot developer.
        Task: %s
        Generate ONLY a valid Java file.
        """.formatted(task);

        String code = llm.generateCode(prompt);

        String filePath = "generated/UserController.java";

        fileTool.writeFile(filePath, code);

        gitTool.commitAll(".", "feat: AI generated " + task);
    }
}
