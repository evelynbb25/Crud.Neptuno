/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Luisa
 */
public class Cliente {

    private Integer id;
    private String codigo;
    private String empresa;
    private String contacto;
    private String cargo_Contacto;
    private String direccion;
    private String ciudad;
    private String region;
    private String cp;
    private String pais;
    private String telefono;
    private String fax;

    public Cliente() {
    }

    public Cliente(Integer id, String codigo, String empresa, String contacto, String cargo_Contacto, String direccion, String ciudad, String region, String cp, String pais, String telefono, String fax) {
        this.id = id;
        this.codigo = codigo;
        this.empresa = empresa;
        this.contacto = contacto;
        this.cargo_Contacto = cargo_Contacto;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.cp = cp;
        this.pais = pais;
        this.telefono = telefono;
        this.fax = fax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCargo_Contacto() {
        return cargo_Contacto;
    }

    public void setCargo_Contacto(String cargo_Contacto) {
        this.cargo_Contacto = cargo_Contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    //to string

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", codigo=" + codigo + ", empresa=" + empresa + ", contacto=" + contacto + ", cargo_Contacto=" + cargo_Contacto + ", direccion=" + direccion + ", ciudad=" + ciudad + ", region=" + region + ", cp=" + cp + ", pais=" + pais + ", telefono=" + telefono + ", fax=" + fax + '}';
    }
    
}
