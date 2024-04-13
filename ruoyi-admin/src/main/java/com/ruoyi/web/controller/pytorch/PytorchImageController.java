package com.ruoyi.web.controller.pytorch;


import com.ruoyi.common.annotation.Anonymous;
import org.python.core.PyObject;
//import org.python.core.io.BufferedReader;
import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@CrossOrigin
@RestController
@RequestMapping("/pytorch/image")
public class PytorchImageController {
    String pythonInterpreterPath = "E:\\Anaconda3\\envs\\pytorch\\python.exe";
    String pythonScriptPath = "E:\\RuoYi\\RuoYi-Vue\\ruoyi-admin\\src\\main\\resources\\main_0318.py";
    @Async
    public Future<String> executePythonScript(String imagePath) {
        long startTime = System.currentTimeMillis();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    pythonInterpreterPath,
                    pythonScriptPath,
                    "--image_path=" + imagePath
            );

            // 合并错误流到输入流
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // 获取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 输出完整的执行结果
            System.out.println("Python脚本执行结果:\n" + output.toString());

            long endTime = System.currentTimeMillis();
            System.out.println("Python脚本执行时间: " + (endTime - startTime) + " 毫秒");

            if (exitCode == 0) {
                return new AsyncResult<>(output.toString());
            } else {
                return new AsyncResult<>("图像识别模型执行失败");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new AsyncResult<>("执行失败: " + e.getMessage());
        }
    }

    @Anonymous
    @PostMapping("/recognize")
    public String recognize(@RequestParam("image") MultipartFile imageFile) {
        long startApiTime = System.currentTimeMillis();

        try {
            // 保存上传的图像到临时文件
            File tempFile = File.createTempFile("uploaded_image", imageFile.getOriginalFilename());
            imageFile.transferTo(tempFile);
            String imagePath = tempFile.getAbsolutePath();

            // 异步执行Python脚本
            Future<String> resultFuture = executePythonScript(imagePath);

            // 删除临时文件
            tempFile.delete();

            // 等待异步任务完成并返回结果
            String result = resultFuture.get();
            System.out.println("result: " + result);

            long endApiTime = System.currentTimeMillis();
            System.out.println("API执行时间: " + (endApiTime - startApiTime) + " 毫秒");

            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "执行失败: " + e.getMessage();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    // filepath 入参
//    @Anonymous
//    @PostMapping("/recognize")
//    public String recognize(@RequestParam("filepath") String filePath) {
//        long startApiTime = System.currentTimeMillis();
//        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                return "文件不存在: " + filePath;
//            }
//            // 异步执行Python脚本
//            Future<String> resultFuture = executePythonScript(filePath);
//            // 等待异步任务完成并返回结果
//            String result = resultFuture.get();
//            System.out.println("result: " + result);
//            long endApiTime = System.currentTimeMillis();
//            System.out.println("API执行时间: " + (endApiTime - startApiTime) + " 毫秒");
//            return result;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return "执行失败: " + e.getMessage();
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
}


