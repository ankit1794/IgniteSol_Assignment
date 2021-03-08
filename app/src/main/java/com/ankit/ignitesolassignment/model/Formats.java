package com.ankit.ignitesolassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formats {

    @SerializedName("application/x-mobipocket-ebook")
    @Expose
    private String application_x_mobipocket_ebook;

    @SerializedName("application/pdf")
    @Expose
    private String application_pdf;

    @SerializedName("text/plain; charset=us-ascii")
    @Expose
    private String text_plain_charset_us_ascii;

    @SerializedName("text/plain; charset=utf-8")
    @Expose
    private String text_plain_charset_utf_8;

    @SerializedName("application/rdf+xml")
    @Expose
    private String application_rdf_xml;

    @SerializedName("application/zip")
    @Expose
    private String application_zip;

    @SerializedName("application/epub+zip")
    @Expose
    private String application_epub_zip;

    @SerializedName("text/html; charset=utf-8")
    @Expose
    private String text_html_charset_utf_8;


    @SerializedName("image/jpeg")
    @Expose
    private String image_jpeg;


    public String getImage_jpeg() {
        return image_jpeg;
    }

    public void setImage_jpeg(String image_jpeg) {
        this.image_jpeg = image_jpeg;
    }

    public String getApplication_x_mobipocket_ebook() {
        return application_x_mobipocket_ebook;
    }

    public void setApplication_x_mobipocket_ebook(String application_x_mobipocket_ebook) {
        this.application_x_mobipocket_ebook = application_x_mobipocket_ebook;
    }

    public String getApplication_pdf() {
        return application_pdf;
    }

    public void setApplication_pdf(String application_pdf) {
        this.application_pdf = application_pdf;
    }

    public String getText_plain_charset_us_ascii() {
        return text_plain_charset_us_ascii;
    }

    public void setText_plain_charset_us_ascii(String text_plain_charset_us_ascii) {
        this.text_plain_charset_us_ascii = text_plain_charset_us_ascii;
    }

    public String getText_plain_charset_utf_8() {
        return text_plain_charset_utf_8;
    }

    public void setText_plain_charset_utf_8(String text_plain_charset_utf_8) {
        this.text_plain_charset_utf_8 = text_plain_charset_utf_8;
    }

    public String getApplication_rdf_xml() {
        return application_rdf_xml;
    }

    public void setApplication_rdf_xml(String application_rdf_xml) {
        this.application_rdf_xml = application_rdf_xml;
    }

    public String getApplication_zip() {
        return application_zip;
    }

    public void setApplication_zip(String application_zip) {
        this.application_zip = application_zip;
    }

    public String getApplication_epub_zip() {
        return application_epub_zip;
    }

    public void setApplication_epub_zip(String application_epub_zip) {
        this.application_epub_zip = application_epub_zip;
    }

    public String getText_html_charset_utf_8() {
        return text_html_charset_utf_8;
    }

    public void setText_html_charset_utf_8(String text_html_charset_utf_8) {
        this.text_html_charset_utf_8 = text_html_charset_utf_8;
    }
}

