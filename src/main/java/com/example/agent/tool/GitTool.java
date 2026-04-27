package com.example.agent.tool;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GitTool {

    public void commitAll(String repoPath, String message) throws Exception {
        try (Git git = Git.open(new File(repoPath))) {

            git.add().addFilepattern(".").call();
            git.commit().setMessage(message).call();
        }
    }
}
