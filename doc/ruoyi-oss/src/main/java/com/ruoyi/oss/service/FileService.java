package com.ruoyi.oss.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    public String imageToBase64(InputStream inputStream) throws IOException;

}
