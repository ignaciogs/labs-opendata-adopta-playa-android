/*
 * Copyright (C) 2012 47 Degrees, LLC
 *   http://47deg.com
 *   hello@47deg.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.fortysevendeg.android.adoptaunaplaya.api.response;

import it.restrung.rest.marshalling.response.AbstractJSONResponse;

import java.io.Serializable;

/**
 * BeachResponse class
 */
public class BeachResponse extends AbstractJSONResponse implements Serializable {

    private String id;

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

    private double longitude;

    private double latitude;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeachResponse that = (BeachResponse) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
