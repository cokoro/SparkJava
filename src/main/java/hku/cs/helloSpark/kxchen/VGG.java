package hku.cs.helloSpark.kxchen;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.trainedmodels.TrainedModels;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.VGG16ImagePreProcessor;
import javax.servlet.MultipartConfigElement;

import static spark.Spark.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;

//addimage
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;

//add html
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.HashMap;

import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.net.HttpURLConnection; 
import java.net.URL; 

public class VGG {
public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    public static void main(String[] args)  throws Exception {
/*
        // Set locations for certificates for https encryption
        String keyStoreLocation = "clientkeystore";
        String keyStorePassword = "skymind";
        secure(keyStoreLocation, keyStorePassword, null,null );
*/	
        // Load the trained model
        File locationToSave = new File("demo_model.zip");
	MultiLayerNetwork vgg16=ModelSerializer.restoreMultiLayerNetwork(locationToSave);
//	ComputationGraph vgg16= ModelSerializer.restoreComputationGraph(locationToSave);

        //get("/hello", (req, res) -> "Hello World");
        // make upload directory for user submitted images
        // Images are uploaded, processed and then deleted
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist
	
	//File uploadDir = new File("target");
        // form this string displays an html form to select and upload an image
        String form = "<form method='post' action='getPredictions' enctype='multipart/form-data'>\n" +
                "    <input type='file' name='uploaded_file'>\n" +
                "    <button>Upload picture</button>\n" +
                "</form>";


        // Spark Java configuration to handle requests
        // test request, the url /hello should return "hello world"
        get("/hello", (req, res) -> "Hello World");

        // Request for VGGpredict returns the form to submit an image
        //get("/VGGpredict", (req, res) -> form);
	get("/VGGpredict", (req, res) -> {
		Map<String, Object> map = new HashMap<>();
		return new ModelAndView(map, "VGG.ftl");
        }, new FreeMarkerEngine());

        post("/getPredictions", (req, res) -> {

	    Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
	    //File imageFile = new File("New Picture.jpg");
            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
		/*byte[] image_data = readInputStream(input);
		FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
		fileOutputStream.write(image_data); 
    		fileOutputStream.close(); */
            }


            File file = tempFile.toFile();
            // Convert file to INDArray
            NativeImageLoader loader = new NativeImageLoader(218, 178, 3);
            INDArray image = loader.asRowVector(file);

            // delete the physical file, if left our drive would fill up over time
            file.delete();

	    INDArray pre = vgg16.output(image);
	    String predictions = pre.toString();


 // 2:保存原压缩尺寸的图片
   String path140 = file.getName();// 构建图片保存的目录


	    Map<String, Object> map = new HashMap<>();
            map.put("message", "The baldness prediction of the picture is: "+predictions);
            //map.put("pic_address",path140); 

		System.out.print("==================predictions is "+predictions); 
            return new ModelAndView(map, "VGG.ftl");
        }, new FreeMarkerEngine());

       /*     return "<h1> '" + predictions  + "' </h1>" +
                    "Would you like to try another" +
                    form;

            //return "<h1>Your image is: '" + tempFile.getName(1).toString() + "' </h1>";


        });*/
    }
}
