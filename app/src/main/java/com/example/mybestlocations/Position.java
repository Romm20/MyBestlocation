package com.example.mybestlocations;

public class Position {
    int idposition;
    String numero;
    String psuedo;
    String longitude;
    String latitude;


    public Position(int idposition, String numero, String psuedo, String longitude, String latitude) {
        this.idposition = idposition;
        this.numero = numero;
        this.psuedo = psuedo;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "Position{" +
                "idposition=" + idposition +
                ", numero='" + numero + '\'' +
                ", psuedo='" + psuedo + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}


