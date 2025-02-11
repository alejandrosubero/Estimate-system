package com.jshandyman.service.serviceImplement;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.service.ServiceFile;
import com.jshandyman.service.service.TemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Comparator;
import java.util.stream.Stream;

@Component
public class BuildPdfAndFile implements ServiceFile {

    @Autowired
    private TemplateService templateService;

    protected static final Log logger = LogFactory.getLog(MailServicesImplment.class);


    @Override
    public Object upload(MultipartFile fileUpload, String objeto) {
        return new Object();
    }


    public void crearArchivo(String direccion, String escrito, String nombreArchivo) {

        logger.info(" start Build File from html template");

        String carpetas = direccion;
        String archivos = java.nio.file.FileSystems.getDefault().getSeparator() + nombreArchivo;
        String contenido1 = escrito;

        File create_carpeta = new File(carpetas);
        File create_archivo = new File(carpetas + archivos);

        if (create_archivo.exists()) {
            logger.info("THE File EXISTS");
            deleteFileOrFolder(carpetas + archivos);
            try {
                if (create_archivo.createNewFile()) {
                    FileWriter fw = new FileWriter(create_archivo);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido1);
                    bw.close();
                    logger.info("THE FILE WAS CREATED");
                } else {
                    logger.info("THE FILE WAS NOT CREATED");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("THE File DOES NOT EXIST IT WILL BE CREATED");
            create_carpeta.mkdirs();
            try {
                if (create_archivo.createNewFile()) {
                    logger.info("Create a New File...");
                    FileWriter fw = new FileWriter(create_archivo);
                    logger.info("File Writer...");
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido1);
                    logger.info("Close File Writer...");
                    bw.close();
                    logger.info("THE FILE WAS CREATED");
                } else {
                    logger.info("THE FILE WAS NOT CREATED");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteFileOrFolder(String directorio) {
        try{
//            Path filePath = Paths.get(directorio);
//            Files.delete(filePath);

            File f = new File(directorio);
            this.delete(f);
            if(f.delete()){
                logger.info(" the File was delete : "+f.exists());
            }else {
                logger.info(" the File, don't was delete : "+f.exists());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//            logger.info(" Delete-File: "+directorio);
    }

    private void delete(File fileDel) {
        if (fileDel.isDirectory()) {
            if (fileDel.list().length == 0) {
                fileDel.delete();
            } else {
                for (String temp : fileDel.list()) {
                    File fileDelete = new File(fileDel, temp);
                    delete(fileDelete);
                }
                if (fileDel.list().length == 0) {
                    fileDel.delete();
                }
            }
        } else {
            fileDel.delete();
        }
//        confirDelete(fileDel);
    }


    public static void deleteDirectoryJava8(String dir) throws IOException {
        Path path = Paths.get(dir);
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(path1 -> deleteDirectoryJava8Extract(path1));
        }
    }

    // extract method to handle exception in lambda
    public static void deleteDirectoryJava8Extract(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }



    public void confirDelete(File file){
        try{
            if(file.exists()){
                logger.info(" the File was delete : "+file.exists());
            }else {
                logger.info(" the File, don't was delete : "+file.exists());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void buildingPdf(String imput, String Ouput, String basePath) throws FileNotFoundException {

        File htmlSource = null;
        File pdfDest = null;
        try {
            htmlSource = new File(basePath + imput);
            pdfDest = new File(basePath + Ouput);
            logger.info("pdfHTML specific code");
            ConverterProperties converterProperties = new ConverterProperties();
            logger.info("convertToPdf");
            HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            htmlSource = null;
            pdfDest = null;
        }
    }


    public void buildPdf(String imput, String Ouput) throws FileNotFoundException {
        FileInputStream html = null;
        FileOutputStream pdf = null;
        try {
            logger.info("PDF Created STARTET ...");
            html = new FileInputStream(Constant.templateFile + imput);
            pdf = new FileOutputStream(Constant.templateFile + Ouput);
            HtmlConverter.convertToPdf(html, pdf);
            logger.info("PDF Created!...");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                html.close();
                pdf.close();
                logger.info("files html and PDF are close...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void mergePdfFile(String imput1, String imput2, String finalName, String basePat) throws FileNotFoundException {
        logger.info("Merge PDF Files...");
        try {
            FileInputStream pdf1 = new FileInputStream(basePat + imput1);
            FileInputStream pdf2 = new FileInputStream(basePat + imput2);
            PDFMergerUtility mergerUtility = new PDFMergerUtility();
            mergerUtility.addSource(pdf1);
            mergerUtility.addSource(pdf2);
            mergerUtility.setDestinationFileName(basePat+ finalName);
            mergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            pdf1.close();
            pdf2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String base64EncodePdf(String archivo) {
        String b64 = "";
        try {
            File file = new File(Constant.templateFile + archivo);
            byte[] bytes = Files.readAllBytes(file.toPath());
            b64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return b64;
    }

}
