package com.fortysevendeg.android.adoptaunaplaya.api.response;

import it.restrung.rest.marshalling.response.AbstractJSONResponse;

import java.io.Serializable;

/**
 * BeachResponse class
 */
public class BeachResponse extends AbstractJSONResponse implements Serializable {

    private String comunidad;

    private String provincia;

    private String municipio;

    private String nombre;

    private String puntoMuestreo;

    private String adoptadaPor;

    private String utmX;

    private String utmY;

    private String utmHuso;

    private String fechaToma;

    private String escherichiaColi;

    private String enterococo;

    private String observaciones;

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntoMuestreo() {
        return puntoMuestreo;
    }

    public void setPuntoMuestreo(String puntoMuestreo) {
        this.puntoMuestreo = puntoMuestreo;
    }

    public String getAdoptadaPor() {
        return adoptadaPor;
    }

    public void setAdoptadaPor(String adoptadaPor) {
        this.adoptadaPor = adoptadaPor;
    }

    public String getUtmX() {
        return utmX;
    }

    public void setUtmX(String utmX) {
        this.utmX = utmX;
    }

    public String getUtmY() {
        return utmY;
    }

    public void setUtmY(String utmY) {
        this.utmY = utmY;
    }

    public String getUtmHuso() {
        return utmHuso;
    }

    public void setUtmHuso(String utmHuso) {
        this.utmHuso = utmHuso;
    }

    public String getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(String fechaToma) {
        this.fechaToma = fechaToma;
    }

    public String getEscherichiaColi() {
        return escherichiaColi;
    }

    public void setEscherichiaColi(String escherichiaColi) {
        this.escherichiaColi = escherichiaColi;
    }

    public String getEnterococo() {
        return enterococo;
    }

    public void setEnterococo(String enterococo) {
        this.enterococo = enterococo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
