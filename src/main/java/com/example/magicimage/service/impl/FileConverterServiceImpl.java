package com.example.magicimage.service.impl;

import com.example.magicimage.service.FileConverterService;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileConverterServiceImpl implements FileConverterService {
    @Override
    public void convert(String pdfFilePath, String outputFolderPath) throws IOException {
        ConvertCmd cmd = new ConvertCmd();
        var listener = new FileListenerImpl();
          try {
              // 70 is not good -> 4 minutes
              // 120 -> 10 minutes
              // 100 -> 5 minutes
              // 200 ->
              var operation = new IMOperation()
                      .density(100).quality(100d)
                      .addImage(pdfFilePath)
                      .addImage(outputFolderPath+"01.pdf");
              cmd.setAsyncMode(true);
              cmd.addProcessEventListener(listener);
                cmd.run(operation);
                while(true){
                    Thread.sleep(3000);
                    if(!listener.isRunning())
                        break;
                }
                listener.destroy();
            } catch (IM4JavaException | InterruptedException e) {
                throw new RuntimeException(e);
            }

    }
}
