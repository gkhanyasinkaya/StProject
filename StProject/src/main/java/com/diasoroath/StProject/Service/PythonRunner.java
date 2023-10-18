package com.diasoroath.StProject.Service;

import java.io.IOException;

public class PythonRunner {

    public static void runPythonScript(String scriptPath, String base64Photo, String userId, String reportId) throws IOException {



        String pythonExecutable = "python.exe";
        ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutable, scriptPath, base64Photo, userId, reportId);
        Process process = processBuilder.start();


        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
            } else {
                System.out.println("Python script execution failed with exit code: " + exitCode);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("PythonRunner <scriptPath> <base64Photo> <userId> <reportId>");
            return;
        }

        String scriptPath = args[0];
        String base64Photo = args[1];
        String userId = args[2];
        String reportId = args[3];
        runPythonScript(scriptPath, base64Photo, userId, reportId);
    }
}

