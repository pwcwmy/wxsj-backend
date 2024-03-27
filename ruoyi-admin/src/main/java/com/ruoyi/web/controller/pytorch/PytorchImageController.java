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

//    @PostMapping("/recognize")
//    public String recognizeImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
//        // 保存图像文件到临时文件
//        File tempFile = File.createTempFile("image", imageFile.getOriginalFilename());
//        imageFile.transferTo(tempFile);
//
//        // 调用PyTorch模型进行图像识别
//        PythonInterpreter pythonInterpreter = new PythonInterpreter();
//        pythonInterpreter.exec("image = load_image('" + tempFile.getAbsolutePath() + "')");
//        pythonInterpreter.exec("result = model.predict(image)");
//
//        // 处理模型预测结果
//        PyObject resultObject = pythonInterpreter.get("result");
//        String result = resultObject.toString();
//
//        // 删除临时文件
//        tempFile.delete();
//
//        return result;
//    }

    String pythonInterpreterPath = "E:\\Anaconda3\\envs\\pytorch\\python.exe";
    String pythonScriptPath = "E:\\RuoYi\\RuoYi-Vue\\ruoyi-admin\\src\\main\\resources\\main_0318.py";


//    /**
//     *  调用PyTorch模型进行图像识别
//     * @param imageFile
//     * @return
//     */
//    @Anonymous
//    @PostMapping("/recognize")
//    public String recognize(@RequestParam("image") MultipartFile imageFile) {
//        System.out.println("图像识别接口开始调用");
//        try {
//            long startTime = System.currentTimeMillis();
//
//            // 保存上传的图像到临时文件
//            File tempFile = File.createTempFile("uploaded_image", imageFile.getOriginalFilename());
//            imageFile.transferTo(tempFile);
//            String imagePath = tempFile.getAbsolutePath();
//
//            // 执行Python脚本
//            ProcessBuilder processBuilder = new ProcessBuilder(
//                    pythonInterpreterPath,
//                    pythonScriptPath,
//                    "--image_path=" + imagePath
//            );
//
//            // 合并错误流到输入流
//            processBuilder.redirectErrorStream(true);
//
//            Process process = processBuilder.start();
//            int exitCode = process.waitFor();
//            // 获取Python脚本的输出
//            InputStream inputStream = process.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            StringBuilder output = new StringBuilder();
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");
//            }
//            StringBuilder output1 = output;
//
//            long endTime = System.currentTimeMillis();
//            long executionTime = endTime - startTime;
//
//            // 输出完整的执行结果和执行时间
//            System.out.println("Python脚本执行结果:\n" + output.toString());
//            System.out.println("接口执行时间: " + executionTime + " 毫秒");
//
//            // 删除临时文件
//            tempFile.delete();
//
//            if (exitCode == 0) {
//                return "Python脚本执行成功，结果: " + output.toString();
//            } else {
//                return "Python脚本执行失败";
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return "执行失败: " + e.getMessage();
//        }
//    }
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
                return new AsyncResult<>("Python脚本执行成功，结果: " + output.toString());
            } else {
                return new AsyncResult<>("Python脚本执行失败");
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
}


