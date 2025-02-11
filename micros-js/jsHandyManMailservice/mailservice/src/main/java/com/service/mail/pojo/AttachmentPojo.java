package com.service.mail.pojo;


import java.util.Arrays;
import java.util.Objects;

public class AttachmentPojo {

    private Long idAttachment;
    private  byte[] fileAttachment;
    private String fileName;

    public AttachmentPojo() {
    }

    public AttachmentPojo(byte[] fileAttachment, String fileName) {
        this.fileAttachment = fileAttachment;
        this.fileName = fileName;
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public byte[] getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(byte[] fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttachmentPojo)) return false;
        AttachmentPojo that = (AttachmentPojo) o;
        return Objects.equals(idAttachment, that.idAttachment) && Arrays.equals(fileAttachment, that.fileAttachment) && Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idAttachment, fileName);
        result = 31 * result + Arrays.hashCode(fileAttachment);
        return result;
    }
}
