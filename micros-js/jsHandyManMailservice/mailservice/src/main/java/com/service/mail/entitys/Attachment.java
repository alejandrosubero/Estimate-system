package com.service.mail.entitys;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "attachments")
public class Attachment {

    private static final long serialVersionUID = 8286941585339050054L;

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 5000)
    @Column(name = "idAttachment", updatable = true, nullable = false, length = 100)
    private Long idAttachment;

    @Lob
    @Column(name = "fileAttachment", updatable = true, nullable = true)
    private  byte[] fileAttachment;

    @Column(name = "fileName", updatable = true, nullable = true, length = 200)
    private String fileName;

    public Attachment() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment)) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(idAttachment, that.idAttachment) && Arrays.equals(fileAttachment, that.fileAttachment) && Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idAttachment, fileName);
        result = 31 * result + Arrays.hashCode(fileAttachment);
        return result;
    }
}
